package xyz.ziang.mudisk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ziang.mudisk.entity.Account;
import xyz.ziang.mudisk.mapper.AccountMapper;
import xyz.ziang.mudisk.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;

    @Override
    public Account queryAccountByAccount(String account) {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("account",account);
        return accountMapper.selectOne(wrapper);
    }
}
