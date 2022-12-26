package xyz.ziang.mudisk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.ziang.mudisk.entity.Permission;

@Mapper
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
}
