package example.gateway.remote.impl;

import java.util.List;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import example.cms.rpc.api.BlogRpcClient;
import example.cms.rpc.api.dto.BlogRpcDto;
import example.gateway.remote.BlogService;
import example.gateway.remote.cms.BlogHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogHttpClient blogHttpClient;

    @DubboReference
    private BlogRpcClient blogRpcClient;

    @Override
    @SentinelResource(value = "blog_saveBlog", fallback = "createBlogFallback")
    public String save(Object blog) {
        return blogHttpClient.save(blog);
    }

    @Override
    @SentinelResource(
        value = "blog_getById",
        fallback = "getByIdFallback")
    public BlogRpcDto getById(String blogId) {
        return blogRpcClient.getById(blogId);
    }

    @Override
    @SentinelResource(value = "blog_getByUserId")
    public List<BlogRpcDto> getByUserId(String userId) {
        return blogRpcClient.getByUserId(userId);
    }

    public String saveBlogFallback(Object blog, Throwable e) {
        if (e instanceof BlockException) {
            log.warn("createBlog Block param {}", blog, e);
        } else {
            log.warn("createBlog fall param {}", blog, e);
        }
        return null;
    }

    public Object getByIdFallback(String blogId, Throwable e) {
        if (e instanceof BlockException) {
            log.warn("getBlogById Block param {}", blogId, e);
        } else {
            log.warn("getBlogById fall param {}", blogId, e);
        }
        return null;
    }
}
