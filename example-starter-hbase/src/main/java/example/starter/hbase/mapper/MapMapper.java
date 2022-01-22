package example.starter.hbase.mapper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.google.common.collect.Maps;

public final class MapMapper {

    private MapMapper() {
    }

    public static <K1, K2, V1> Map<K2, V1> mapKey(
        final Map<K1, V1> source,
        final Function<K1, K2> keyMapper) {
        if (source == null) {
            return new HashMap<>();
        }

        Map<K2, V1> result = Maps.newHashMapWithExpectedSize(source.size());
        for (Map.Entry<K1, V1> entry : source.entrySet()) {
            K2 key = keyMapper.apply(entry.getKey());
            result.put(key, entry.getValue());
        }

        return result;
    }

    public static <K1, V1, V2> Map<K1, V2> mapValue(
        final Map<K1, V1> source,
        final Function<V1, V2> valueMapper) {
        if (source == null) {
            return new HashMap<>();
        }

        Map<K1, V2> result = Maps.newHashMapWithExpectedSize(source.size());
        for (Map.Entry<K1, V1> entry : source.entrySet()) {
            K1 key = entry.getKey();
            V2 value = valueMapper.apply(entry.getValue());
            result.put(key, value);
        }

        return result;
    }

    public static <K1, K2, V1, V2> Map<K2, V2> map(
        final Map<K1, V1> source,
        final Function<K1, K2> keyMapper,
        final Function<V1, V2> valueMapper) {
        if (source == null) {
            return new HashMap<>();
        }

        Map<K2, V2> result = Maps.newHashMapWithExpectedSize(source.size());
        for (Map.Entry<K1, V1> entry : source.entrySet()) {
            K2 key = keyMapper.apply(entry.getKey());
            V2 value = valueMapper.apply(entry.getValue());
            result.put(key, value);
        }

        return result;
    }
}
