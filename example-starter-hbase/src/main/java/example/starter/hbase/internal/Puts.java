package example.starter.hbase.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.client.Put;
import example.starter.hbase.RowPut;

public final class Puts {
    private Puts() {
    }

    public static Put of(RowPut rowPut) {
        final Put result = new Put(rowPut.getKey());
        final byte[] family = rowPut.getFamily();

        for (RowPut.Cell cell : rowPut.getCells()) {
            result.addColumn(family, cell.getQualifier(), cell.getValue());
        }

        return result;
    }

    public static List<Put> of(List<RowPut> rowPuts) {
        final List<Put> result = new ArrayList<>(rowPuts.size());
        for (RowPut rowPut : rowPuts) {
            result.add(of(rowPut));
        }
        return result;
    }
}
