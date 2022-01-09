package example.gateway.remote.cms;

import java.util.List;

import example.gateway.remote.cms.config.FeignH2PriorKnowledgeConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
    name = "cms",
    contextId = "blog",
    primary = false,
    url = Constants.URL,
    configuration = FeignH2PriorKnowledgeConfig.class,
    path = "/v1/blog"
)
public interface BlogHttpClient {
    @PostMapping
    String save(@RequestBody Object blog);

    @GetMapping(path = "{blogId}")
    Object getById(@PathVariable(name = "blogId") String blogId);

    @GetMapping()
    List<Object> getByUserId(
        @RequestParam(name = "userId") String userId
    );
}
