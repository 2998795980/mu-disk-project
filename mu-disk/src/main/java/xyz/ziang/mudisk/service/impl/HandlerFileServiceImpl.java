package xyz.ziang.mudisk.service.impl;

import java.io.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.hutool.core.util.ObjectUtil;
import xyz.ziang.mudisk.common.constant.CommonConstant;
import xyz.ziang.mudisk.common.context.AccountInfoContext;
import xyz.ziang.mudisk.common.context.AccountInfoContextHolder;
import xyz.ziang.mudisk.entity.FileStoreEntity;
import xyz.ziang.mudisk.service.FileStoreService;
import xyz.ziang.mudisk.service.HandlerFileService;

@Service
public class HandlerFileServiceImpl implements HandlerFileService {

    @Resource
    private FileStoreService fileStoreService;

    private static final String absolutePath = "F:\\file";

    @Override
    public void uploadFile(MultipartFile file, Long parentId) throws IOException {
        // 将文件写入到客户的文件夹中
        AccountInfoContext context = AccountInfoContextHolder.getContext();
        if (ObjectUtil.isNull(context)) {
            context = new AccountInfoContext();
            context.setUserName("admin");
            context.setUserId(0L);
        }
        String folderPath = absolutePath + File.separator + context.getUserName();
        File dir = new File(folderPath);
        if (!dir.exists()) {
            boolean isMkdirs = dir.mkdirs();
            // 如果创建失败则直接返回
            if (!isMkdirs) {
                return;
            }
        }
        String realPath = folderPath + File.separator + file.getOriginalFilename();
        FileStoreEntity fileStoreEntity = new FileStoreEntity();
        this.writeFileToLocal(file.getInputStream(), new File(realPath));
        fileStoreEntity.setFileLocation(realPath);
        fileStoreEntity.setParentId(parentId);
        fileStoreEntity.setFileName(file.getName());
        fileStoreEntity.setFileSize(String.valueOf(file.getBytes().length));
        fileStoreEntity.setFileType(CommonConstant.FILE);
        fileStoreService.create(fileStoreEntity);
    }

    /**
     * 下载文件
     * 
     * @param fileStoreEntity 文件对象
     */
    @Override
    public void downloadFile(FileStoreEntity fileStoreEntity) {
        //
    }

    /**
     * write file to local
     * 
     * @param ins 输入流
     * @param file 文件
     */
    private void writeFileToLocal(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
