package xyz.ziang.mudisk.config;

import java.util.ArrayList;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import xyz.ziang.mudisk.realm.AccountRealm;
import xyz.ziang.mudisk.realm.EmailRealm;
import xyz.ziang.mudisk.realm.PhoneRealm;

@Configuration
@Slf4j
public class ShiroConfig {

    @Autowired
    AccountRealm accountRealm;
    @Autowired
    EmailRealm emailRealm;
    @Autowired
    PhoneRealm phoneRealm;

    /**
     * 添加shiro内置过滤器
     *
     * anon: 无需认证即可访问 authc: 必须认证才能用 user: 必须拥有 “记住我” 功能才能用 perms: 拥有对某个资源的权限才能用 role: 拥有某个角色权限才能访问
     *
     * @return defaultShiroFilterChainDefinition
     */
    @Bean
    public DefaultShiroFilterChainDefinition defaultShiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition filter = new DefaultShiroFilterChainDefinition();
        // 无需认证
        filter.addPathDefinition("/login", "anon");
        filter.addPathDefinition("/register", "anon");
        filter.addPathDefinition("/captcha/base64", "anon");
        filter.addPathDefinition("/captcha/picture", "anon");
        filter.addPathDefinition("/resource/**", "anon");

        // 监听logout过滤器
        filter.addPathDefinition("/logout", "logout");

        // 需要认证
        filter.addPathDefinition("/**", "authc");

        // 添加RememberMe过滤器
        filter.addPathDefinition("/**", "user");

        return filter;
    }

    /**
     * 默认WebSecurityManager 1.设置加密方式 2.添加多个认证方法 3.添加rememberMe功能
     *
     * @return WebSecurityManager
     */
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        // 创建默认webSecurityManager
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        // 使用Hash加密 md5 迭代10次
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(10);
        log.info("create CredentialsMatcher success");
        // 加入hash加密方式
        accountRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        emailRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        phoneRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        log.info("realm add CredentialsMatcher success");
        // 添加自定义Realm（内部含有认证，授权）
        ArrayList<Realm> realms = new ArrayList<>();
        realms.add(accountRealm);
        realms.add(phoneRealm);
        realms.add(emailRealm);
        // 默认策略 只要有一个匹配成功，即登入成功
        // webSecurityManager.setAuthenticator();
        webSecurityManager.setRealms(realms);
        log.info("webSecurityManager add more realm success");
        // 添加rememberMe管理器
        webSecurityManager.setRememberMeManager(rememberMeManager());
        log.info("webSecurityManager add rememberMeManager success");
        log.info("webSecurityManager init success");
        return webSecurityManager;
    }

    /**
     * 创建简易的Cookie
     * 
     * @return Cookie
     */
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie();
        // 设置跨域
        cookie.setDomain("domain");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        // 存活时间
        cookie.setMaxAge(60 * 60 * 24 * 30);
        return cookie;
    }

    /**
     * shiro的Cookie管理对象
     * 
     * @return CookieManager
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCookie(rememberMeCookie());
        // 设置密钥
        manager.setCipherKey("hfajsdhfbasivbae".getBytes());
        return manager;
    }

    @Bean
    public Authorizer authorizer() {
        return new ModularRealmAuthorizer();
    }

}
