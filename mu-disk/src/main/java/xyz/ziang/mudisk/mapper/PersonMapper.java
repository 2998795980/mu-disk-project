package xyz.ziang.mudisk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import xyz.ziang.mudisk.entity.Person;

@Repository
public interface PersonMapper extends BaseMapper<Person> {
}
