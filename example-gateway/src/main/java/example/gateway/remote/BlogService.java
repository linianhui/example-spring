package example.gateway.remote;

import java.util.List;

import example.cms.rpc.api.dto.BlogRpcDto;

public interface BlogService {
    String save(Object blog);

    BlogRpcDto getById(String blogId);

    List<BlogRpcDto> getByIds(List<String> blogIdList);

    List<BlogRpcDto> getByUserId(String userId);
}
