package example.cms.service.blog.impl;

import java.util.List;

import example.cms.dao.mysql.mapper.BlogMapper;
import example.cms.dao.po.BlogPo;
import example.cms.service.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public BlogPo getById(String id) {
        return blogMapper.selectById(Integer.parseInt(id));
    }

    @Override
    public List<BlogPo> getByUserId(String userId) {
        return blogMapper.selectByUserId(userId);
    }

    @Override
    public String save(BlogPo blog) {
        return String.valueOf(blogMapper.insert(blog));
    }
}
