package example.starter.hbase.mapper;

import java.util.List;

import example.starter.hbase.RowGet;
import org.apache.hadoop.hbase.client.Get;

public final class GetMapper {
    private GetMapper() {
    }

    public static Get map(final RowGet source) {
        final Get result = new Get(source.getKey());
        final byte[] family = source.getFamily();

        for (byte[] qualifier : source.getQualifiers()) {
            result.addColumn(family, qualifier);
        }

        return result;
    }

    public static List<Get> map(List<RowGet> source) {
       return ListMapper.map(source, GetMapper::map, null);
    }
}
