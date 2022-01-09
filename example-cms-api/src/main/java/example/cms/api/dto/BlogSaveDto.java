package example.cms.api.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BlogSaveDto implements Serializable {
    private static final long serialVersionUID = 5233778954942504187L;
    private String userId;
    private String title;
    private String content;
}
