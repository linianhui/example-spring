package example.cms.rpc.blog;

import java.util.List;

import example.cms.api.BlogApi;
import example.cms.rpc.api.BlogRpcClient;
import example.cms.rpc.api.dto.BlogRpcDto;
import example.cms.rpc.builder.BlogRpcDtoBuilder;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class BlogRpcClientImpl implements BlogRpcClient {

    @Autowired
    private BlogApi blogApi;

    @Override
    public BlogRpcDto getById(String blogId) {
        return BlogRpcDtoBuilder.build(blogApi.getById(blogId));
    }

    @Override
    public List<BlogRpcDto> getByUserId(String userId) {
        return BlogRpcDtoBuilder.build(blogApi.getByUserId(userId));
    }
}
