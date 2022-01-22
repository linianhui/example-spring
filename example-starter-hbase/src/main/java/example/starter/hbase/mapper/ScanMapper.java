package example.starter.hbase.mapper;

import example.starter.hbase.RowScan;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.PrefixFilter;

public final class ScanMapper {
    private ScanMapper() {
    }

    public static Scan map(final RowScan rowScan) {
        final Scan result = new Scan();
        result.withStartRow(rowScan.getStartKey(), true);
        result.withStopRow(rowScan.getEndKey(), true);
        if (rowScan.getPrefixFilter() != null) {
            result.setFilter(new PrefixFilter(rowScan.getPrefixFilter()));
        }
        final byte[] family = rowScan.getFamily();
        for (byte[] qualifier : rowScan.getQualifiers()) {
            result.addColumn(family, qualifier);
        }

        return result;
    }

}
