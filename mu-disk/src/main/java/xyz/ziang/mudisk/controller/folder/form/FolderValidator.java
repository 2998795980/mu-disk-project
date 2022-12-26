package xyz.ziang.mudisk.controller.folder.form;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import xyz.ziang.mudisk.entity.FileStoreEntity;
import xyz.ziang.mudisk.service.FileStoreService;

/**
 * 文件夹校验器
 */
@Component
public class FolderValidator {

    @Resource
    private FileStoreService fileStoreService;

    /**
     * 校验文件对象是否存在
     * 
     * @param id 对象id
     * @return 文件存储对象
     */
    public FileStoreEntity validateFileStoreExist(Long id) {
        return fileStoreService.findOne(id).orElseThrow(() -> {
            throw new IllegalArgumentException("文件夹不存在");
        });
    }
}
