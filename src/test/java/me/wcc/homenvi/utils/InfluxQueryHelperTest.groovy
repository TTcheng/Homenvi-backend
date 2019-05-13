package me.wcc.homenvi.utils

import spock.lang.Specification

/**
 *
 * @author chuncheng.wang@hand-china.com 2019-01-29 16:29:28
 */
class InfluxQueryHelperTest extends Specification {
    def measurement = "collections"

    def "test query"() {
        given:
        def fields = Arrays.asList("id", "name")
        def conditions = Arrays.asList("time>now()-1h", "identifier=alpha")
        when:
        def sql = InfluxQueryHelper.query(fields, measurement, conditions)
        then:
        sql == "SELECT id,name FROM collections WHERE 1=1 AND time>now()-1h AND identifier=alpha"
    }

    def "test query1"() {
        given:
        def fields = Arrays.asList("id", "name")
        def conditions = Arrays.asList("time>now()-1h", "identifier=alpha")
        def groups = Arrays.asList("identifier", "time(1h)")
        when:
        def sql = InfluxQueryHelper.query(fields, measurement, conditions, groups, true)
        then:
        sql == "SELECT id,name FROM collections WHERE 1=1 AND time>now()-1h AND identifier=alpha GROUP BY identifier,time(1h) ORDER BY time DESC"
    }

    def "test query2"() {
        given:
        def fields = Arrays.asList("id", "name")
        def conditions = Arrays.asList("time>now()-1h", "identifier=alpha")
        def groups = Arrays.asList("identifier", "time(1h)")
        when:
        def sql = InfluxQueryHelper.query(fields, measurement, conditions, groups, true, true, 9, 10)
        then:
        sql == "SELECT id,name FROM collections WHERE 1=1 AND time>now()-1h AND identifier=alpha GROUP BY identifier,time(1h) ORDER BY time DESC LIMIT 9,10;"
    }

    def "test build"() {
        given:
        def expected = "SELECT * FROM collections WHERE 1=1 LIMIT 9,10;"
        when:
        def sql = new InfluxQueryHelper().selectAll().from(measurement).limit(9, 10).buildWithSemicolon()
        then:
        expected == sql
    }

    def "test build with group"() {
        given:
        def expected = "SELECT mean(humidity) FROM collections WHERE 1=1 GROUP BY time(1h) LIMIT 9,10;"
        when:
        def sql = new InfluxQueryHelper()
                .select("mean(humidity)")
                .from(measurement)
                .groupBy("time(1h)")
                .limit(9, 10)
                .buildWithSemicolon()
        then:
        expected == sql
    }
}
