package example.cms.service.blog.builder;

import java.util.List;
import java.util.stream.Collectors;

import example.cms.dto.BlogDto;
import example.cms.service.po.BlogPo;

public class BlogDtoBuilder {
    public static BlogDto toBlogDto(BlogPo input) {
        if (input==null) {
            return null;
        }
        BlogDto result = new BlogDto();
        result.setId(input.getId());
        result.setTitle(input.getTitle());
        result.setContent(input.getContent());
        return result;
    }

    public static List<BlogDto> toBlogDtoList(List<BlogPo> input) {
        if (input==null) {
            return null;
        }

        return input.stream()
            .map(BlogDtoBuilder::toBlogDto)
            .collect(Collectors.toList());
    }
}
