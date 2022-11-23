package xyz.ziang.mudisk.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.ziang.mudisk.mapper.AccountMapper;

import java.util.List;

public class CommonRealm extends AuthorizingRealm {

    @Autowired
    AccountMapper accountMapper;

    /**
     * 授权 获取Role，Permission
     * @param principalCollection 用户信息
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取用户唯一身份信息
        String principal = principalCollection.getPrimaryPrincipal().toString();
        List<String> roles = accountMapper.QueryUserRoleInfo(principal);
        List<String> permissions = accountMapper.QueryUserPermissionInfo(principal);
        // 创建AuthorizationInfo对象，存储用户权限和角色
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }

    /**
     * 自定义认证
     * @param authenticationToken 用户信息
     * @return AuthenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        return null;
    }
}
