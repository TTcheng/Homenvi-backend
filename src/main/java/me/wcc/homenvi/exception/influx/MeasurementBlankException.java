package me.wcc.homenvi.exception.influx;

/**
 * @author chuncheng.wang@hand-china.com 2019-01-29 16:29:28
 */
public class MeasurementBlankException extends InfluxQueryException {
    public MeasurementBlankException() {
        super("Measurement must be given!");
    }

    public MeasurementBlankException(String reason, Throwable cause) {
        super(reason, cause);
    }

    public MeasurementBlankException(String reason) {
        super(reason);
    }
}
