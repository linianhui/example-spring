package example.cms.service.blog;

import java.util.List;

import example.cms.dto.BlogDto;

public interface BlogService {
    List<BlogDto> listAll();

    BlogDto getById(int id);

    int save(BlogDto blog);
}
