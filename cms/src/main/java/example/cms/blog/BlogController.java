package example.cms.blog;

import java.util.List;

import example.cms.dao.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/blog")
public class BlogController {

    @Autowired
    BlogMapper blogMapper;

    @PostMapping()
    public int createBlog(
        @RequestBody Blog blog
    ) {
        return blogMapper.insert(blog);
    }

    @GetMapping(path = "{blogId}")
    public Blog getBlogById(
        @PathVariable(name = "blogId") int blogId
    ) {
        return blogMapper.selectById(blogId);
    }

    @GetMapping
    public List<Blog> getBlogList() {
        return blogMapper.selectAll();
    }
}
