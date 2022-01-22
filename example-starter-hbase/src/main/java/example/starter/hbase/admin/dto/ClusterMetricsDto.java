package example.starter.hbase.admin.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ClusterMetricsDto implements Serializable {
    private static final long serialVersionUID = 5715092316911435421L;

    private String hbaseVersion;
    private String clusterId;
    private int masterInfoPort;
    private Boolean balancerOn;
    private ServerNameDto masterName;
    private List<String> masterCoprocessorNames;
    private List<ServerNameDto> serverNames;
    private List<ServerNameDto> deadServerNames;
    private List<ServerNameDto> backupMasterNames;
    private List<RegionStateDto> inTransitionRegions;
    private Map<TableNameDto, RegionStateCountDto> tableRegionStateCounts;
    private Map<ServerNameDto, ServerMetricsDto> serverMetrics;
}

