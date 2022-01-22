package example.starter.hbase.mapper;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import example.starter.hbase.RowResult;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

public final class ResultMapper {
    private ResultMapper() {
    }

    public static List<RowResult> map(List<Result> source) {
        return ListMapper.map(source, ResultMapper::map, null);
    }

    public static List<RowResult> map(Result[] source) {
        return ListMapper.map(source, ResultMapper::map, null);
    }

    public static RowResult map(Result source) {
        if (source == null || source.isEmpty()) {
            return null;
        }

        final RowResult rowResult = new RowResult();
        final String key = Bytes.toString(source.getRow());
        if (key == null) {
            return null;
        }
        rowResult.setKey(key);

        Cell[] rawCells = source.rawCells();
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