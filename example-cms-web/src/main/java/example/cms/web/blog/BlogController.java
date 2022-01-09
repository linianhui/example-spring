package example.cms.web.blog;

import java.util.List;

import example.cms.api.BlogApi;
import example.cms.api.dto.BlogDto;
import example.cms.api.dto.BlogSaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/blog")
public class BlogController {

    @Autowired
    private BlogApi blogApi;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String save(
        @RequestBody BlogSaveDto blog
    ) {
        return blogApi.save(blog);
    }

    @GetMapping(path = "{blogId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BlogDto getById(
        @PathVariable(name = "blogId") String blogId
    ) {
        return blogApi.getById(blogId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BlogDto> getByUserId(
        @RequestParam(name = "userId") String userId
    ) {
        return blogApi.getByUserId(userId);
    }
}
