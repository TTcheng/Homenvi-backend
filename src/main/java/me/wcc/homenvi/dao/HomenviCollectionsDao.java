package me.wcc.homenvi.dao;

import me.wcc.homenvi.entity.HomenviCollections;
import me.wcc.homenvi.service.InfluxdbService;
import org.influxdb.dto.Point;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chuncheng.wang@hand-china.com 2019-01-29 16:29:28
 */
@SuppressWarnings("unused")
@Service
public class HomenviCollectionsDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomenviCollectionsDao.class);
    private final InfluxdbService influxdbService;

    private String dbName;
    private String measurement;

    public HomenviCollectionsDao(InfluxdbService influxdbService) {
        this.influxdbService = influxdbService;
    }

    public void writeToDatabase(HomenviCollections[] collections) {
        List<Point> points = new ArrayList<>(collections.length);
        for (HomenviCollections data : collections) {
            points.add(data.toPoint(measurement));
        }
        influxdbService.writeToInfluxdb(points, dbName);
    }

    public void writeToDatabase(List<HomenviCollections> collectionsList) {
        List<Point> points = collectionsList.stream()
                .map(homenviCollections -> homenviCollections.toPoint(measurement))
                .collect(Collectors.toList());
        influxdbService.writeToInfluxdb(points, dbName);
    }

    public void writeToDatabase(HomenviCollections data) {
        influxdbService.writeToInfluxdb(data.toPoint(measurement), dbName);
    }

    public List<HomenviCollections> queryForList(String sql) {
        return processQueryResults(influxdbService.query(sql, dbName));
    }

    /**
     * 处理查询结果,将结果转化成HomenviCollections的列表
     *
     * @param queryResult 查询结果
     * @return HomenviCollections list.
     */
    private List<HomenviCollections> processQueryResults(QueryResult queryResult) {
        if (queryResult.getResults().isEmpty()) {
            LOGGER.warn("查询结果为空");
            return Collections.emptyList();
        }
        List<HomenviCollections> homenviCollectionsList = new LinkedList<>();
        List<QueryResult.Result> results = queryResult.getResults();
        results.forEach(result -> {
            List<QueryResult.Series> series = result.getSeries();
            if (!CollectionUtils.isEmpty(series)) {
                series.forEach(aSeries -> {
                    List<String> columns = aSeries.getColumns();
                    List<List<Object>> aSeriesValues = aSeries.getValues();
                    homenviCollectionsList.addAll(getQueryData(columns, aSeriesValues));
                });
            }
        });
        return homenviCollectionsList;
    }

    @Value("${homenvi.influx-database}")
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    @Value("${homenvi.influx-measurement}")
    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    /***整理列名、行数据***/
    private List<HomenviCollections> getQueryData(List<String> columns, List<List<Object>> values) {
        List<HomenviCollections> lists = new ArrayList<>();
        for (List<Object> list : values) {
            HomenviCollections collections = new HomenviCollections();
            String timeStr = (String) list.get(0);
            collections.setTime(Instant.parse(timeStr));
            Map<String, Double> seriesData = new HashMap<>();
            for (int i = 1; i < list.size(); i++) {
                if (HomenviCollections.FIELD_IDENTIFIER.equals(columns.get(i))) {
                    collections.setIdentifier((String) list.get(i));
                    continue;
                }
                String propertyName = columns.get(i);//字段名
                Double value = (Double) list.get(i);//相应字段值
                seriesData.put(propertyName, value);
            }
            collections.setValues(seriesData);
            lists.add(collections);
        }
        return lists;
    }
}
