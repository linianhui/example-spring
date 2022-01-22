package example.starter.hbase.mapper;

import java.util.ArrayList;
import java.util.List;

import example.starter.hbase.RowGet;
import org.apache.hadoop.hbase.client.Get;

public final class GetMapper {
    private GetMapper() {
    }

    public static Get map(final RowGet rowGet) {
        final Get result = new Get(rowGet.getKey());
        final byte[] family = rowGet.getFamily();

        for (byte[] qualifier : rowGet.getQualifiers()) {
            result.addColumn(family, qualifier);
        }

        return result;
    }

    public static List<Get> map(List<RowGet> rowGets) {
        final List<Get> result = new ArrayList<>(rowGets.size());
        for (RowGet rowGet : rowGets) {
            result.add(map(rowGet));
        }
        return result;
    }
}
