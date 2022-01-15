package example.starter.hbase;

import java.util.Collection;

import lombok.Data;

@Data
public class RowScan {
    private byte[] startKey;
    private byte[] endKey;
    private byte[] prefixFilter;
    private byte[] family;
    private Collection<byte[]> qualifiers;
}
