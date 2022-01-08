package example.starter.hbase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Data;


@Data
public class RowGet {
    private byte[] key;
    private byte[] family;
    private Collection<byte[]> qualifiers;

    public static RowGet of(
        final byte[] key,
        final byte[] family,
        final Collection<byte[]> qualifiers) {
        RowGet result = new RowGet();
        result.setKey(key);
        result.setFamily(family);
        result.setQualifiers(qualifiers);
        return result;
    }

    public static List<RowGet> of(
        final Collection<byte[]> keys,
        final byte[] family,
        final Collection<byte[]> qualifiers) {
        final List<RowGet> result = new ArrayList<>(keys.size());
        for (byte[] key : keys) {
            result.add(of(key, family, qualifiers));
        }
        return result;
    }
}
