package example.cms.service.blog.builder;

import example.cms.dto.BlogDto;
import example.cms.service.po.BlogPo;

public class BlogPoBuilder {

    public static BlogPo toBlogPo(BlogDto input) {
        if (input==null) {
            return null;
        }
        BlogPo result = new BlogPo();
        result.setId(input.getId());
        result.setTitle(input.getTitle());
        result.setContent(input.getContent());
        return result;
    }
}
