package example.starter.hbase.admin.mapper;


import java.util.Map;

import example.starter.hbase.admin.dto.RegionStateCountDto;
import example.starter.hbase.admin.dto.TableNameDto;
import example.starter.hbase.mapper.MapMapper;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.RegionStatesCount;

public final class RegionStateCountMapper {
    private RegionStateCountMapper() {
    }

    public static Map<TableNameDto, RegionStateCountDto> map(
        final Map<TableName, RegionStatesCount> source) {
        return MapMapper.map(source, TableNameMapper::map, RegionStateCountMapper::map);
    }

    public static RegionStateCountDto map(final RegionStatesCount source) {
        if (source == null) {
            return null;
        }

        RegionStateCountDto result = new RegionStateCountDto();
        result.setTotalRegionCount(source.getTotalRegions());
        result.setOpenedRegionCount(source.getOpenRegions());
        result.setClosedRegionCount(source.getClosedRegions());
        result.setInTransitionRegionCount(source.getRegionsInTransition());
        result.setSplitRegionCount(source.getSplitRegions());
        return result;
    }

}
