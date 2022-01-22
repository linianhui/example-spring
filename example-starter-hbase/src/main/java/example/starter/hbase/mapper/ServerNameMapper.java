package example.starter.hbase.mapper;

import java.util.Collection;
import java.util.List;

import example.starter.hbase.dto.ServerNameDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.ServerName;
import org.apache.hadoop.hbase.net.Address;

public final class ServerNameMapper {
    private ServerNameMapper() {
    }

    public static List<ServerNameDto> map(final Collection<ServerName> source) {
        return ListMapper.map(source, ServerNameMapper::map);
    }

    public static ServerNameDto map(final ServerName source) {
        if (source == null) {
            return null;
        }

        ServerNameDto result = new ServerNameDto();
        Address address = source.getAddress();
        result.setHostName(address.getHostname());
        result.setPort(address.getPort());
        result.setStartCode(source.getStartcode());
        return result;
    }

    public static ServerNameDto map(final String source) {
        if (source == null) {
            return null;
        }
        ServerNameDto result = new ServerNameDto();

        if (StringUtils.contains(source, ":")) {
            String[] split = source.split(":");
            result.setHostName(split[0]);
            result.setPort(Integer.parseInt(split[1]));
            return result;
        }

        result.setHostName(source);
        result.setPort(16020);
        return result;
    }

    public static ServerName map(final ServerNameDto source) {
        if (source == null) {
            return null;
        }

        return ServerName.valueOf(source.getHostName(), source.getPort(), source.getStartCode());
    }
}
