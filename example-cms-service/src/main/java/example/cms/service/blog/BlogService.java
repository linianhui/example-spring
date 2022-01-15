package example.cms.service.blog;

import java.util.List;

import example.cms.dao.po.BlogPo;

public interface BlogService {

    BlogPo getById(String id);

    List<BlogPo> getByIds(List<String> ids);

    List<BlogPo> getByUserId(String userId);

    String save(BlogPo blog);
}
