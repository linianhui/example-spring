package example.cms.api;

import java.util.List;

import example.cms.api.dto.BlogDto;
import example.cms.api.dto.BlogSaveDto;

public interface BlogApi {
    BlogDto getById(String blogId);

    List<BlogDto> getByIds(List<String> blogIdList);

    List<BlogDto> getByUserId(String userId);

    String save(BlogSaveDto blog);
}
