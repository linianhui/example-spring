package example.cms.dao.mysql.po;

import java.io.Serializable;

import lombok.Data;

@Data
public class BlogPo implements Serializable {
    private static final long serialVersionUID = 6802598841226088009L;
    private String id;
    private String userId;
    private String title;
    private String content;
    private long createdAt;
    private long updatedAt;
}
