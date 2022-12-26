package xyz.ziang.mudisk.service;

import xyz.ziang.mudisk.entity.Account;
import xyz.ziang.mudisk.entity.Role;

public interface RoleService {

    /**
     * 通过Account查找Role
     * @param account
     * @return
     */
    Role queryRoleByAccount(Account account);
}
