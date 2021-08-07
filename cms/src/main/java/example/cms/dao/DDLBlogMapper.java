package example.cms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DDLBlogMapper {
    @Update(
        "CREATE TABLE If Not Exists blog (\n" +
            "id int NOT NULL AUTO_INCREMENT,\n" +
            "title varchar(256) NOT NULL,\n" +
            "PRIMARY KEY (id)\n" +
            ")"
    )
    void createTableIfNotExists();

    @Select("SELECT * FROM information_schema.TABLES WHERE TABLE_NAME=blog")
    List<Map> getTableInformation();
}