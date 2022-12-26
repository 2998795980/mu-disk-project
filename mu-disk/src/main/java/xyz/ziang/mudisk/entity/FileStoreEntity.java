package xyz.ziang.mudisk.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import xyz.ziang.mudisk.common.entity.MpBaseEntity;

@TableName("file_store")
public class FileStoreEntity extends MpBaseEntity {
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件大小
     */
    private String fileSize;
    /**
     * 文件真实存放位置
     */
    private String fileLocation;
    /**
     * 父文件id
     */
    private Long parentId;

    /**
     * 文件类型
     */
    private Integer fileType;

    /**
     * 排序
     */
    private Integer sort;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public FileStoreEntity() {}

    public FileStoreEntity(String fileName, String fileSize, String fileLocation, Long parentId, Integer fileType) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileLocation = fileLocation;
        this.parentId = parentId;
        this.fileType = fileType;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "FileStoreEntity{" + "fileName='" + fileName + '\'' + ", fileSize='" + fileSize + '\''
            + ", fileLocation='" + fileLocation + '\'' + ", parentId=" + parentId + ", fileType=" + fileType + ", sort="
            + sort + '}';
    }
}
