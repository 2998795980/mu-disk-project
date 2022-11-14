package xyz.ziang.mudisk.controller.file;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import xyz.ziang.mudisk.common.result.ApiResult;
import xyz.ziang.mudisk.controller.folder.form.FolderValidator;
import xyz.ziang.mudisk.entity.FileStoreEntity;
import xyz.ziang.mudisk.service.HandlerFileService;

/**
 * 处理文件Controller
 */
@RequestMapping("handler/file")
@RestController
public class HandlerFileController {

    @Resource
    private HandlerFileService handlerFileService;
    @Resource
    private FolderValidator folderValidator;

    /**
     * 上传文件
     * <p/>
     * 这个接口不适用大文件上传 大文件需要分片上传
     * 
     * @param file 文件
     * @param id 父节点id
     * @return Void
     */
    @PostMapping("/upload/{id}")
    public ApiResult<Void> uploadFile(@RequestPart MultipartFile file, @PathVariable("id") Long id) throws IOException {
        folderValidator.validateFileStoreExist(id);
        handlerFileService.uploadFile(file, id);
        return ApiResult.success();
    }

    /**
     * 上传文件 分片
     * <p/>
     * 这个接口不适用大文件上传 大文件需要分片上传
     *
     * @param file 文件
     * @param id 父节点id
     * @return Void
     */
    @PostMapping("/upload/slice/{id}")
    public ApiResult<Void> uploadFileSlice(@RequestPart MultipartFile file, @PathVariable("id") Long id)
        throws IOException {
        folderValidator.validateFileStoreExist(id);
        handlerFileService.uploadFileSlice(file, id);
        return ApiResult.success();
    }

    /**
     * 下载文件通过id
     * 
     * @param id 文件对象id
     * @return 下载路径
     */
    @PostMapping("/download/{id}")
    public ApiResult<Void> downloadFile(@PathVariable("id") Long id) {
        FileStoreEntity fileStoreEntity = folderValidator.validateFileStoreExist(id);
        handlerFileService.downloadFile(fileStoreEntity);
        return ApiResult.success();
    }
}
