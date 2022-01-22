package example.starter.hbase.mapper;

import java.util.*;

import com.google.common.collect.Maps;
import example.starter.hbase.RowResult;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

public final class ResultMapper {
    private ResultMapper() {
    }

    public static List<RowResult> map(List<Result> rawResults) {
        if (rawResults == null) {
            return null;
        }

        List<RowResult> result = new ArrayList<>(rawResults.size());
        for (Result rawResult : rawResults) {
            RowResult rowResult = map(rawResult);
            if (rowResult != null) {
                result.add(rowResult);
            }
        }
        return result;
    }

    public static List<RowResult> map(Result[] rawResults) {
        if (rawResults == null) {
            return null;
        }

        List<RowResult> result = new ArrayList<>(rawResults.length);
        for (Result rawResult : rawResults) {
            RowResult rowResult = map(rawResult);
            if (rowResult != null) {
                result.add(rowResult);
            }
        }
        return result;
    }

    public static RowResult map(Result rawResult) {
        if (rawResult == null || rawResult.isEmpty()) {
            return null;
        }

        final RowResult rowResult = new RowResult();
        final String key = Bytes.toString(rawResult.getRow());
        if (key == null) {
            return null;
        }
        rowResult.setKey(key);

        Cell[] rawCells = rawResult.rawCells();
        Map<String, byte[]> cellMap = Maps.newHashMapWithExpectedSize(rawCells.length);
        for (Cell rawCell : rawCells) {
            String qualifier = getQualifier(rawCell);
            byte[] value = getValue(rawCell);
            cellMap.put(qualifier, value);
        }

        rowResult.setCells(cellMap);
        return rowResult;
    }

    private static String getQualifier(Cell cell) {
        byte[] bytes = cell.getQualifierArray();
        int qualifierOffset = cell.getQualifierOffset();
        int qualifierLength = cell.getQualifierLength();
        return Bytes.toString(bytes, qualifierOffset, qualifierLength);
    }

    private static byte[] getValue(Cell cell) {
        int valueLength = cell.getValueLength();
        byte[] result = new byte[valueLength];
        CellUtil.copyValueTo(cell, result, 0);
        return result;
    }
}