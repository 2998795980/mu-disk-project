package xyz.ziang.mudisk.controller.folder.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import xyz.ziang.mudisk.common.constant.CommonConstant;
import xyz.ziang.mudisk.entity.FileStoreEntity;

public class FolderForm {
    @NotBlank(message = "文件夹名称不能为空")
    private String folderName;
    @NotNull(message = "节点id不能为空")
    private Long parentId;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

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
        fileStoreEntity.setParentId(parentId);
        fileStoreEntity.setSort(0);
        return fileStoreEntity;
    }

    public void update(FileStoreEntity fileStoreEntity) {
        fileStoreEntity.setFileName(this.folderName);
    }
}
