package me.wcc.homenvi.exception.influx;

/**
 * @author chuncheng.wang@hand-china.com 2019-01-29 16:29:28
 */
public class InfluxSqlException extends RuntimeException {
    public InfluxSqlException() {
    }

    public InfluxSqlException(String reason, Throwable cause) {
        super(reason, cause);
    }

    public InfluxSqlException(String reason) {
        super(reason);
    }
}
