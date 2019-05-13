package me.wcc.homenvi.service;

import me.wcc.base.infra.utils.DateTimeUtils;
import me.wcc.homenvi.dao.HomenviCollectionsDao;
import me.wcc.homenvi.entity.CollectionLevel;
import me.wcc.homenvi.entity.CollectionSpecification;
import me.wcc.homenvi.entity.HomenviCollections;
import me.wcc.homenvi.utils.InfluxQueryHelper;
import me.wcc.homenvi.utils.InfluxQueryHelper.AggregateFunctions;
import me.wcc.homenvi.utils.InfluxQueryHelper.SelectorFunctions;
import me.wcc.homenvi.vo.CollectionAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chuncheng.wang@hand-china.com 2019-01-29 16:29:28
 */
@SuppressWarnings("unused")
@Service
public class HomenviCollectionsService {
    private HomenviCollectionsDao homenviCollectionsDao;
    private CollectionSpecificationService collectionSpecificationService;
    private CollectionLevelService collectionLevelService;

    @Autowired
    public void setCollectionLevelService(CollectionLevelService collectionLevelService) {
        this.collectionLevelService = collectionLevelService;
    }

    @Autowired
    public void setCollectionSpecificationService(CollectionSpecificationService collectionSpecificationService) {
        this.collectionSpecificationService = collectionSpecificationService;
    }

    @Autowired
    public void setHomenviCollectionsDao(HomenviCollectionsDao homenviCollectionsDao) {
        this.homenviCollectionsDao = homenviCollectionsDao;
    }

    /**
     * 获取均值
     *
     * @param originFields 字段
     * @param start        开始时间
     * @param end          结束时间
     * @return HomenviCollections
     */
    public HomenviCollections getMeanByTimeRange(List<String> originFields, LocalDateTime start, LocalDateTime end) {
        return calculateByTimeRange(originFields, AggregateFunctions.MEAN, start, end);
    }

    /**
     * 获取最大值
     *
     * @param originFields 字段
     * @param start        开始时间
     * @param end          结束时间
     * @return HomenviCollections
     */
    public HomenviCollections getMaxByTimeRange(List<String> originFields, LocalDateTime start, LocalDateTime end) {
        return calculateByTimeRange(originFields, SelectorFunctions.MAX, start, end);
    }


    /**
     * 获取最小值
     *
     * @param originFields 字段
     * @param start        开始时间
     * @param end          结束时间
     * @return HomenviCollections
     */
    public HomenviCollections getMinByTimeRange(List<String> originFields, LocalDateTime start, LocalDateTime end) {
        return calculateByTimeRange(originFields, SelectorFunctions.MIN, start, end);
    }

    /**
     * 获取极差
     *
     * @param originFields 字段
     * @param start        开始时间
     * @param end          结束时间
     * @return HomenviCollections
     */
    public HomenviCollections getSpreadByTimeRange(List<String> originFields, LocalDateTime start, LocalDateTime end) {
        return calculateByTimeRange(originFields, AggregateFunctions.SPREAD, start, end);
    }

    private HomenviCollections calculateByTimeRange(List<String> originFields, String aggregate, LocalDateTime start, LocalDateTime end) {
        List<String> fields = originFields.stream()
                .map(field -> aggregate + "(" + field + ") AS " + field)
                .collect(Collectors.toList());
        List<String> conditions = Arrays.asList(
                "time>" + DateTimeUtils.getNanoTime(start),
                "time<" + DateTimeUtils.getNanoTime(end)
        );
        String sql = InfluxQueryHelper.query(fields, HomenviCollections.MEASUREMENT, conditions);
        List<HomenviCollections> collectionsList = homenviCollectionsDao.queryForList(sql);
        if (CollectionUtils.isEmpty(collectionsList)) {
            return null;
        }
        return collectionsList.get(0);
    }

    private String report(Boolean html, LocalDateTime start, LocalDateTime end) {
        final List<String> fields = HomenviCollections.VITAL_FIELDS;
        HomenviCollections meanCollections = getMeanByTimeRange(fields, start, end);
        LocalDateTime lastStart = start.minusSeconds(DateTimeUtils.secondsBetween(start, end));
        HomenviCollections lastMeanCollections = getMeanByTimeRange(fields, lastStart, start);
        if (isAnyNull(meanCollections, lastMeanCollections)) {
            return null;
        }
        Map<String, CollectionSpecification> specificationMap = collectionSpecificationService.selectMapByFields(fields);
        Map<String, Double> meanValues = meanCollections.getValues();
        Map<String, Double> lastMeanValues = lastMeanCollections.getValues();
        if (html) {
            return buildHtmlContent(meanValues, lastMeanValues, specificationMap);
        }
        return buildTextContent(meanValues, lastMeanValues, specificationMap);
    }

    private String buildTextContent(Map<String, Double> meanValues, Map<String, Double> lastMeanValues,
                                    Map<String, CollectionSpecification> specificationMap) {
        StringBuilder reportBuilder = new StringBuilder();
        meanValues.forEach((key, value) -> {
            Double lastValue = lastMeanValues.get(key);
            CollectionSpecification specification = specificationMap.get(key);
            reportBuilder.append(specification.getName())
                    .append("的平均值约为").append(Math.round(value)).append(specification.getUnit()).append("，");
            CollectionLevel level = collectionLevelService.satisfiedLevel(key, value);
            if (null != level) {
                reportBuilder.append("表现为").append(level.getLevelName()).append("，");
            }
            long increase = Math.round(value) - Math.round(lastValue);
            if (increase > 0) {
                reportBuilder.append("较上个周期增加").append(increase).append(specification.getUnit());
            } else if (increase < 0) {
                reportBuilder.append("较上个周期降低").append(increase).append(specification.getUnit());
            }
            reportBuilder.append('\n');
        });
        return reportBuilder.toString();
    }

    private final String preSmall = "<small>";
    private final String sufSmall = "</small>";
    private final String preStrong = "<strong>";
    private final String sufStrong = "</strong>";
    private String buildHtmlContent(Map<String, Double> meanValues, Map<String, Double> lastMeanValues,
                                    Map<String, CollectionSpecification> specificationMap) {
        StringBuilder reportBuilder = new StringBuilder();
        meanValues.forEach((key, value) -> {
            Double lastValue = lastMeanValues.get(key);
            CollectionSpecification specification = specificationMap.get(key);
            reportBuilder.append("<p>").append(preStrong).append(specification.getName()).append(sufStrong)
                    .append("的平均值约为").append(Math.round(value)).append(preSmall).append(specification.getUnit())
                    .append(sufSmall).append("，");
            CollectionLevel level = collectionLevelService.satisfiedLevel(key, value);
            if (null != level) {
                reportBuilder.append("表现为").append(preStrong).append(level.getLevelName()).append(sufStrong)
                        .append("，");
            }
            long increase = Math.round(value) - Math.round(lastValue);
            if (increase > 0) {
                reportBuilder.append("较上个周期增加").append(increase).append(preSmall).append(specification.getUnit())
                        .append(sufSmall);
            } else if (increase < 0) {
                reportBuilder.append("较上个周期降低").append(increase).append(preSmall).append(specification.getUnit())
                        .append(sufSmall);
            } else {
                reportBuilder.append("与上个周期持平");
            }
            reportBuilder.append("</p>");
        });
        return reportBuilder.toString();
    }

    public String simpleReport(LocalDateTime start, LocalDateTime end) {
        return report(false, start, end);
    }

    /**
     * 生成指定时间的报告文本
     *
     * @param start start
     * @param end   end
     * @return HTML格式化的文档
     */
    public String htmlReport(LocalDateTime start, LocalDateTime end) {
        return report(true, start, end);
    }

    private Map<String, CollectionAnalysis> getReportValues(List<String> fields, LocalDateTime start, LocalDateTime end) {
        HomenviCollections maxCollections = getMaxByTimeRange(fields, start, end);
        HomenviCollections minCollections = getMinByTimeRange(fields, start, end);
        HomenviCollections meanCollections = getMeanByTimeRange(fields, start, end);
        HomenviCollections spreadCollections = getSpreadByTimeRange(fields, start, end);
        if (isAnyNull(maxCollections, minCollections, meanCollections, spreadCollections)) {
            return null;
        }
        Map<String, Double> maxValues = maxCollections.getValues();
        Map<String, Double> minValues = minCollections.getValues();
        Map<String, Double> meanValues = meanCollections.getValues();
        Map<String, Double> spreadValues = spreadCollections.getValues();
        if (isAnyNull(maxValues, minValues, meanValues, spreadValues)) {
            return null;
        }
        Map<String, CollectionAnalysis> analysisList = new HashMap<>(4);
        fields.forEach(field -> {
            CollectionAnalysis analysis = new CollectionAnalysis();
            analysis.setField(field);
            analysis.setStart(start);
            analysis.setEnd(end);
            analysis.setMax(maxValues.get(field));
            analysis.setMin(minValues.get(field));
            analysis.setMean(meanValues.get(field));
            analysis.setSpread(spreadValues.get(field));
            analysisList.put(field, analysis);
        });
        return analysisList;
    }

    private Boolean isAnyNull(Object... objects) {
        for (Object object : objects) {
            if (null == object) {
                return true;
            }
        }
        return false;
    }

    private Boolean isAnyNull(List<Object> objects) {
        return isAnyNull(objects.toArray());
    }
}
