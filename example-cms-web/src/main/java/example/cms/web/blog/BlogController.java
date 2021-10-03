package example.cms.web.blog;

import java.util.List;

import example.cms.dto.BlogDto;
import example.cms.service.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/blog")
public class BlogController {

    @Autowired
   private BlogService blogService;

    @PostMapping()
    public int save(
        @RequestBody BlogDto blog
    ) {
        return blogService.save(blog);
    }

    @GetMapping(path = "{blogId}")
    public BlogDto getById(
        @PathVariable(name = "blogId") int blogId
    ) {
        return blogService.getById(blogId);
    }

    @GetMapping
    public List<BlogDto> listAll() {
        return blogService.listAll();
    }
}
