package example.cms.rpc.api.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class BlogRpcDto implements Serializable {
    private static final long serialVersionUID = 3066773124221232959L;
    private String id;
    private String userId;
    private String title;
    private String content;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
