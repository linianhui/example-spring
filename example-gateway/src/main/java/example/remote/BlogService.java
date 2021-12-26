package example.remote;

import java.util.List;

import example.cms.dto.BlogDto;

public interface BlogService {
    int createBlog(Object blog);

    Object getBlogById(int blogId);

    List<BlogDto> getBlogList();
}
