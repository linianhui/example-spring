package example.starter.hbase.admin.mapper;

import java.util.List;

import example.starter.hbase.admin.dto.TableNameDto;
import example.starter.hbase.mapper.ListMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.TableName;

public final class TableNameMapper {
    private TableNameMapper() {
    }

    public static List<TableNameDto> map(final TableName[] source) {
        return ListMapper.map(source, TableNameMapper::map);
    }

    public static TableNameDto map(final TableName source) {
        if (source == null) {
            return null;
        }

        TableNameDto result = new TableNameDto();
        result.setNamespace(source.getNamespaceAsString());
        result.setName(source.getQualifierAsString());
        result.setSystemTable(source.isSystemTable());
        return result;
    }

    public static TableName map(final TableNameDto source) {
        if (source == null) {
            return null;
        }

        return TableName.valueOf(source.getNamespace(), source.getName());
    }

    public static TableNameDto map(final String source) {
        if (source == null) {
            return null;
        }
        TableNameDto result = new TableNameDto();

        if (StringUtils.contains(source, TableName.NAMESPACE_DELIM)) {
            String[] split = StringUtils.split(source, TableName.NAMESPACE_DELIM);
            result.setNamespace(split[0]);
            result.setName(split[1]);
            return result;
        }

        result.setName(source);
        return result;
    }
}
