package xyz.ziang.mudisk.controller.folder;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import xyz.ziang.mudisk.common.entity.TreeNode;
import xyz.ziang.mudisk.common.result.ApiResult;
import xyz.ziang.mudisk.controller.folder.form.FolderForm;
import xyz.ziang.mudisk.controller.folder.form.FolderValidator;
import xyz.ziang.mudisk.entity.FileStoreEntity;
import xyz.ziang.mudisk.service.FileStoreService;

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
     * 构建顶级节点树
     */
    @GetMapping("/build/root/tree")
    public ApiResult<List<TreeNode<FileStoreEntity>>> buildRootTreeNode() {
        return ApiResult.success(fileStoreService.buildRootTreeNode());
    }

    /**
     * 通过id构建节点树
     */
    @GetMapping("/build/tree/{id}")
    public ApiResult<List<TreeNode<FileStoreEntity>>> buildRootTreeNode(@PathVariable("id") Long id) {
        FileStoreEntity fileStoreEntity = validator.validateFileStoreExist(id);
        return ApiResult.success(fileStoreService.buildTreeNode(fileStoreEntity.getId()));
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
     * 将文件夹放入回收站 递归删除
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/recovery/{id}")
    public ApiResult<Void> deleteFolderToRecovery(@PathVariable("id") Long id) {
        FileStoreEntity fileStoreEntity = validator.validateFileStoreExist(id);
        fileStoreService.recovery(fileStoreEntity);
        return ApiResult.success();
    }

    /**
     * 删除文件夹 递归删除
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
