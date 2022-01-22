package example.starter.hbase.mapper;

import java.util.List;

import example.starter.hbase.dto.TableDescriptorDto;
import org.apache.hadoop.hbase.client.TableDescriptor;

public final class TableDescriptorMapper {
    private TableDescriptorMapper() {
    }

    public static List<TableDescriptorDto> map(final TableDescriptor[] source) {
        return ListMapper.map(source, TableDescriptorMapper::map);
    }

    public static TableDescriptorDto map(final TableDescriptor source) {
        if (source == null) {
            return null;
        }

        TableDescriptorDto result = new TableDescriptorDto();
        result.setTableName(TableNameMapper.map(source.getTableName()));
        return result;
    }
}
