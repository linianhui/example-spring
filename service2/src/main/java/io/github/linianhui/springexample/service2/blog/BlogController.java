package io.github.linianhui.springexample.service2.blog;

import java.util.List;

import io.github.linianhui.springexample.service2.dao.BlogMapper;
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
