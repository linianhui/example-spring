package example.starter.hbase.admin.dto;

import java.io.Serializable;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class TableNameDto implements Comparable<TableNameDto>, Serializable {
    private static final long serialVersionUID = -1168173193801041233L;

    private String namespace;
    private String name;
    private boolean systemTable;

    @Override
    public int compareTo(TableNameDto o) {
        if (o == null) {
            return -1;
        }

        return StringUtils.compareIgnoreCase(this.namespace + this.name, o.namespace + o.name);
    }
}
