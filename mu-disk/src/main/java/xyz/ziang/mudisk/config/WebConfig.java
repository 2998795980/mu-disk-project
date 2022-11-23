package xyz.ziang.mudisk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import xyz.ziang.mudisk.interceptor.AutoIdempotentInterceptor;

import javax.annotation.Resource;

/**
 * 将拦截器添加到配置类中，使拦截器生效
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Resource
    private AutoIdempotentInterceptor autoIdempotentInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(autoIdempotentInterceptor);
        super.addInterceptors(registry);
    }
}
