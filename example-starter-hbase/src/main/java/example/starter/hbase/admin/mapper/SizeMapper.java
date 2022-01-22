package example.starter.hbase.admin.mapper;

import example.starter.hbase.admin.dto.SizeDto;
import org.apache.hadoop.hbase.Size;

public final class SizeMapper {
    private SizeMapper() {
    }

    public static SizeDto map(final Size source) {
        if (source == null) {
            return null;
        }

        SizeDto result = new SizeDto();
        result.setUnit(source.getUnit().getSimpleName());
        result.setValue(source.get());
        return result;
    }
}
