package example.starter.hbase.admin.mapper;

import java.util.Map;

import example.starter.hbase.admin.dto.UserMetricsDto;
import example.starter.hbase.mapper.MapMapper;
import org.apache.hadoop.hbase.UserMetrics;
import org.apache.hadoop.hbase.util.Bytes;

public final class UserMetricsMapper {
    private UserMetricsMapper() {
    }

    public static Map<String, UserMetricsDto> map(
        final Map<byte[], UserMetrics> source) {
        return MapMapper.map(source, Bytes::toString, UserMetricsMapper::map);
    }

    public static UserMetricsDto map(final UserMetrics source) {
        if (source == null) {
            return null;
        }

        UserMetricsDto result = new UserMetricsDto();
        result.setName(source.getNameAsString());
        result.setRequestCount(source.getRequestCount());
        result.setReadRequestCount(source.getReadRequestCount());
        result.setWriteRequestCount(source.getWriteRequestCount());
        result.setFilteredReadRequestCount(source.getFilteredReadRequests());
        result.setClientMetrics(mapClient(source.getClientMetrics()));
        return result;
    }

    private static Map<String, UserMetricsDto.ClientMetricsDto> mapClient(
        final Map<String, UserMetrics.ClientMetrics> source) {
        return MapMapper.mapValue(source, UserMetricsMapper::mapClient);
    }

    private static UserMetricsDto.ClientMetricsDto mapClient(final UserMetrics.ClientMetrics source) {
        if (source == null) {
            return null;
        }

        UserMetricsDto.ClientMetricsDto result = new UserMetricsDto.ClientMetricsDto();
        result.setHostName(source.getHostName());
        result.setRequestCount(source.getReadRequestsCount() + source.getWriteRequestsCount());
        result.setReadRequestCount(source.getReadRequestsCount());
        result.setWriteRequestCount(source.getWriteRequestsCount());
        result.setFilteredReadRequestCount(source.getFilteredReadRequestsCount());
        return result;
    }

}

