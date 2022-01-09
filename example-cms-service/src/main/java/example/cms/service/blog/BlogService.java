package example.cms.service.blog;

import java.util.List;

import example.cms.dao.mysql.po.BlogPo;

public interface BlogService {

    BlogPo getById(String id);

    List<BlogPo> getByUserId(String userId);

    String save(BlogPo blog);
}
