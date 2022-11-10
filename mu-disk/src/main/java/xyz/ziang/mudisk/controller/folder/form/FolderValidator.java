package xyz.ziang.mudisk.controller.folder.form;

import org.springframework.stereotype.Component;
import xyz.ziang.mudisk.entity.FileStoreEntity;
import xyz.ziang.mudisk.service.FileStoreService;

import javax.annotation.Resource;

/**
 * 文件夹校验器
 */
@Component
public class FolderValidator {

    @Resource
    private FileStoreService fileStoreService;


    public FileStoreEntity validateFileStoreExist(Long id) {
        return fileStoreService.findOne(id).orElseThrow(() -> {
            throw new IllegalArgumentException("文件夹不存在");
        });
    }
}
