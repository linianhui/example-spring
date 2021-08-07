package example.gateway.blog;

import java.util.List;

import example.cms.BlogClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/blog")
public class BlogController {

    @Autowired
    private BlogClient blogClient;

    @PostMapping()
    public int createBlog(@RequestBody Object blog) {
        return blogClient.createBlog(blog);
    }

    @GetMapping(path = "{blogId}")
    public Object getBlogById(@PathVariable(name = "blogId") int blogId) {
        return blogClient.getBlogById(blogId);
    }

    @GetMapping
    public List<Object> getBlogList() {
        return blogClient.getBlogList();
    }
}
