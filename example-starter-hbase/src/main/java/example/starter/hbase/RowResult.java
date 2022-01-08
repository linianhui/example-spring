package example.starter.hbase;

import java.util.Map;

import lombok.Data;
import org.apache.hadoop.hbase.util.Bytes;

@Data
public class RowResult {
    private String key;
    private Map<String, byte[]> cells;

    public String getString(String qualifier, String defaultValue) {
        byte[] bytes = getBytes(qualifier);
        if (bytes != null) {
            return Bytes.toString(bytes);
        }
        return defaultValue;
    }

    public int getInt(String qualifier, int defaultValue) {
        byte[] bytes = getBytes(qualifier);
        if (bytes != null) {
            return Bytes.toInt(bytes);
        }
        return defaultValue;
    }

    public Integer getInteger(String qualifier, Integer defaultValue) {
        byte[] bytes = getBytes(qualifier);
        if (bytes != null) {
            return Bytes.toInt(bytes);
        }
        return defaultValue;
    }

    public long getLong(String qualifier, long defaultValue) {
        byte[] bytes = getBytes(qualifier);
        if (bytes != null) {
            return Bytes.toLong(bytes);
        }
        return defaultValue;
    }

    public Long getLong(String qualifier, Long defaultValue) {
        byte[] bytes = getBytes(qualifier);
        if (bytes != null) {
            return Bytes.toLong(bytes);
        }
        return defaultValue;
    }

    public float getFloat(String qualifier, float defaultValue) {
        byte[] bytes = getBytes(qualifier);
        if (bytes != null) {
            return Bytes.toFloat(bytes);
        }
        return defaultValue;
    }

    public Float getFloat(String qualifier, Float defaultValue) {
        byte[] bytes = getBytes(qualifier);
        if (bytes != null) {
            return Bytes.toFloat(bytes);
        }
        return defaultValue;
    }

    public double getLong(String qualifier, double defaultValue) {
        byte[] bytes = getBytes(qualifier);
        if (bytes != null) {
            return Bytes.toDouble(bytes);
        }
        return defaultValue;
    }

    public Double getLong(String qualifier, Double defaultValue) {
        byte[] bytes = getBytes(qualifier);
        if (bytes != null) {
            return Bytes.toDouble(bytes);
        }
        return defaultValue;
    }

    public byte[] getBytes(String qualifier) {
        if (cells == null) {
            return null;
        }

        return cells.get(qualifier);
    }
}
