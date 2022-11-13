package xyz.ziang.mudisk.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import xyz.ziang.mudisk.common.constant.CommonConstant;
import xyz.ziang.mudisk.common.entity.TreeNode;
import xyz.ziang.mudisk.common.service.impl.MpCrudBaseServiceImpl;
import xyz.ziang.mudisk.entity.FileStoreEntity;
import xyz.ziang.mudisk.mapper.FileStoreMapper;
import xyz.ziang.mudisk.service.FileStoreService;

@Service
public class FileStoreServiceImpl extends MpCrudBaseServiceImpl<FileStoreEntity, FileStoreMapper>
    implements FileStoreService {
    /**
     * 通过有参构造器传入Mapper对象
     *
     * @param repository mapper 对象
     */
    public FileStoreServiceImpl(FileStoreMapper repository) {
        super(repository);
    }

    @Override
    public List<TreeNode<FileStoreEntity>> buildRootTreeNode() {
        // 先查询parent id 为0的根节点
        // 之后递归查询所有的子节点
        QueryWrapper<FileStoreEntity> queryWrapper = new QueryWrapper<>();
        List<FileStoreEntity> fileStoreEntities = repository.selectList(queryWrapper);
        queryWrapper.eq("parent_id", CommonConstant.TREE_ROOT_ID);
        queryWrapper.eq("state", CommonConstant.ACTIVE_STATE);
        return this.buildRootTreeNode(CommonConstant.TREE_ROOT_ID, fileStoreEntities);
    }

    @Override
    public List<TreeNode<FileStoreEntity>> buildTreeNode(Long id) {
        QueryWrapper<FileStoreEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", id);
        queryWrapper.eq("state", CommonConstant.ACTIVE_STATE);
        List<FileStoreEntity> fileStoreEntities = repository.selectList(queryWrapper);
        return this.buildRootTreeNode(id, fileStoreEntities);
    }

    /**
     * 构建文件对象节点树
     *
     * @param rootId 根节点id
     * @param fileStoreEntities 文件对象集合
     * @return 节点树集合
     */
    private List<TreeNode<FileStoreEntity>> buildRootTreeNode(Long rootId, List<FileStoreEntity> fileStoreEntities) {
        if (CollectionUtils.isEmpty(fileStoreEntities)) {
            return new ArrayList<>();
        }
        // 构建节点数
        List<TreeNode<FileStoreEntity>> treeRootNodes = fileStoreEntities.stream()
            .filter(fileStoreEntity -> rootId.equals(fileStoreEntity.getParentId())).map(fileStoreEntity -> {
                TreeNode<FileStoreEntity> fileStoreEntityTreeNode = new TreeNode<>();
                fileStoreEntityTreeNode.setData(fileStoreEntity);
                fileStoreEntityTreeNode.setFileName(fileStoreEntity.getFileName());
                fileStoreEntityTreeNode.setId(fileStoreEntity.getId());
                return fileStoreEntityTreeNode;
            }).collect(Collectors.toList());
        this.buildTreeNode(treeRootNodes, fileStoreEntities);
        return treeRootNodes;
    }

    /**
     * 构建节点树
     * 
     * @param rootNodeList 根节点
     * @param fileStoreEntities 待构建节点树的资源
     */
    private void buildTreeNode(List<TreeNode<FileStoreEntity>> rootNodeList, List<FileStoreEntity> fileStoreEntities) {
        // 如果根节点为空则不参与 TODO 数据库中不存在序号字段
        if (!CollectionUtils.isEmpty(rootNodeList)) {
            for (TreeNode<FileStoreEntity> rootNode : rootNodeList) {
                List<TreeNode<FileStoreEntity>> childNodeList = fileStoreEntities.stream()
                    .filter(fileStoreEntity -> rootNode.getId().equals(fileStoreEntity.getId()))
                    .map(fileStoreEntity -> {
                        TreeNode<FileStoreEntity> fileStoreEntityTreeNode = new TreeNode<>();
                        fileStoreEntityTreeNode.setData(fileStoreEntity);
                        fileStoreEntityTreeNode.setFileName(fileStoreEntity.getFileName());
                        fileStoreEntityTreeNode.setId(fileStoreEntity.getId());
                        return fileStoreEntityTreeNode;
                    }).collect(Collectors.toList());
                // 设置子节点
                rootNode.setChildNodes(childNodeList);
                // 递归查找子节点
                buildTreeNode(childNodeList, fileStoreEntities);
            }
        }
    }
}
