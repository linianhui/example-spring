package example.cms.dao.po;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.ImmutableList;
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
