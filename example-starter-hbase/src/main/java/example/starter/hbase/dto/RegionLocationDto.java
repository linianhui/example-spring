package example.starter.hbase.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class RegionLocationDto implements Comparable<RegionLocationDto>, Serializable {
    private static final long serialVersionUID = -4873712454369714584L;

    private RegionDto region;
    private ServerNameDto serverName;

    @Override
    public int compareTo(RegionLocationDto o) {
        if (o == null) {
            return -1;
        }
        return this.region.compareTo(o.region);
    }
}
