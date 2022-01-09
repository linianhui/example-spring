package example.starter.hbase;

import java.util.ArrayList;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.hadoop.hbase.util.Bytes;

@Data
public class RowPut {
    private byte[] key;
    private byte[] family;
    private Collection<Cell> cells;

    public void addCell(byte[] qualifier, String value) {
        if (value!=null) {
            this.addCell(qualifier, Bytes.toBytes(value));
        } else {
            this.addCell(qualifier, (byte[]) null);
        }
    }

    public void addCell(byte[] qualifier, int value) {
        this.addCell(qualifier, Bytes.toBytes(value));
    }

    public void addCell(byte[] qualifier, Integer value) {
        if (value!=null) {
            this.addCell(qualifier, Bytes.toBytes(value));
        } else {
            this.addCell(qualifier, (byte[]) null);
        }
    }

    public void addCell(byte[] qualifier, long value) {
        this.addCell(qualifier, Bytes.toBytes(value));
    }

    public void addCell(byte[] qualifier, Long value) {
        if (value!=null) {
            this.addCell(qualifier, Bytes.toBytes(value));
        } else {
            this.addCell(qualifier, (byte[]) null);
        }
    }

    public void addCell(byte[] qualifier, double value) {
        this.addCell(qualifier, Bytes.toBytes(value));
    }

    public void addCell(byte[] qualifier, Double value) {
        if (value!=null) {
            this.addCell(qualifier, Bytes.toBytes(value));
        } else {
            this.addCell(qualifier, (byte[]) null);
        }
    }

    public void addCell(byte[] qualifier, byte[] value) {
        this.cells.add(Cell.of(qualifier, value));
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    public static class Cell {
        private byte[] qualifier;
        private byte[] value;
    }

    public static RowPut of(
        final String key,
        final byte[] family,
        final int cellCount) {
        RowPut result = new RowPut();
        result.setKey(Bytes.toBytes(key));
        result.setFamily(family);
        result.setCells(new ArrayList<>(cellCount));
        return result;
    }
}
