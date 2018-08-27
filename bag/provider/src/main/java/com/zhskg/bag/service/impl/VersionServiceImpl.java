package com.zhskg.bag.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhskg.bag.entity.Version;
import com.zhskg.bag.mapper.VersionMapper;
import com.zhskg.bag.model.VersionEntry;
import com.zhskg.bag.param.VersionParam;
import com.zhskg.bag.service.VersionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * 版本接口实现类
 */
@Service(version = "1.0")
public class VersionServiceImpl implements VersionService {
    
    /**
     * 注入dao
     */
    @Autowired
    private VersionMapper versionMapper;

    /**
     * 添加版本
     * @param version 版本
     * @return int
     * @author huchuan
     */
    @Override
    public int add(VersionParam version) {
        Version versionEntity = new Version(); // 存放sql映射字段实体
        BeanUtils.copyProperties(version, versionEntity); // 将形参中接收到的参数拷贝到sql映射实体中

        // 设置系统自动生成的映射实体属性
        versionEntity.setCreatetime(new Date()); // 设置创建时间

        // 执行sql操作
        int flag = versionMapper.add(versionEntity);

        return flag; // 返回sql操作状态
    }

    /**
     * 根据id删除版本
     * @param id 版本id
     * @return int
     */
    @Override
    public int delete(long id) {
        int flag = versionMapper.delete(id);
        return flag;
    }

    /**
     * 根据id修改版本
     * @param version 版本
     * @return int
     */
    @Override
    public int update(VersionParam version) {
        Version versionEntity = new Version(); // 存放sql映射字段实体
        BeanUtils.copyProperties(version, versionEntity); // 将形参中接收到的参数拷贝到sql映射实体中

        // 执行sql操作
        int flag = versionMapper.update(versionEntity);
        return flag;
    }

    /**
     * 根据参数查询版本列表
     * @param versionParam 版本
     * @return List<Version>
     */
    @Override
    public List<VersionParam> query(Integer pageIndex, Integer pageSize, VersionParam versionParam) {
        // 分页默认设置
        if (pageIndex == null || pageIndex <= 0) pageIndex = 1;
        if (pageSize == null || pageSize <= 0) pageSize = 10;
        pageIndex = (pageIndex - 1) * pageSize; // 计算分页起始条数
        List<VersionParam> list = versionMapper.query(pageIndex, pageSize, versionParam);
        return list;
    }

    /**
     * 根据id查看版本详情
     * @param id 版本号id
     * @return List<VersionEntry>
     */
    @Override
    public List<VersionEntry> queryById(long id) {
        return versionMapper.queryById(id);
    }

    /**
     * @param versionParam 版本
     * @return int 版本列表总条目数
     */
    public int queryAllVersionCount(VersionParam versionParam) {
        return versionMapper.queryAllVersionCount(versionParam);
    }
}
