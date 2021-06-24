package io.github.linianhui.springexample.service1.blog;

import java.util.List;

import io.github.linianhui.springexample.service2.BlogClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
