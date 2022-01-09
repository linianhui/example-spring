package example.cms.rpc.builder;

import java.util.List;
import java.util.stream.Collectors;

import example.cms.api.dto.BlogDto;
import example.cms.rpc.api.dto.BlogRpcDto;

public final class BlogRpcDtoBuilder {
    public static BlogRpcDto build(BlogDto input) {
        BlogRpcDto result = new BlogRpcDto();
        result.setId(input.getId());
        result.setUserId(input.getUserId());
        result.setTitle(input.getTitle());
        result.setContent(input.getContent());
        result.setCreatedAt(input.getCreatedAt());
        result.setUpdatedAt(input.getUpdatedAt());
        return result;
    }

    public static List<BlogRpcDto> build(List<BlogDto> blogDtos) {
        return blogDtos.stream()
            .map(BlogRpcDtoBuilder::build)
            .collect(Collectors.toList());
    }
}
