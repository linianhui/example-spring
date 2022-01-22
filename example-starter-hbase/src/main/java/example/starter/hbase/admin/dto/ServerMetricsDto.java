package example.starter.hbase.admin.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import lombok.Data;

@Data
public class ServerMetricsDto implements Serializable {
    private static final long serialVersionUID = 8940486845216586345L;

    private ServerNameDto serverName;
    private int infoServerPort;

    private int versionNumber;
    private String version;

    private long reportTimestamp;
    private long lastReportTimestamp;

    private long requestCountPerSecond;
    private long requestCount;

    private SizeDto usedHeapSize;
    private SizeDto maxHeapSize;

    private Set<String> coprocessorNames;

    private Map<String, RegionMetricsDto> regionMetrics;
    private Map<String, UserMetricsDto> userMetrics;
}
