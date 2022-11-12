package xyz.ziang.mudisk.service;

import java.util.List;

import xyz.ziang.mudisk.common.entity.TreeNode;
import xyz.ziang.mudisk.common.service.MpCrudBaseService;
import xyz.ziang.mudisk.entity.FileStoreEntity;

public interface FileStoreService extends MpCrudBaseService<FileStoreEntity> {

    /**
     * 构建根节点树
     * 
     * @return 节点树
     */
    List<TreeNode<FileStoreEntity>> buildRootTreeNode();

    /**
     * 构建任意节点树
     * 
     * @param id 文件对象id
     * @return 节点树
     */
    List<TreeNode<FileStoreEntity>> buildTreeNode(Long id);
}
