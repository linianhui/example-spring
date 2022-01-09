package example.cms.service.blog.builder;

import java.util.List;
import java.util.stream.Collectors;

import example.cms.api.dto.BlogDto;
import example.cms.dao.po.BlogPo;
import example.util.OffsetDateTimes;

public class BlogDtoBuilder {
    public static BlogDto build(BlogPo input) {
        if (input==null) {
            return null;
        }
        BlogDto result = new BlogDto();
        result.setId(input.getId());
        result.setUserId(input.getUserId());
        result.setTitle(input.getTitle());
        result.setContent(input.getContent());
        result.setCreatedAt(OffsetDateTimes.utc(input.getCreatedAt()));
        result.setUpdatedAt(OffsetDateTimes.utc(input.getUpdatedAt()));
        return result;
    }

    public static List<BlogDto> build(List<BlogPo> input) {
        if (input==null) {
            return null;
        }

        return input.stream()
            .map(BlogDtoBuilder::build)
            .collect(Collectors.toList());
    }
}
