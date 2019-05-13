package me.wcc.homenvi.entity;

import org.influxdb.dto.Point;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class HomenviCollections implements Pointed, Serializable {
    public static final String DEFAULT_IDENTIFIER = "HomenviCollectorAlpha";
    public static final String MEASUREMENT = "collections";

    public static final String FIELD_IDENTIFIER = "identifier";
    public static final String FIELD_CELSIUS = "celsius";
    public static final String FIELD_HUMIDITY = "humidity";
    public static final String FIELD_GAS = "gasValue";
    public static final String FIELD_FAHRENHEIT = "fahrenheit";
    public static final String FIELD_HEAT_INDEX_CELSIUS = "heatIndexCelsius";
    public static final String FIELD_HEAT_INDEX_FAHRENHEIT = "heatIndexFahrenheit";
    public static final String FIELD_BRIGHTNESS = "brightness";
    public static final String FIELD_DUST_DENSITY = "dustDensity";
    public static final String FIELD_SOUND = "sound";

    public static final List<String> FIELDS = Arrays.asList(
            FIELD_BRIGHTNESS,
            FIELD_HUMIDITY,
            FIELD_FAHRENHEIT,
            FIELD_HEAT_INDEX_CELSIUS,
            FIELD_CELSIUS,
            FIELD_HEAT_INDEX_FAHRENHEIT,
            FIELD_DUST_DENSITY,
            FIELD_GAS,
            FIELD_SOUND);

    public static final List<String> VITAL_FIELDS = Arrays.asList(
            FIELD_BRIGHTNESS,
            FIELD_HUMIDITY,
            FIELD_FAHRENHEIT,
            FIELD_CELSIUS,
            FIELD_DUST_DENSITY,
            FIELD_GAS,
            FIELD_SOUND
    );

    @NotNull
    private Instant time;
    @NotEmpty
    private String identifier;
    private Map<String, Double> values;


    public HomenviCollections() {
        identifier = DEFAULT_IDENTIFIER;
    }

    public HomenviCollections(@NotNull Instant time, @NotEmpty String identifier) {
        this.time = time;
        this.identifier = identifier;
    }

    public HomenviCollections(@NotNull Instant time, @NotEmpty String identifier, Map<String, Double> values) {
        this.time = time;
        this.identifier = identifier;
        this.values = values;
    }
    //getter and setter

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Map<String, Double> getValues() {
        return values;
    }

    public void setValues(Map<String, Double> values) {
        this.values = values;
    }

    @Override
    public int hashCode() {
        return identifier.hashCode() & time.hashCode() & values.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HomenviCollections) {
            HomenviCollections target = (HomenviCollections) obj;
            return Objects.equals(identifier, target.getIdentifier())
                    && Objects.equals(values, target.getValues())
                    && Objects.equals(time, target.getTime());
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HomenviCollections{");
        sb.append("time=").append(time);
        sb.append(", identifier='").append(identifier).append('\'');
        values.forEach((key, value) -> {
            sb.append(", ");
            sb.append(key);
            sb.append("=");
            sb.append(value);
        });
        sb.append('}');
        return sb.toString();
    }

    @Override
    public Point toPoint(final String measurement) {
        Point.Builder pointBuilder = Point.measurement(measurement);
        pointBuilder.time(time.toEpochMilli(), TimeUnit.MILLISECONDS);
        pointBuilder.addField(FIELD_IDENTIFIER, identifier);
        values.forEach(pointBuilder::addField);
        return pointBuilder.build();
    }
}