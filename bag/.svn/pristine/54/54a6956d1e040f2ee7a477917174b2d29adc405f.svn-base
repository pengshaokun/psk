package com.zhskg.bag.service.impl;

import com.alibaba.dubbo.config.annotation.Service;

import com.zhskg.bag.entity.App;
import com.zhskg.bag.entity.Role;
import com.zhskg.bag.mapper.*;
import com.zhskg.bag.model.AppEntry;
import com.zhskg.bag.param.*;
import com.zhskg.bag.service.AppService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by xiangshiquan on 2017/10/9.
 */
@Service(interfaceName = "com.zhskg.bag.service.AppService",version = "1.0")
public class AppServiceImpl implements AppService {

    @Autowired
    private AppMapper appMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AccountRoleMapper accountRoleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    public String addAndId(AppEntry appEntry) {
        String id = UUID.randomUUID().toString().replace("-", "");
        appEntry.setAppId(id);
        App app = new App();
        BeanUtils.copyProperties(appEntry,app);
        appMapper.add(app);
        return id;
    }

    public Integer batchAdd(List<AppEntry> appList) {
        List<App> list = new ArrayList<>();
        App app;
        for (AppEntry item:appList){
            app = new App();
            BeanUtils.copyProperties(item,app);
            list.add(app);
        }
        Integer num = appMapper.batchAdd(list);
        //appList.forEach((data) -> list.add(getEntity(data)));
        return num;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer removeById(String appId) {
        try {
            RoleParam roleParam = new RoleParam();
            //roleParam.setAppId(appId);
            List<Role> roles = roleMapper.getList(roleParam);
            roles.forEach((role) -> {
                AccountRoleParam accountRoleParam = new AccountRoleParam();
                accountRoleParam.setRoleId(role.getRoleId());
                accountRoleMapper.realRemove(accountRoleParam);
                RoleMenuParam roleMenuParam = new RoleMenuParam();
                RoleResourceParam roleResourceParam = new RoleResourceParam();
                roleMenuParam.setRoleId(role.getRoleId());
                roleResourceParam.setRoleId(role.getRoleId());
                roleMenuMapper.realRemove(roleMenuParam);
                roleResourceMapper.realRemove(roleResourceParam);
            });
            roleMapper.remove(roleParam);
            return appMapper.removeById(appId);
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    public Integer remove(AppParam condition) {
        return appMapper.remove(condition);
    }

    public Integer realRemoveById(String appId) {
        return appMapper.realRemoveById(appId);
    }

    public Integer realRemove(AppParam condition) {
        return appMapper.realRemove(condition);
    }

    public AppEntry get(String appId) {
        App entity = appMapper.get(appId);
        AppEntry entry = new AppEntry();
        BeanUtils.copyProperties(entity,entry);
        return entry;
    }

    public AppEntry getFirst(AppParam condition) {
        App entity = appMapper.getFirst(condition);
        AppEntry entry = new AppEntry();
        BeanUtils.copyProperties(entity,entry);
        return entry;
    }

    public List<AppEntry> getList(AppParam condition) {
        List<AppEntry> list = new ArrayList<>();
        List<App> appList = appMapper.getList(condition);
        AppEntry entry;
        for(App item:appList){
           entry = new AppEntry();
           BeanUtils.copyProperties(item,entry);
           list.add(entry);
        }
       // appList.forEach((entity) -> list.add(getEntry(entity)));
        return list;
    }

    public List<AppEntry> getPageList(Integer pageIndex, Integer pageSize, AppParam condition) {
        pageIndex = (pageIndex - 1) * pageSize;
        List<AppEntry> list = new ArrayList<>();
        List<App> appList = appMapper.getPageList(pageIndex, pageSize, condition);
        AppEntry entry;
        for(App item:appList){
            entry = new AppEntry();
            BeanUtils.copyProperties(item,entry);
            list.add(entry);
        }
        //appList.forEach((entity) -> list.add(getEntry(entity)));
        return list;
    }

    public Integer getCount(AppParam condition) {
        return appMapper.getCount(condition);
    }

    public String save(AppEntry appEntry) {
        String id = appEntry.getAppId();
        if (id != null && id != "") {
            App entity = new App();
            BeanUtils.copyProperties(appEntry,entity);
            appMapper.updateById(entity);
            return id;
        } else {
            id = UUID.randomUUID().toString().replace("-", "");
            appEntry.setAppId(id);
            App entity = new App();
            BeanUtils.copyProperties(appEntry,entity);
            appMapper.add(entity);
            return id;
        }
    }

    public Integer update(AppEntry appEntry, AppParam condition) {
        App entity = new App();
        BeanUtils.copyProperties(appEntry,entity);
        Integer num = appMapper.update(entity, condition);
        return num;
    }
}
