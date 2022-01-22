package example.starter.hbase.admin.mapper;

import java.util.Collection;
import java.util.List;

import example.starter.hbase.admin.dto.RegionStateDto;
import example.starter.hbase.mapper.ListMapper;
import org.apache.hadoop.hbase.master.RegionState;

public final class RegionStateMapper {
    private RegionStateMapper() {
    }

    public static List<RegionStateDto> map(final Collection<RegionState> source) {
        return ListMapper.map(source, RegionStateMapper::map, null);
    }

    public static RegionStateDto map(final RegionState source) {
        if (source == null) {
            return null;
        }

        RegionStateDto result = new RegionStateDto();
        result.setServerName(ServerNameMapper.map(source.getServerName()));
        result.setRegion(RegionMapper.map(source.getRegion()));
        result.setStamp(source.getStamp());
        result.setState(source.getState().name());
        result.setRitDuration(source.getRitDuration());
        return result;
    }

}
