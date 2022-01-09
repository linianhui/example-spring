package example.starter.hbase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Data;
import org.apache.hadoop.hbase.util.Bytes;


@Data
public class RowGet {
    private byte[] key;
    private byte[] family;
    private Collection<byte[]> qualifiers;

    public static RowGet of(
        final String key,
        final byte[] family,
        final Collection<byte[]> qualifiers) {
        RowGet result = new RowGet();
        result.setKey(Bytes.toBytes(key));
        result.setFamily(family);
        result.setQualifiers(qualifiers);
        return result;
    }

    public static List<RowGet> of(
        final Collection<String> keys,
        final byte[] family,
        final Collection<byte[]> qualifiers) {
        final List<RowGet> result = new ArrayList<>(keys.size());
        for (String key : keys) {
            result.add(of(key, family, qualifiers));
        }
        return result;
    }
}
