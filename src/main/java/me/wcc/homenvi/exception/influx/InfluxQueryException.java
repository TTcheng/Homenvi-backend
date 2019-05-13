package me.wcc.homenvi.exception.influx;

/**
 * @author chuncheng.wang@hand-china.com 2019-01-29 16:29:28
 */
public class InfluxQueryException extends InfluxSqlException{
    public InfluxQueryException() {
    }

    public InfluxQueryException(String reason, Throwable cause) {
        super(reason, cause);
    }

    public InfluxQueryException(String reason) {
        super(reason);
    }
}
