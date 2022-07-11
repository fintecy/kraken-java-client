package org.fintecy.md.kraken.model;

import java.time.Instant;
import java.util.Objects;

public class SystemStatus extends MicroType<String> {

    private final Instant timestamp;

    public SystemStatus(String value, Instant timestamp) {
        super(value);
        this.timestamp = timestamp;
    }

    public String status() {
        return getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SystemStatus that = (SystemStatus) o;
        return Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), timestamp);
    }

    @Override
    public String toString() {
        return "SystemStatus{" +
                "status=" + value +
                ", timestamp=" + timestamp +
                "} ";
    }
}
