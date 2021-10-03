package example.cms.service.dao;

import java.util.List;

import example.cms.service.po.BlogPo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BlogMapper {
    @Select("SELECT * FROM blog;")
    List<BlogPo> selectAll();

    @Select("SELECT * FROM blog WHERE id=#{id};")
    BlogPo selectById(int id);

    @Insert("INSERT INTO blog(title) VALUES(#{title});")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(BlogPo blog);
}
