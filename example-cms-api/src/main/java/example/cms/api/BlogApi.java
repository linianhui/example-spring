package example.cms.api;

import java.util.List;

import example.cms.api.dto.BlogDto;

public interface BlogApi {
    BlogDto get(String blogId);

    List<BlogDto> get(List<String> blogIds);

    List<BlogDto> getAll();
}
