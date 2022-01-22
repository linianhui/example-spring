package example.starter.hbase.mapper;

import java.util.Collection;
import java.util.List;

import example.starter.hbase.dto.RegionDto;
import org.apache.hadoop.hbase.client.RegionInfo;
import org.apache.hadoop.hbase.util.Bytes;

public final class RegionMapper {
    private RegionMapper() {
    }

    public static List<RegionDto> map(final Collection<RegionInfo> source) {
        return ListMapper.map(source, RegionMapper::map);
    }

    public static RegionDto map(final RegionInfo source) {
        if (source == null) {
            return null;
        }

        RegionDto result = new RegionDto();
        result.setId(source.getRegionId());
        result.setName(source.getRegionNameAsString());
        result.setMetaRegion(source.isMetaRegion());
        result.setSplit(source.isSplit());
        result.setFirst(source.isFirst());
        result.setLast(source.isLast());
        result.setTableName(TableNameMapper.map(source.getTable()));
        result.setStartKey(Bytes.toString(source.getStartKey()));
        result.setEndKey(Bytes.toString(source.getEndKey()));
        return result;
    }
}
