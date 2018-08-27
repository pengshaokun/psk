
package com.zhskg.bag.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhskg.bag.entity.Resource;
import com.zhskg.bag.mapper.ResourceMapper;
import com.zhskg.bag.model.ResourceEntry;
import com.zhskg.bag.model.TreeNode;
import com.zhskg.bag.param.ResourceParam;
import com.zhskg.bag.service.ResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
@Service(version = "1.0")
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    private TreeNode getTreeNode(Resource entity) {
        TreeNode node = new TreeNode();
        if (entity != null) {
            node.setId(entity.getResourceId());
            node.setText(entity.getResourceName());
            node.setState(entity.getTotal() > 0 ? "closed" : "open");
            ResourceEntry entry = new ResourceEntry();
            BeanUtils.copyProperties(entity, entry);
            node.setAttributes(entry);
        }
        return node;
    }

    private List<TreeNode> GetChildren(Long parentId, List<Resource> source) {
        List<TreeNode> rslt = new ArrayList<>();
        List<Resource> current = new ArrayList<>();
        source.forEach((entity) -> {
            if (entity.getParentId().equals(parentId)) {
                current.add(entity);
            }
        });
        if (current.size() > 0) {
            current.forEach((entity) -> {
                TreeNode node = new TreeNode();
                List<TreeNode> children = GetChildren(entity.getResourceId(), source);
                if (entity != null) {
                    node.setId(entity.getResourceId());
                    node.setText(entity.getResourceName());
                    node.setState(children.size() > 0 ? "closed" : "open");
                    node.setChecked(entity.getTotal() > 0 ? true : false);
                    node.setChildren(children);
                    ResourceEntry entry = new ResourceEntry();
                    BeanUtils.copyProperties(entity, entry);
                    node.setAttributes(entry);
                }
                rslt.add(node);
            });
        }
        return rslt;
    }

    public Long addAndId(ResourceEntry data) {
        Resource entity = new Resource();
        BeanUtils.copyProperties(data, entity);
        resourceMapper.addAndId(entity);
        return entity.getResourceId();
    }

    public Integer batchAdd(List<ResourceEntry> resourceList) {
        List<Resource> list = new ArrayList<Resource>();
        //resourceList.forEach((data) -> list.add(getEntity(data)));
        Resource entity;
        for (ResourceEntry item : resourceList) {
            entity = new Resource();
            BeanUtils.copyProperties(item, entity);
            list.add(entity);
        }
        return resourceMapper.batchAdd(list);
    }

    public Integer removeById(Long resourceId) {
        Resource resource = resourceMapper.get(resourceId);
        if (resource != null) {
            return resourceMapper.removeByPath(resource.getPath());
        } else {
            return 0;
        }
    }

    public Integer remove(ResourceParam condition) {
        return resourceMapper.remove(condition);
    }

    public Integer realRemoveById(Long resourceId) {
        return resourceMapper.realRemoveById(resourceId);
    }

    public Integer realRemove(ResourceParam condition) {
        return resourceMapper.realRemove(condition);
    }

    public ResourceEntry get(Long resourceId) {
        Resource resource = resourceMapper.get(resourceId);
        ResourceEntry resourceEntry = new ResourceEntry();
        BeanUtils.copyProperties(resource, resourceEntry);
        return resourceEntry;
    }

    public ResourceEntry getFirst(ResourceParam condition) {
        Resource resource = resourceMapper.getFirst(condition);
        ResourceEntry resourceEntry = new ResourceEntry();
        BeanUtils.copyProperties(resource, resourceEntry);
        return resourceEntry;
    }

    public List<ResourceEntry> getList(ResourceParam condition) {
        List<ResourceEntry> list = new ArrayList<ResourceEntry>();
        List<Resource> resourceList = resourceMapper.getList(condition);
        //resourceList.forEach((entity) -> list.add(getEntry(entity)));
        ResourceEntry entry;
        for (Resource item:resourceList) {
            entry = new ResourceEntry();
            BeanUtils.copyProperties(item,entry);
            entry.setState(item.getTotal() > 0 ? "closed" : "open");
            list.add(entry);
        }
        return list;
    }

    public List<ResourceEntry> getPageList(Integer pageIndex, Integer pageSize, ResourceParam condition) {
        pageIndex = (pageIndex - 1) * pageSize;
        List<ResourceEntry> list = new ArrayList<ResourceEntry>();
        List<Resource> resourceList = resourceMapper.getPageList(pageIndex, pageSize, condition);
        //resourceList.forEach((entity) -> list.add(getEntry(entity)));
        ResourceEntry entry;
        for (Resource item:resourceList) {
            entry = new ResourceEntry();
            BeanUtils.copyProperties(item,entry);
            entry.setState(item.getTotal() > 0 ? "closed" : "open");
            list.add(entry);
        }
        return list;
    }

    public List<ResourceEntry> getAsyncTreeList(Long parentId) {
        ResourceParam resourceParam = new ResourceParam();
        resourceParam.setParentId(parentId);
        List<ResourceEntry> list = new ArrayList<>();
        List<Resource> resourceList = resourceMapper.getList(resourceParam);
        //resourceList.forEach((entity) -> list.add(getEntry(entity)));
        ResourceEntry entry;
        for (Resource item:resourceList) {
            entry = new ResourceEntry();
            BeanUtils.copyProperties(item,entry);
            entry.setState(item.getTotal() > 0 ? "closed" : "open");
            list.add(entry);
        }
        return list;
    }

    public List<TreeNode> getAsyncTree(Long parentId) {
        ResourceParam resourceParam = new ResourceParam();
        resourceParam.setParentId(parentId);
        List<TreeNode> list = new ArrayList<>();
        List<Resource> resourceList = resourceMapper.getList(resourceParam);
        resourceList.forEach((entity) -> list.add(getTreeNode(entity)));
        return list;
    }

    public List<TreeNode> getTree() {
        ResourceParam resourceParam = new ResourceParam();
        List<Resource> resources = resourceMapper.getList(resourceParam);
        return GetChildren(0L, resources);
    }

    public Integer getCount(ResourceParam condition) {
        return resourceMapper.getCount(condition);
    }

    public Long save(ResourceEntry resourceData) {
        Resource entity = new Resource();
        BeanUtils.copyProperties(resourceData, entity);
        if (entity.getResourceId() != null && entity.getResourceId().longValue() != 0) {
            resourceMapper.updateById(entity);
        } else {
            resourceMapper.addAndId(entity);
            ArrayList<String> path = new ArrayList<>();
            for (String i : resourceData.getParentPath().split(",")) {
                if (!"".equals(i)) path.add(i);
            }
            path.add(entity.getResourceId().toString());
            resourceMapper.updatePath(String.join(",", String.join(",", path)), entity.getResourceId());
        }
        return entity.getResourceId();
    }

    public Integer update(ResourceEntry resourceData, ResourceParam condition) {
        Resource entity = new Resource();
        BeanUtils.copyProperties(resourceData, entity);
        return resourceMapper.update(entity, condition);
    }
}