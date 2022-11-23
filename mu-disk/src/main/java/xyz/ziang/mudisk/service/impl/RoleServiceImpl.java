package xyz.ziang.mudisk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.ziang.mudisk.entity.Account;
import xyz.ziang.mudisk.entity.Role;
import xyz.ziang.mudisk.mapper.RoleMapper;
import xyz.ziang.mudisk.service.RoleService;

public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public Role queryRoleByAccount(Account account) {
        return null;
    }
}
