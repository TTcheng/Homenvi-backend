package me.wcc.homenvi.entity;

import org.influxdb.dto.Point;

/**
 * 使实体转换成influxDB的Point
 *
 * @author ttchengwang@foxmail.com 2019-01-29 16:29:28
 */
@FunctionalInterface
public interface Pointed {
    // Pointy
    Point toPoint(final String measurement);
}