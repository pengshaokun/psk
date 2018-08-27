
package com.zhskg.bag.service;

import com.zhskg.bag.model.ResourceEntry;
import com.zhskg.bag.model.TreeNode;
import com.zhskg.bag.param.ResourceParam;

import java.util.List;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
public interface ResourceService {
    /**
     * 添加资源
     * @param resourceEntry 要添加的资源信息
     * @return 添加的资源Id
     */
    Long addAndId(ResourceEntry resourceEntry);

    /**
     * 批量添加资源
     * @param resourceList 要添加的资源信息列表
     * @return 添加的资源数
     */
    Integer batchAdd(List<ResourceEntry> resourceList);

    /**
     * 根据资源Id逻辑删除资源
     * @param resourceId 资源Id
     * @return 逻辑删除的资源数
     */
    Integer removeById(Long resourceId);

    /**
     * 根据查询条件逻辑删除资源
     * @param condition 查询条件
     * @return 逻辑删除符合查询条件的资源数
     */
    Integer remove(ResourceParam condition);

    /**
     * 根据资源Id物理删除资源
     * @param resourceId 资源Id
     * @return 物理删除的资源数
     */
    Integer realRemoveById(Long resourceId);

    /**
     * 根据查询条件物理删除的资源
     * @param condition 查询条件
     * @return 物理删除的符合查询条件的资源数
     */
    Integer realRemove(ResourceParam condition);

    /**
     * 根据资源Id获取资源
     * @param resourceId 资源Id
     * @return 资源信息
     */
    ResourceEntry get(Long resourceId);

    /**
     * 根据查询条件获取第一个资源
     * @param condition 查询条件
     * @return 符合查询条件和排序条件的第一个资源
     */
    ResourceEntry getFirst(ResourceParam condition);

    /**
     * 根据查询条件获取资源列表
     * @param condition 查询条件
     * @return 符合查询条件和排序条件的资源列表
     */
    List<ResourceEntry> getList(ResourceParam condition);

    /**
     * 根据查询条件获取第pageIndex页的资源列表(pageSize条)
     * @param pageIndex 页索引
     * @param pageSize 页大小
     * @param condition 查询条件
     * @return 符合查询条件的第pageIndex页的资源列表(pageSize条)
     */
    List<ResourceEntry> getPageList(Integer pageIndex, Integer pageSize, ResourceParam condition);

    /**
     * 根据应用Id和父级资源Id异步获取下级资源
     * @param parentId 父级菜单Id
     * @return 下级菜单
     */
    List<ResourceEntry> getAsyncTreeList(Long parentId);

    /**
     * 根据应用Id和父级资源Id异步获取下级资源
     * @param parentId 父级资源Id
     * @return 下级资源
     */
    List<TreeNode> getAsyncTree(Long parentId);

    /**
     * 根据应用Id获取其下的所有资源
     * @return 应用Id下所有资源
     */
    List<TreeNode> getTree();

    /**
     * 根据查询条件获取资源数
     * @param condition 查询条件
     * @return 符合查询条件的资源数
     */
    Integer getCount(ResourceParam condition);

    /**
     * 添加或修改资源
     * @param resourceEntry 要添加或修改的资源信息
     * @return 添加或修改的资源Id
     */
    Long save(ResourceEntry resourceEntry);

    /**
     * 根据查询条件更新资源
     * @param resourceEntry 要更新的资源新
     * @param condition 查询条件
     * @return 更新的资源数
     */
    Integer update(ResourceEntry resourceEntry, ResourceParam condition);
}