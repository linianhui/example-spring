package example.cms.dao.hbase;

import java.util.List;

import example.cms.dao.po.BlogPo;

public interface BlogDao {
    BlogPo getById(String id);

    List<BlogPo> getByIds(List<String> ids);

    List<BlogPo> getByUserId(String userId);

    String save(BlogPo blog);
}
