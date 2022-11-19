package xyz.ziang.mudisk.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.ziang.mudisk.common.mapper.MpBaseMapper;
import xyz.ziang.mudisk.entity.FileStoreEntity;

@Repository
@Mapper
public interface FileStoreMapper extends MpBaseMapper<FileStoreEntity> {

}
