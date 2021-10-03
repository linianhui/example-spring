package example.cms.rpc.blog;

import java.util.List;

import example.cms.dto.BlogDto;
import example.cms.rpc.api.BlogRpcClient;
import example.cms.service.blog.BlogService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class BlogRpcClientImpl implements BlogRpcClient {

    @Autowired
    private BlogService blogService;

    @Override
    public BlogDto getBlogById(int blogId) {
        return blogService.getById(blogId);
    }

    @Override
    public List<BlogDto> getBlogList() {
        return blogService.listAll();
    }
}
