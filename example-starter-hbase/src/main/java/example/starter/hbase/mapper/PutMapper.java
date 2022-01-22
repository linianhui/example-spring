package example.starter.hbase.mapper;

import java.util.List;

import example.starter.hbase.RowPut;
import org.apache.hadoop.hbase.client.Put;

public final class PutMapper {
    private PutMapper() {
    }

    public static Put map(RowPut source) {
        final Put result = new Put(source.getKey());
        final byte[] family = source.getFamily();

        for (RowPut.Cell cell : source.getCells()) {
            result.addColumn(family, cell.getQualifier(), cell.getValue());
        }

        return result;
    }

    public static List<Put> map(List<RowPut> source) {
        return ListMapper.map(source, PutMapper::map, null);
    }
}
