package example.cms.service.po;

import java.io.Serializable;

import lombok.Data;

@Data
public class BlogPo implements Serializable {
    private static final long serialVersionUID = 6802598841226088009L;
    private int id;
    private String title;
    private String content;
}
