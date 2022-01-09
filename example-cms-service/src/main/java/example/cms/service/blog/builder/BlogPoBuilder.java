package example.cms.service.blog.builder;

import example.cms.api.dto.BlogSaveDto;
import example.cms.dao.po.BlogPo;

public class BlogPoBuilder {

    public static BlogPo build(BlogSaveDto input) {
        if (input==null) {
            return null;
        }
        BlogPo result = new BlogPo();
        result.setUserId(input.getUserId());
        result.setTitle(input.getTitle());
        result.setContent(input.getContent());
        return result;
    }
}
