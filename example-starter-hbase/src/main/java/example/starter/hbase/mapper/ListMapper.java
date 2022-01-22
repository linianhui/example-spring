package example.starter.hbase.mapper;

import java.util.*;
import java.util.function.Function;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

public final class ListMapper {

    private ListMapper() {
    }

    public static <T, R extends Comparable<R>> List<R> map(T[] source, Function<T, R> map) {
        if (ArrayUtils.isEmpty(source)) {
            return new ArrayList<>();
        }

        List<R> result = new ArrayList<>(source.length);
        for (T item : source) {
            R r = map.apply(item);
            if (r != null) {
                result.add(r);
            }
        }

        result.sort(R::compareTo);

        return result;
    }

    public static <T, R extends Comparable<R>> List<R> map(Collection<T> source, Function<T, R> map) {
        if (CollectionUtils.isEmpty(source)) {
            return new ArrayList<>();
        }

        List<R> result = new ArrayList<>(source.size());
        for (T item : source) {
            R r = map.apply(item);
            if (r != null) {
                result.add(r);
            }
        }

        result.sort(R::compareTo);

        return result;
    }
}
