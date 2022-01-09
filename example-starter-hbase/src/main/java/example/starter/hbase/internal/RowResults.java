package example.starter.hbase.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import example.starter.hbase.RowResult;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

public final class RowResults {
    private RowResults() {
    }

    public static List<RowResult> of(List<Result> rawResults) {
        if (rawResults==null) {
            return null;
        }

        List<RowResult> result = new ArrayList<>(rawResults.size());
        for (Result rawResult : rawResults) {
            RowResult rowResult = of(rawResult);
            if (rowResult!=null) {
                result.add(rowResult);
            }
        }
        return result;
    }

    public static List<RowResult> of(Result[] rawResults) {
        if (rawResults==null) {
            return null;
        }

        List<RowResult> result = new ArrayList<>(rawResults.length);
        for (Result rawResult : rawResults) {
            RowResult rowResult = of(rawResult);
            if (rowResult!=null) {
                result.add(rowResult);
            }
        }
        return result;
    }

    public static RowResult of(Result rawResult) {
        if (rawResult==null) {
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