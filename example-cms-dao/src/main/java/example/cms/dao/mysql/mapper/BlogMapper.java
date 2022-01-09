package example.cms.dao.mysql.mapper;

import java.util.List;

import example.cms.dao.mysql.po.BlogPo;
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

    @Select("SELECT * FROM blog WHERE userId=#{userId};")
    List<BlogPo> selectByUserId(String userId);

    @Insert("INSERT INTO blog(userId,title) VALUES(#{userId},#{title});")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(BlogPo blog);
}
