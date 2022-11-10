package xyz.ziang.mudisk.controller.folder;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.ziang.mudisk.common.result.ApiResult;
import xyz.ziang.mudisk.controller.folder.form.FolderForm;
import xyz.ziang.mudisk.controller.folder.form.FolderValidator;
import xyz.ziang.mudisk.entity.FileStoreEntity;
import xyz.ziang.mudisk.service.FileStoreService;

import javax.annotation.Resource;

/**
 * 用户文件夹Controller
 */
@RequestMapping("/folder")
@RestController
public class FolderController {

    @Resource
    private FileStoreService fileStoreService;
    @Resource
    private FolderValidator validator;

    /**
     * builder Folder Tree TODO
     */
    @GetMapping("/build/root/tree")
    public ApiResult<Void> buildRootTreeNode() {
        //  构建根节点树结构
        return ApiResult.success();
    }


    /**
     * 创建文件夹
     *
     * @param folderForm 文件form
     * @return Void
     */
    @PostMapping("")
    public ApiResult<Void> createFolder(@RequestBody @Validated FolderForm folderForm) {
        FileStoreEntity fileStoreEntity = folderForm.createFolder();
        fileStoreService.create(fileStoreEntity);
        return ApiResult.success();
    }


    /**
     * 修改文件夹
     *
     * @param folderForm 文件form
     * @return Void
     */
    @PutMapping("{id}")
    public ApiResult<Void> updateFolder(@PathVariable("id") Long id, @RequestBody @Validated FolderForm folderForm) {
        FileStoreEntity fileStoreEntity = validator.validateFileStoreExist(id);
        folderForm.update(fileStoreEntity);
        fileStoreService.update(fileStoreEntity);
        return ApiResult.success();
    }


    /**
     * 删除文件夹
     *
     * @param id 文件夹id
     * @return Void
     */
    @DeleteMapping("{id}")
    public ApiResult<Void> deleteFolder(@PathVariable("id") Long id) {
        // 校验这个文件是否存在
        fileStoreService.achieve(id);
        return ApiResult.success();
    }
}
