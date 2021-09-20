package example.gateway.blog;

import java.util.List;

import example.cms.BlogClient;
import example.cms.dto.BlogDto;
import example.cms.rpc.api.BlogRpcClient;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/blog")
public class BlogController {

    @Autowired
    private BlogClient blogClient;

    @DubboReference
    private BlogRpcClient blogRpcClient;

    @PostMapping()
    public int createBlog(@RequestBody Object blog) {
        return blogClient.createBlog(blog);
    }

    @GetMapping(path = "{blogId}")
    public Object getBlogById(@PathVariable(name = "blogId") int blogId) {
        return blogRpcClient.getBlogById(blogId);
    }

    @GetMapping
    public List<BlogDto> getBlogList() {
        return blogRpcClient.getBlogList();
    }
}
