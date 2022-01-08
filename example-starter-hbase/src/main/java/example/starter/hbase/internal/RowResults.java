package example.starter.hbase.internal;

import java.util.Map;

import com.google.common.collect.Maps;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import example.starter.hbase.RowResult;

public final class RowResults {
    private RowResults() {
    }

    public static RowResult[] of(Result[] rawResults) {
        if (rawResults == null) {
            return null;
        }

        int length = rawResults.length;
        RowResult[] result = new RowResult[length];
        for (int i = 0; i < length; i++) {
            result[i] = of(rawResults[i]);
        }
        return result;
    }

    public static RowResult of(Result rawResult) {
        if (rawResult == null) {
            return null;
        }

        final RowResult rowResult = new RowResult();
        final String key = Bytes.toString(rawResult.getRow());
        rowResult.setKey(key);

        Cell[] rawCells = rawResult.rawCells();
        Map<String, byte[]> cellMap = Maps.newHashMapWithExpectedSize(rawCells.length);
        for (Cell rawCell : rawCells) {
            String qualifier = Bytes.toString(rawCell.getQualifierArray());
            byte[] value = rawCell.getValueArray();
            cellMap.put(qualifier, value);
        }

        rowResult.setCells(cellMap);
        return rowResult;
    }
}