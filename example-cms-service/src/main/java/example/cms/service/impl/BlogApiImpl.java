package example.cms.service.impl;

import java.util.List;

import example.cms.api.BlogApi;
import example.cms.api.dto.BlogDto;
import example.cms.api.dto.BlogSaveDto;
import example.cms.service.blog.BlogService;
import example.cms.service.blog.builder.BlogDtoBuilder;
import example.cms.service.blog.builder.BlogPoBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BlogApiImpl implements BlogApi {

    @Autowired
    private BlogService blogService;

    @Override
    public BlogDto getById(String blogId) {
        return BlogDtoBuilder.build(blogService.getById(blogId));
    }

    @Override
    public List<BlogDto> getByIds(List<String> blogIdList) {
        return BlogDtoBuilder.build(blogService.getByIds(blogIdList));
    }

    @Override
    public List<BlogDto> getByUserId(String userId) {
        return BlogDtoBuilder.build(blogService.getByUserId(userId));
    }

    @Override
    public String save(BlogSaveDto blog) {
        return blogService.save(BlogPoBuilder.build(blog));
    }
}
