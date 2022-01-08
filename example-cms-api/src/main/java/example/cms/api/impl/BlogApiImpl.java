package example.cms.api.impl;

import java.util.List;

import example.cms.api.BlogApi;
import example.cms.api.dto.BlogDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BlogApiImpl implements BlogApi {
    @Override
    public BlogDto get(String blogId) {
        return null;
    }

    @Override
    public List<BlogDto> get(List<String> blogIds) {
        return null;
    }

    @Override
    public List<BlogDto> getAll() {
        return null;
    }
}
