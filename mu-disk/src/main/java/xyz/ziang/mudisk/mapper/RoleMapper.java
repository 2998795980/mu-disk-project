package xyz.ziang.mudisk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.ziang.mudisk.entity.Role;

@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 给用户添加普通用户角色
     */
    void addUserRole(@Param("userId") Long id);

    void addUserAdminRole(@Param("userId") Long id);
}
