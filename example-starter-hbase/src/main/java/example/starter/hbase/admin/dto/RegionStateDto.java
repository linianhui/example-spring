package example.starter.hbase.admin.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class RegionStateDto implements Serializable {
    private static final long serialVersionUID = -8705992520011659312L;

    private ServerNameDto serverName;
    private RegionDto region;
    private String state;
    private long stamp;
    private long ritDuration;
}
