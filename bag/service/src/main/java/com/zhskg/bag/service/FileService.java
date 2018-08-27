package com.zhskg.bag.service;

import com.zhskg.bag.model.FileTempEntry;
import com.zhskg.bag.param.FileTempParam;

import java.util.List;

/**
 * Created by luochaojun on 2017/11/4.
 */
public interface FileService {
    /**
     * 添加文件信息
     * @param data{}
     * @return
     */
    int add(FileTempEntry data);

    /**
     * 批量添加文件
     * @param dataList{}
     * @return
     */
    boolean bathAdd(List<FileTempEntry> dataList);

    /**
     * 清理过期文件
     *
     * @return true:成功 false：失败
     */
    boolean clearOverdueFile();

    /**
     * 获取文件过期列表
     * @return
     * 过期文件列表
     */
    List<FileTempEntry> getOverdueFile();
    /**
     * 修改过期标记
     *
     * @param filePath    文件路径
     * @param overdueFlag 过期标记（0：不过期，1：过期）
     * @return true：成功 false：失败
     */
    boolean updateOverdueFlag(String filePath, Integer overdueFlag);

    /**
     * 修改过期标记
     *
     * @param filePaths   文件路径列表
     * @param overdueFlag 过期标记（0：不过期，1：过期）
     * @return true：成功 false：失败
     */
    boolean updateOverdueFlag(List<String> filePaths, Integer overdueFlag);

    /**
     * 获取文件总数
     *
     * @param param {}
     * @return 文件数量
     */
    int getCount(FileTempParam param);

    /**
     * 获取文件分页列表
     *
     * @param pageIndex 页码
     * @param pageSize  条数
     * @param param{}
     * @return 分页列表
     */
    List<FileTempEntry> getPageList(int pageIndex, int pageSize, FileTempParam param);
}
