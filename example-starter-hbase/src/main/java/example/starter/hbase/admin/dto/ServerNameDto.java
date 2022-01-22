package example.starter.hbase.admin.dto;

import java.io.Serializable;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class ServerNameDto implements Comparable<ServerNameDto>, Serializable {
    private static final long serialVersionUID = 149191372568568031L;

    private String hostName;
    private int port;
    private long startCode;

    @Override
    public int compareTo(ServerNameDto o) {
        if (o == null) {
            return -1;
        }

        return StringUtils.compareIgnoreCase(this.hostName + this.port, o.hostName + o.port);
    }
}
