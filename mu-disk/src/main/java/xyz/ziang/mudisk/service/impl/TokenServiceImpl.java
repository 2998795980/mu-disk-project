package xyz.ziang.mudisk.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ziang.mudisk.exception.ServiceException;
import xyz.ziang.mudisk.service.RedisService;
import xyz.ziang.mudisk.service.TokenService;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {
    private static final String TOKEN_NAME = "token";

    @Autowired
    private RedisService redisService;

    @Override
    public String createToken() {
        String token = UUID.randomUUID().toString();
        try {
            redisService.setEx(token, token, 10000L);
            return token;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void checkToken(HttpServletRequest request) throws ServiceException {
        String token = request.getHeader(TOKEN_NAME);
        if (StringUtils.isBlank(token)) {
            // header中不存在token
            token = request.getParameter(TOKEN_NAME);
            if (StringUtils.isBlank(token)) {
                // parameter中也不存在token
                throw new ServiceException("参数不合法，必须带token参数");
            }
        }
        if (!redisService.exists(token)) {
            throw new ServiceException("请勿重复操作");
        }
        boolean remove = redisService.remove(token);
        // 必须再次判断是否移除成功，因为可能多个请求同时执行上面移除的代码，但是最终只有一个返回移除成功的，如果不判断是否移除成功，就会失去幂等性的
        if (!remove) {
            throw new ServiceException("请勿重复操作");
        }
    }
}
