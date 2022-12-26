package xyz.ziang.mudisk.service;

import xyz.ziang.mudisk.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

/**
 * 使用Token实现幂等性
 */
public interface TokenService {

    // 创建token
    String createToken();
    // 检验token
    void checkToken(HttpServletRequest request) throws ServiceException;
}
