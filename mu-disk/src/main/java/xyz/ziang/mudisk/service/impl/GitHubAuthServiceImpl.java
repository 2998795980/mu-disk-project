package xyz.ziang.mudisk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.ziang.mudisk.service.GitHubAuthService;

import javax.xml.transform.Source;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class GitHubAuthServiceImpl implements GitHubAuthService {
    /**
     * github的授权地址
     */
    private final static String GITHUB_CODE_URL = "https://github.com/login/oauth/authorize?client_id=%s&redirect_uri=%s&state=%s";

    /**
     * 获取github用户ACCESS_TOKEN地址
     */
    private final static String GITHUB_TOKEN_URL = "https://github.com/login/oauth/access_token?client_id=%s&client_secret=%s&code=%s";

    /**
     * 获取github用户信息
     */
    private final static String GITHUB_USER_URL = "https://api.github.com/user?access_token=%s";

    /**
     * 服务器github的客户端ID
     */
    private final static String CLIENT_ID = "0bd250db8dcd761a59a2";

    /**
     * 应用秘钥
     */
    private final static String CLIENT_SECRETE = "43a81b2c8f6d0b6a6a028533837a66ec91faaf10";

    /**
     * github的回调地址
     */
    private final static String REDIRECT_URL = "http://127.0.0.1:9991/github/callback";

    /**
     *
     */
    private final static String ACCESS_TOKEN_NAME = "access_token";

    /**
     * 该字段的值会回传回来
     */
    private final static String STATE = "true";
    /**
     *  本系统访问github登录页面的URL地址
     */
    static String APP_GITHUB_CODE_URL = String.format(GITHUB_CODE_URL,CLIENT_ID,REDIRECT_URL,STATE);

    @Override
    public String getGithubAuthPath() {
        return APP_GITHUB_CODE_URL;
    }

    @Override
    public String callback(String code) {
        String accessToken = getAccessToken(code);
        return getOpenId(accessToken);
    }
    public String getAccessToken(String code) {
        String url = String.format(GITHUB_TOKEN_URL,CLIENT_ID,CLIENT_SECRETE,code);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.build().encode().toUri();

        String resp = getRestTemplate().getForObject(uri, String.class);
        if (resp.contains(ACCESS_TOKEN_NAME)) {
            Map<String, String> map = getParam(resp);
            String access_token = map.get(ACCESS_TOKEN_NAME);
            return access_token;
        } else {
            throw new RuntimeException(resp);
        }

    }

    public String getOpenId(String accessToken) {
        String url = String.format(GITHUB_USER_URL,accessToken);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.build().encode().toUri();
        String resp = getRestTemplate().getForObject(uri, String.class);
        log.error("getAccessToken resp = "+resp);
        return  resp;
    }


    public RestTemplate getRestTemplate() {// 手动添加
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(120000);
        List<HttpMessageConverter<?>> messageConverters = new LinkedList<>();
        messageConverters.add(new ByteArrayHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        messageConverters.add(new ResourceHttpMessageConverter());
        messageConverters.add(new SourceHttpMessageConverter<Source>());
        messageConverters.add(new AllEncompassingFormHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        RestTemplate restTemplate = new RestTemplate(messageConverters);
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }

    private Map<String, String> getParam(String string) {
        Map<String, String> map = new HashMap();
        String[] kvArray = string.split("&");
        for (int i = 0; i < kvArray.length; i++) {
            String[] kv = kvArray[i].split("=");
            if(kv != null){
                if(kv.length == 1){
                    map.put(kv[0], null);
                }else if(kv.length == 2){
                    map.put(kv[0], kv[1]);
                }
            }
        }
        return map;
    }
}