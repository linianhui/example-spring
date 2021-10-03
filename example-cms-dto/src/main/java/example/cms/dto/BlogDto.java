package example.cms.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BlogDto implements Serializable {

    private static final long serialVersionUID = -3940388258933569348L;

    private int id;
    private String title;
    private String content;
}
