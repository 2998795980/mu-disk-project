package xyz.ziang.mudisk.service;

import xyz.ziang.mudisk.entity.Account;

public interface AccountService {
    /**
     * 通过account账号查找Account
     * @param account
     * @return
     */
    Account queryAccountByAccount(String account);
}
