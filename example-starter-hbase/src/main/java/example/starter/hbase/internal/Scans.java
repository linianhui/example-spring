package example.starter.hbase.internal;

import example.starter.hbase.RowScan;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.ColumnPrefixFilter;

public final class Scans {
    private Scans() {
    }

    public static Scan of(final RowScan rowScan) {
        final Scan result = new Scan();
        result.withStartRow(rowScan.getStartKey(), true);
        result.withStopRow(rowScan.getEndKey(), true);
        result.setFilter(new ColumnPrefixFilter(rowScan.getFilterPrefix()));
        final byte[] family = rowScan.getFamily();
        for (byte[] qualifier : rowScan.getQualifiers()) {
            result.addColumn(family, qualifier);
        }

        return result;
    }

}
