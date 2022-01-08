package example.starter.hbase;

import java.util.Collection;

import lombok.Data;

@Data
public class RowPut {
    private byte[] key;
    private byte[] family;
    private Collection<Cell> cells;

    @Data
    public static class Cell {
        private byte[] qualifier;
        private byte[] value;
    }
}
