package me.wcc.homenvi.utils;

import me.wcc.base.infra.constant.BaseConstants.Symbol;
import me.wcc.homenvi.exception.influx.MeasurementBlankException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chuncheng.wang@hand-china.com 2019-01-29 16:29:28
 */
@SuppressWarnings({"rawtypes", "unused"})
public class InfluxQueryHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(InfluxQueryHelper.class);
    public static final List<String> AllFields = Collections.singletonList("*");
    private static final String DEFAULT_CONDITION = "1=1";
    private static final String TIME = "time";

    private StringBuilder sqlBuilder;
    private String measurement;
    private List<String> fields = new ArrayList<>();
    private final List<String> conditions = new ArrayList<>();
    private final List<String> groups = new ArrayList<>();
    private Boolean descending = false;
    private Integer limits;
    private Integer limitStart;

    private Boolean mean = false;
    private Boolean round = false;

    public InfluxQueryHelper() {
        sqlBuilder = new StringBuilder();
    }

    public static String query(List<String> fields, String measurement, List<String> conditions) {
        return query(fields, measurement, conditions, null, false, false, null, null);
    }

    public static String query(List<String> fields, String measurement, List<String> conditions, List<String> groups,
                               Boolean descending) {
        return query(fields, measurement, conditions, groups, descending, false, null, null);
    }

    public static String query(List<String> fields, String measurement, List<String> conditions, List<String> groups,
                               Boolean descending, Boolean withSemicolon) {
        return query(fields, measurement, conditions, groups, descending, withSemicolon, null, null);
    }

    public static String query(List<String> fields, String measurement, List<String> conditions, List<String> groups,
                               Boolean descending, Boolean withSemicolon, Integer limitStart, Integer limits
    ) {
        InfluxQueryHelper helper = new InfluxQueryHelper()
                .select(fields)
                .from(measurement)
                .where(conditions)
                .groupBy(groups)
                .limit(limitStart, limits);
        if (null != descending && descending) {
            helper.timeDescending();
        }
        if (null != descending && withSemicolon) {
            return helper.buildWithSemicolon();
        }
        return helper.build();
    }

    public InfluxQueryHelper selectAll() {
        this.fields.addAll(AllFields);
        return this;
    }

    public InfluxQueryHelper select(List<String> fields) {
        this.fields.addAll(fields);
        return this;
    }

    public InfluxQueryHelper select(String... fields) {
        return select(Arrays.asList(fields));
    }

    public InfluxQueryHelper mean() {
        this.mean = true;
        return this;
    }

    public InfluxQueryHelper round() {
        this.round = true;
        return this;
    }

    private void buildSelect() {
        if (fields.isEmpty()) {
            fields.addAll(AllFields);
        }
        sqlBuilder.append(Keywords.SELECT);
        sqlBuilder.append(Symbol.SPACE);
        String join = String.join(",", fields);
        sqlBuilder.append(join);
        sqlBuilder.append(Symbol.SPACE);
    }

    public InfluxQueryHelper from(String measurement) {
        this.measurement = measurement;
        return this;
    }

    private void buildFrom() {
        if (StringUtils.isBlank(measurement)) {
            throw new MeasurementBlankException();
        }
        sqlBuilder.append(Keywords.FROM);
        sqlBuilder.append(Symbol.SPACE);
        sqlBuilder.append(measurement.trim());
        sqlBuilder.append(Symbol.SPACE);
    }

    public InfluxQueryHelper where(List<String> conditions) {
        this.conditions.addAll(conditions);
        return this;
    }

    public InfluxQueryHelper where(String... conditions) {
        return where(Arrays.asList(conditions));
    }

    private void buildWhere() {
        sqlBuilder.append(Keywords.WHERE);
        sqlBuilder.append(Symbol.SPACE);
        sqlBuilder.append(DEFAULT_CONDITION);
        sqlBuilder.append(Symbol.SPACE);
        if (!CollectionUtils.isEmpty(conditions)) {
            for (String condition : conditions) {
                sqlBuilder.append(Keywords.AND);
                sqlBuilder.append(Symbol.SPACE);
                sqlBuilder.append(condition);
                sqlBuilder.append(Symbol.SPACE);
            }
        }
    }

    public InfluxQueryHelper groupBy(List<String> groups) {
        if (null == groups) {
            return this;
        }
        this.groups.addAll(groups);
        return this;
    }

    public InfluxQueryHelper groupBy(String... groups) {
        return groupBy(Arrays.asList(groups));
    }

    private void buildGroups() {
        if (CollectionUtils.isEmpty(groups)) {
            return;
        }
        sqlBuilder.append(Keywords.GROUP);
        sqlBuilder.append(Symbol.SPACE);
        sqlBuilder.append(Keywords.BY);
        sqlBuilder.append(Symbol.SPACE);
        String join = String.join(",", groups);
        sqlBuilder.append(join);
        sqlBuilder.append(Symbol.SPACE);
    }

    private void timeDescending() {
        this.descending = true;
    }

    private void buildOrder() {
        if (descending) {
            sqlBuilder.append(Keywords.ORDER);
            sqlBuilder.append(Symbol.SPACE);
            sqlBuilder.append(Keywords.BY);
            sqlBuilder.append(Symbol.SPACE);
            sqlBuilder.append(TIME);
            sqlBuilder.append(Symbol.SPACE);
            sqlBuilder.append(Keywords.DESC);
            sqlBuilder.append(Symbol.SPACE);
        }
    }

    public InfluxQueryHelper limit(Integer limits) {
        this.limits = limits;
        return this;
    }

    public InfluxQueryHelper limit(Integer start, Integer limits) {
        this.limitStart = start;
        this.limits = limits;
        return this;
    }

    private void buildLimit() {
        if (limits == null) {
            return;
        }
        sqlBuilder.append(Keywords.LIMIT);
        sqlBuilder.append(Symbol.SPACE);
        if (null != limitStart) {
            sqlBuilder.append(limitStart);
            sqlBuilder.append(Symbol.COMMA);
        }
        sqlBuilder.append(limits);
        sqlBuilder.append(Symbol.SPACE);
    }

    public String build() {
        if (CollectionUtils.isEmpty(fields)) {
            LOGGER.warn("Querying fields aren't given, all fields in measurement will be selected!");
        }
        if (mean) {
            decorateFields("mean");
        }
        if (round) {
            decorateFields("round");
        }
        buildSelect();
        buildFrom();
        buildWhere();
        buildGroups();
        buildOrder();
        buildLimit();
        return sqlBuilder.toString().trim();
    }

    public String buildWithSemicolon() {
        return build().concat(Symbol.SEMICOLON);
    }

    private void decorateFields(String decorator) {
        fields = fields.stream()
                .map(field -> decorator + Symbol.LEFT_SMALL_BRACE + field + Symbol.RIGHT_SMALL_BRACE)
                .collect(Collectors.toList());
    }

    public static final class Keywords {
        private Keywords() {
        }

        public static final String SELECT = "SELECT";
        public static final String FROM = "FROM";
        public static final String WHERE = "WHERE";
        public static final String ORDER = "ORDER";
        public static final String GROUP = "GROUP";
        public static final String BY = "BY";
        public static final String AND = "AND";
        public static final String LIMIT = "LIMIT";
        public static final String ASC = "ASC";
        public static final String DESC = "DESC";
    }

    public static final class AggregateFunctions {
        private AggregateFunctions() {
        }

        public static final String COUNT = "COUNT";
        public static final String DISTINCT = "DISTINCT";
        /**
         * 积分，与时间坐标围成的面积
         */
        public static final String INTEGRAL = "INTEGRAL";
        /**
         * 均值
         */
        public static final String MEAN = "MEAN";
        /**
         * 中位数
         */
        public static final String MEDIAN = "MEDIAN";
        /**
         * 众数
         */
        public static final String MODE = "MODE";
        /**
         * 极差，最大值减最小值
         */
        public static final String SPREAD = "SPREAD";
        /**
         * 方差
         */
        public static final String STDDEV = "STDDEV";
        public static final String SUM = "SUM";
    }

    public static final class SelectorFunctions {
        private SelectorFunctions() {
        }

        public static final String BOTTOM = "BOTTOM";
        public static final String FIRST = "FIRST";
        public static final String LAST = "LAST";
        public static final String MAX = "MAX";
        public static final String MIN = "MIN";
        /**
         * 最大的n个值 ： TOP(n)
         */
        public static final String TOP = "TOP";
        /**
         * 随机采样: SAMPLE(n)
         */
        public static final String SAMPLE = "SAMPLE";
        /**
         * 百分比 PERCENTILE(field, 100) ,PERCENTILE(field, 5)
         */
        public static final String PERCENTILE = "PERCENTILE";
    }
}