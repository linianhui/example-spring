package example.starter.hbase.internal;

import java.util.ArrayList;
import java.util.List;

import example.starter.hbase.RowGet;
import org.apache.hadoop.hbase.client.Get;

public final class Gets {
    private Gets() {
    }

    public static Get of(final RowGet rowGet) {
        final Get result = new Get(rowGet.getKey());
        final byte[] family = rowGet.getFamily();

        for (byte[] qualifier : rowGet.getQualifiers()) {
            result.addColumn(family, qualifier);
        }

        return result;
    }

    public static List<Get> of(List<RowGet> rowGets) {
        final List<Get> result = new ArrayList<>(rowGets.size());
        for (RowGet rowGet : rowGets) {
            result.add(of(rowGet));
        }
        return result;
    }
}
