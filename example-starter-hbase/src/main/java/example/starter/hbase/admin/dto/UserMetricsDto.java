package example.starter.hbase.admin.dto;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

@Data
public class UserMetricsDto implements Serializable {
    private static final long serialVersionUID = -7871766880791522002L;

    private String name;
    private long requestCount;
    private long readRequestCount;
    private long writeRequestCount;
    private long filteredReadRequestCount;
    private Map<String, ClientMetricsDto> clientMetrics;

    @Data
    public static class ClientMetricsDto implements Serializable {
        private static final long serialVersionUID = 1513821718838050580L;

        private String hostName;
        private long requestCount;
        private long readRequestCount;
        private long writeRequestCount;
        private long filteredReadRequestCount;
    }
}
