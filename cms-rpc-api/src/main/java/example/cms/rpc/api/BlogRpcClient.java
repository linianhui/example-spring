package example.cms.rpc.api;

import java.util.List;

import example.cms.dto.BlogDto;

public interface BlogRpcClient {
    BlogDto getBlogById(int blogId);

    List<BlogDto> getBlogList();
}
