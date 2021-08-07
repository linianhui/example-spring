package example.cms.blog;

import example.cms.dao.DDLBlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateTableRunner implements ApplicationRunner {

    @Autowired
    DDLBlogMapper ddlBlogMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ddlBlogMapper.createTableIfNotExists();
    }
}
