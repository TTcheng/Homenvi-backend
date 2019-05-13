package me.wcc.homenvi.service;

import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Influxdb Service.
 * this class storage Object to influxdb and query from influxdb.
 */
@Service
public class InfluxdbService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InfluxdbService.class);
    private InfluxDB influxDB;

    public InfluxdbService(InfluxDB influxDB) {
        this.influxDB = influxDB;
    }

    /**
     * 批量写入influxdb的数据表
     *
     * @param point InfluxdbPoint
     */
    public void writeToInfluxdb(Point point, String dbName) {
        influxDB.setDatabase(dbName);
        influxDB.write(point);
    }

    /**
     * 批量写入influxdb的数据表
     *
     * @param points InfluxdbPoint
     */
    public void writeToInfluxdb(List<Point> points, String dbName) {

        // Flush every 2000 Points, at least every 1000ms
        //启用批量influxDB.enableBatch(1, 1, TimeUnit.MILLISECONDS);
        BatchPoints batchPoints = BatchPoints
                .database(dbName)
                // .retentionPolicy(retention)
                .consistency(InfluxDB.ConsistencyLevel.ALL)
                .build();
        //get a mapping
        points.forEach(batchPoints::point);
        influxDB.write(batchPoints);//write to influx
    }

    public QueryResult query(String sql, String dbName) {
        LOGGER.debug("Send influx query:{},database:{}", sql, dbName);
        return influxDB.query(new Query(sql, dbName));
    }
}