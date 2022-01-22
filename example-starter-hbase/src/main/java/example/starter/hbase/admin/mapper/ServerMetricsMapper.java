package example.starter.hbase.admin.mapper;

import java.util.Map;

import example.starter.hbase.admin.dto.ServerMetricsDto;
import example.starter.hbase.admin.dto.ServerNameDto;
import example.starter.hbase.mapper.MapMapper;
import example.starter.hbase.mapper.TimeMapper;
import org.apache.hadoop.hbase.ServerMetrics;
import org.apache.hadoop.hbase.ServerName;

public final class ServerMetricsMapper {
    private ServerMetricsMapper() {
    }

    public static Map<ServerNameDto, ServerMetricsDto> map(
        final Map<ServerName, ServerMetrics> source) {
        return MapMapper.map(source, ServerNameMapper::map, ServerMetricsMapper::map);
    }

    public static ServerMetricsDto map(final ServerMetrics source) {
        if (source == null) {
            return null;
        }

        ServerMetricsDto result = new ServerMetricsDto();
        result.setServerName(ServerNameMapper.map(source.getServerName()));
        result.setVersion(source.getVersion());
        result.setRequestCountPerSecond(source.getRequestCountPerSecond());
        result.setRequestCount(source.getRequestCount());
        result.setInfoServerPort(source.getInfoServerPort());
        result.setReportTimestamp(TimeMapper.map(source.getReportTimestamp()));
        result.setLastReportTimestamp(TimeMapper.map(source.getLastReportTimestamp()));
        result.setCoprocessorNames(source.getCoprocessorNames());
        result.setRegionMetrics(RegionMetricsMapper.map(source.getRegionMetrics()));
        result.setUserMetrics(UserMetricsMapper.map(source.getUserMetrics()));
        result.setUsedHeapSize(SizeMapper.map(source.getUsedHeapSize()));
        result.setMaxHeapSize(SizeMapper.map(source.getMaxHeapSize()));
        return result;
    }

}

