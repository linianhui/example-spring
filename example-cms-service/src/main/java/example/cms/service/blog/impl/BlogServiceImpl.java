package example.cms.service.blog.impl;

import java.util.List;

import example.cms.dao.hbase.BlogDao;
import example.cms.dao.mysql.mapper.BlogMapper;
import example.cms.dao.po.BlogPo;
import example.cms.service.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public BlogPo getById(String id) {
        return blogDao.getById(id);
    }

    @Override
    public List<BlogPo> getByUserId(String userId) {
        return blogDao.getByUserId(userId);
    }

    @Override
    public String save(BlogPo blog) {
        return String.valueOf(blogDao.save(blog));
    }
}
