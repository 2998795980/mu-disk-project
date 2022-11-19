package xyz.ziang.mudisk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.ziang.mudisk.entity.Account;
import xyz.ziang.mudisk.entity.Role;

import java.util.List;

@Repository
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 通过用户名获取Role
     * @param account 用户名
     * @return Role
     */
    List<Role> QueryRoleByAccount(@Param("account") String account);

    /**
     * 通过手机号获取Account
     * @param phone 手机号
     * @return Account
     */
    Account QueryUserByPhone(@Param("phone") String phone);

    /**
     * 通过邮箱获取Account
     * @param email 邮箱
     * @return Account
     */
    Account QueryUserByEmail(@Param("email") String email);

    /**
     * 通过用户名获取Account
     * @param account 用户名
     * @return Account
     */
    Account QueryUserByAccount(@Param("account") String account);

    /**
     * 通过principal获取Role
     * @param principal 用户身份信息
     * @return List<Role>
     */
    List<String> QueryUserRoleInfo(@Param("principal") String principal);

    List<String> QueryUserPermissionInfo(@Param("principal") String principal);

}
