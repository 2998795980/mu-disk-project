package xyz.ziang.mudisk.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;

/**
 * 账号信息过滤器
 */
public class AccountContextFilter extends HttpFilter {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        // 如果存在权限信息的话
        System.out.println("进入请求");
        super.doFilter(request, response, chain);
        System.out.println("请求完成返回");
        // 删除上下文中的内容

    }
}
