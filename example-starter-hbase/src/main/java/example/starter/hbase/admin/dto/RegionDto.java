package example.starter.hbase.admin.dto;

import java.io.Serializable;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class RegionDto implements Comparable<RegionDto>, Serializable {
    private static final long serialVersionUID = 1697578294963263054L;

    private long id;
    private String name;
    private TableNameDto tableName;
    private String startKey;
    private String endKey;
    private boolean metaRegion;
    private boolean split;
    private boolean first;
    private boolean last;

    @Override
    public int compareTo(RegionDto o) {
        if (o == null) {
            return -1;
        }
        return StringUtils.compareIgnoreCase(this.name, o.name);
    }
}
