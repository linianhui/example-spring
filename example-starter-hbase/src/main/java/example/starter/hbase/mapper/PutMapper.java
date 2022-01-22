package example.starter.hbase.mapper;

import java.util.ArrayList;
import java.util.List;

import example.starter.hbase.RowPut;
import org.apache.hadoop.hbase.client.Put;

public final class PutMapper {
    private PutMapper() {
    }

    public static Put map(RowPut rowPut) {
        final Put result = new Put(rowPut.getKey());
        final byte[] family = rowPut.getFamily();

        for (RowPut.Cell cell : rowPut.getCells()) {
            result.addColumn(family, cell.getQualifier(), cell.getValue());
        }

        return result;
    }

    public static List<Put> map(List<RowPut> rowPuts) {
        final List<Put> result = new ArrayList<>(rowPuts.size());
        for (RowPut rowPut : rowPuts) {
            result.add(map(rowPut));
        }
        return result;
    }
}
