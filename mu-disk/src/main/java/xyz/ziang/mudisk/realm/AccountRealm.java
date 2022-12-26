package xyz.ziang.mudisk.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xyz.ziang.mudisk.entity.Account;
import xyz.ziang.mudisk.mapper.AccountMapper;

@Component
public class AccountRealm extends CommonRealm {

    @Autowired
    AccountMapper accountMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
        throws AuthenticationException {
        // 获取身份信息（用户名）
        String name = authenticationToken.getPrincipal().toString();
        // 查询用户
        Account account = accountMapper.QueryUserByAccount(name);
        // 非空判断
        if (account != null) {
            // 封装
            return new SimpleAuthenticationInfo(authenticationToken.getPrincipal(),
                authenticationToken.getCredentials(), ByteSource.Util.bytes(account.getSalt()), name);
        }
        return null;
    }
}
