package example.gateway.blog;

import java.util.List;

import example.cms.rpc.api.dto.BlogRpcDto;
import example.gateway.remote.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping()
    public String createBlog(@RequestBody Object blog) {
        return blogService.save(blog);
    }

    @GetMapping(path = "{blogId}")
    public ResponseEntity<BlogRpcDto> getBlogById(@PathVariable(name = "blogId") String blogId) {
        BlogRpcDto result = blogService.getById(blogId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public List<BlogRpcDto> getBlogList(
        @RequestParam(name = "userId") String userId
    ) {
        return blogService.getByUserId(userId);
    }

    @GetMapping(path = "batch")
    public List<BlogRpcDto> getBlogList(
        @RequestParam(name = "blogId") List<String> blogIds
    ) {
        return blogService.getByIds(blogIds);
    }
}
