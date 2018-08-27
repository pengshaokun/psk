
package com.zhskg.bag.service;


import com.zhskg.bag.model.AreaEntry;
import com.zhskg.bag.model.TreeNode;
import com.zhskg.bag.param.AreaParam;

import java.util.List;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
public interface AreaService {
    /**
     * 添加行政区域
     * @param areaEntry 要添加的行政区域信息
     * @return 添加的行政区域Id
     */
    Long addAndId(AreaEntry areaEntry);

    /**
     * 批量添加行政区域
     * @param areaList 要添加的行政区域信息列表
     * @return 添加的行政区域数
     */
    Integer batchAdd(List<AreaEntry> areaList);

    /**
     * 根据行政区域Id逻辑删除行政区域
     * @param areaId 行政区域Id
     * @return 逻辑删除的行政区域数
     */
    Integer removeById(Long areaId);

    /**
     * 根据查询条件逻辑删除行政区域
     * @param condition 查询条件
     * @return 逻辑删除的符合查询条件的行政区域数
     */
    Integer remove(AreaParam condition);

    /**
     * 根据行政区域Id物理删除行政区域
     * @param areaId 行政区域Id
     * @return 物理删除的行政区域数
     */
    Integer realRemoveById(Long areaId);

    /**
     * 根据查询条件物理删除行政区域
     * @param condition 查询条件
     * @return 物理删除的符合查询条件的行政区域数
     */
    Integer realRemove(AreaParam condition);

    /**
     * 根据行政区域Id获取行政区域
     * @param areaId 行政区域Id
     * @return 行政区域信息
     */
    AreaEntry get(Long areaId);

    /**
     * 根据查询条件获取第一个行政区域
     * @param condition 查询条件
     * @return 符合查询条件和排序条件的第一个行政区域
     */
    AreaEntry getFirst(AreaParam condition);

    /**
     * 根据查询条件获取行政区域列表
     * @param condition 查询条件
     * @return 符合查询条件和排序条件的行政区域列表
     */
    List<AreaEntry> getList(AreaParam condition);

    /**
     * 根据查询条件获取第pageIndex页行政区域列表(pageSize条)
     * @param pageIndex 页索引
     * @param pageSize 页大小
     * @param condition 查询条件
     * @return 符合查询条件和排序条件的第pageIndex页行政区域列表(pageSize条)
     */
    List<AreaEntry> getPageList(Integer pageIndex, Integer pageSize, AreaParam condition);

    /**
     * 根据父级行政区域Id异步获取下级行政区域（treeGrid使用）
     * @param parentId 父级行政区域Id
     * @return 下级行政区域
     */
    List<AreaEntry> getAsyncTreeList(Long parentId);

    /**
     * 根据父级行政区域Id异步获取下级行政区域(tree使用)
     * @param parentId 父级行政区域Id
     * @return 下级行政区域
     */
    List<TreeNode> getAsyncTree(Long parentId);

    /**
     * 根据查询条件获取行政区域数
     * @param condition 查询条件
     * @return 符合查询条件的行政区域数
     */
    Integer getCount(AreaParam condition);

    /**
     * 添加或修改行政区域
     * @param areaEntry 要添加或修改的行政区域信息
     * @return 添加或修改的行政区域Id
     */
    Long save(AreaEntry areaEntry);

    /**
     * 根据查询条件更新行政区域
     * @param areaEntry 要更新的行政区域信息
     * @param condition 查询条件
     * @return 更新的行政区域数
     */
    Integer update(AreaEntry areaEntry, AreaParam condition);
}