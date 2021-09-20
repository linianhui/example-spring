package example.cms.service.blog.impl;

import java.util.List;

import example.cms.dto.BlogDto;
import example.cms.service.blog.BlogService;
import example.cms.service.blog.builder.BlogDtoBuilder;
import example.cms.service.blog.builder.BlogPoBuilder;
import example.cms.service.dao.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<BlogDto> listAll() {
        return BlogDtoBuilder.toBlogDtoList(blogMapper.selectAll());
    }

    @Override
    public BlogDto getById(int id) {
        return BlogDtoBuilder.toBlogDto(blogMapper.selectById(id));
    }

    @Override
    public int save(BlogDto blog) {
        return blogMapper.insert(BlogPoBuilder.toBlogPo(blog));
    }
}
