package xyz.ziang.mudisk.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import xyz.ziang.mudisk.entity.FileStoreEntity;

public interface HandlerFileService {

    /**
     * 上传文件
     * 
     * @param file 文件对象
     * @param parentId 父节点id
     * @throws IOException
     */
    void uploadFile(MultipartFile file, Long parentId) throws IOException;

    /**
     * 下载文件
     * 
     * @param fileStoreEntity 文件对象
     */
    void downloadFile(FileStoreEntity fileStoreEntity);
}
