package example.starter.hbase.mapper;

import java.util.Collection;
import java.util.List;

import example.starter.hbase.dto.RegionLocationDto;
import org.apache.hadoop.hbase.HRegionLocation;

public final class RegionLocationMapper {
    private RegionLocationMapper() {
    }

    public static List<RegionLocationDto> map(final Collection<HRegionLocation> source) {
        return ListMapper.map(source, RegionLocationMapper::map);
    }

    public static RegionLocationDto map(final HRegionLocation source) {
        if (source == null) {
            return null;
        }

        RegionLocationDto result = new RegionLocationDto();
        result.setRegion(RegionMapper.map(source.getRegion()));
        result.setServerName(ServerNameMapper.map(source.getServerName()));
        return result;
    }
}
