package example.remote.impl;

import java.util.List;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import example.cms.BlogClient;
import example.cms.dto.BlogDto;
import example.cms.rpc.api.BlogRpcClient;
import example.remote.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogClient blogClient;

    @DubboReference
    private BlogRpcClient blogRpcClient;

    @Override
    @SentinelResource(value = "blog_createBlog",fallback = "createBlogFallback")
    public int createBlog(Object blog) {
        return blogClient.createBlog(blog);
    }

    @Override
    @SentinelResource(
        value = "blog_getBlogById",
        fallback = "getBlogByIdFallback")
    public Object getBlogById(int blogId) {
        return blogRpcClient.getBlogById(blogId);
    }

    @Override
    @SentinelResource(value = "blog_getBlogList")
    public List<BlogDto> getBlogList() {
        return blogRpcClient.getBlogList();
    }

    public int createBlogFallback(Object blog, Throwable e) {
        if (e instanceof BlockException) {
            log.warn("createBlog Block param {}", blog, e);
        } else {
            log.warn("createBlog fall param {}", blog, e);
        }
        return -1;
    }

    public Object getBlogByIdFallback(int blogId, Throwable e) {
        if (e instanceof BlockException) {
            log.warn("getBlogById Block param {}", blogId, e);
        } else {
            log.warn("getBlogById fall param {}", blogId, e);
        }
        return null;
    }
}
