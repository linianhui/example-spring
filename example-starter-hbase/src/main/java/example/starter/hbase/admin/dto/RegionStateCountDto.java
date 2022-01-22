package example.starter.hbase.admin.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class RegionStateCountDto implements Serializable {
    private static final long serialVersionUID = 1065506653288246894L;

    private int totalRegionCount;
    private int openedRegionCount;
    private int closedRegionCount;
    private int splitRegionCount;
    private int inTransitionRegionCount;
}
