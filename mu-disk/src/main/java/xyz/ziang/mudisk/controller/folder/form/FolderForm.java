package xyz.ziang.mudisk.controller.folder.form;

import xyz.ziang.mudisk.common.constant.CommonConstant;
import xyz.ziang.mudisk.entity.FileStoreEntity;

import javax.validation.constraints.NotBlank;

public class FolderForm {
    @NotBlank(message = "文件夹名称不能为空")
    private String folderName;

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    /**
     * 创建文件夹对象
     *
     * @return
     */
    public FileStoreEntity createFolder() {
        FileStoreEntity fileStoreEntity = new FileStoreEntity();
        fileStoreEntity.setFileName(this.folderName);
        fileStoreEntity.setFileLocation(null);
        fileStoreEntity.setFileSize("0");
        fileStoreEntity.setFileType(CommonConstant.FOLDER);
        fileStoreEntity.setParentId(0L);
        return fileStoreEntity;
    }

    public void update(FileStoreEntity fileStoreEntity) {
        fileStoreEntity.setFileName(this.folderName);
    }
}
