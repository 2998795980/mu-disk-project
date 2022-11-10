package xyz.ziang.mudisk.service.impl;


import org.springframework.stereotype.Service;
import xyz.ziang.mudisk.common.service.impl.MpCrudBaseServiceImpl;
import xyz.ziang.mudisk.entity.FileStoreEntity;
import xyz.ziang.mudisk.mapper.FileStoreMapper;
import xyz.ziang.mudisk.service.FileStoreService;

@Service
public class FileStoreServiceImpl extends MpCrudBaseServiceImpl<FileStoreEntity, FileStoreMapper> implements FileStoreService {
    /**
     * 通过有参构造器传入Mapper对象
     *
     * @param repository
     */
    public FileStoreServiceImpl(FileStoreMapper repository) {
        super(repository);
    }
}
