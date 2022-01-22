package example.starter.hbase.admin.mapper;

import java.util.Map;

import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Maps;
import example.starter.hbase.admin.dto.ConfigDto;
import org.apache.hadoop.conf.Configuration;

public final class ConfigMapper {
    private ConfigMapper() {
    }

    public static ConfigDto map(final Configuration source) {
        ConfigDto result = new ConfigDto();
        result.setItems(buildMap(source));
        return result;
    }

    private static Map<String, String> buildMap(final Configuration source) {
        if (source == null) {
            return null;
        }

        Map<String, String> result = Maps.newHashMapWithExpectedSize(source.size());
        for (Map.Entry<String, String> entry : source) {
            result.put(entry.getKey(), entry.getValue());
        }
        return ImmutableSortedMap.copyOf(result);
    }
}
