
package com.zhskg.bag.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhskg.bag.entity.Area;
import com.zhskg.bag.mapper.AreaMapper;
import com.zhskg.bag.model.AreaEntry;
import com.zhskg.bag.model.TreeNode;
import com.zhskg.bag.param.AreaParam;
import com.zhskg.bag.service.AreaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
@Service(version = "1.0")
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaMapper areaMapper;

    private TreeNode getTreeNode(Area entity) {
        TreeNode node = new TreeNode();
        if (entity != null) {
            node.setId(entity.getAreaId());
            node.setText(entity.getAreaName());
            node.setState(entity.getTotal() > 0 ? "closed" : "open");
            AreaEntry entry = new AreaEntry();
            BeanUtils.copyProperties(entity, entry);
            node.setAttributes(entry);
        }
        return node;
    }

    public Long addAndId(AreaEntry entry) {
        Area entity = new Area();
        BeanUtils.copyProperties(entry, entity);
        entity.setLevel(Integer.parseInt(String.valueOf(entry.getParentLevel() == null ? 0 : entry.getParentLevel() + 1)));
        //Area entity=getEntity(entry);
        areaMapper.addAndId(entity);
        return entity.getAreaId();
    }

    public Integer batchAdd(List<AreaEntry> areaList) {
        List<Area> list = new ArrayList<Area>();
        Area area;
        for (AreaEntry item : areaList) {
            area = new Area();
            BeanUtils.copyProperties(item, area);
            list.add(area);
        }
        //areaList.forEach((data) -> list.add(getEntity(data)));
        return areaMapper.batchAdd(list);
    }

    public Integer removeById(Long areaId) {
        Area area = areaMapper.get(areaId);
        if (area != null) {
            return areaMapper.removeByPath(area.getPath());
        } else {
            return 0;
        }
    }

    public Integer remove(AreaParam condition) {
        return areaMapper.remove(condition);
    }

    public Integer realRemoveById(Long areaId) {
        return areaMapper.realRemoveById(areaId);
    }

    public Integer realRemove(AreaParam condition) {
        return areaMapper.realRemove(condition);
    }

    public AreaEntry get(Long areaId) {
        Area area = areaMapper.get(areaId);
        AreaEntry entry = new AreaEntry();
        BeanUtils.copyProperties(area, entry);
        entry.setState(area.getTotal() > 0 ? "closed" : "open");
        return entry;
    }

    public AreaEntry getFirst(AreaParam condition) {
        Area area = areaMapper.getFirst(condition);
        AreaEntry entry = new AreaEntry();
        BeanUtils.copyProperties(area, entry);
        entry.setState(area.getTotal() > 0 ? "closed" : "open");
        return entry;
    }

    public List<AreaEntry> getList(AreaParam condition) {
        List<AreaEntry> list = new ArrayList<AreaEntry>();
        List<Area> areaList = areaMapper.getList(condition);
        AreaEntry entry;
        for (Area item : areaList) {
            entry = new AreaEntry();
            BeanUtils.copyProperties(item,entry);
            list.add(entry);
        }
        //areaList.forEach((entity) -> list.add(getEntry(entity)));
        return list;
    }

    public List<AreaEntry> getPageList(Integer pageIndex, Integer pageSize, AreaParam condition) {
        pageIndex = (pageIndex - 1) * pageSize;
        List<AreaEntry> list = new ArrayList<AreaEntry>();
        List<Area> areaList = areaMapper.getPageList(pageIndex, pageSize, condition);
        AreaEntry entry;
        for (Area item : areaList) {
            entry = new AreaEntry();
            BeanUtils.copyProperties(item,entry);
            entry.setState(item.getTotal() > 0 ? "closed" : "open");
            list.add(entry);
        }
        //areaList.forEach((entity) -> list.add(getEntry(entity)));
        return list;
    }

    public List<AreaEntry> getAsyncTreeList(Long parentId) {
        AreaParam areaParam = new AreaParam();
        areaParam.setParentId(parentId);
        List<AreaEntry> list = new ArrayList<>();
        List<Area> areaList = areaMapper.getList(areaParam);
        //areaList.forEach((entity) -> list.add(getEntry(entity)));
        AreaEntry entry;
        for (Area item : areaList) {
            entry = new AreaEntry();
            BeanUtils.copyProperties(item,entry);
            entry.setState(item.getTotal() > 0 ? "closed" : "open");
            list.add(entry);
        }
        return list;
    }

    public List<TreeNode> getAsyncTree(Long parentId) {
        AreaParam areaParam = new AreaParam();
        areaParam.setParentId(parentId);
        List<TreeNode> list = new ArrayList<>();
        List<Area> areas = areaMapper.getList(areaParam);
        areas.forEach((entity) -> list.add(getTreeNode(entity)));
        return list;
    }

    public Integer getCount(AreaParam condition) {
        return areaMapper.getCount(condition);
    }

    public Long save(AreaEntry areaEntry) {
        Area entity = new Area();
        BeanUtils.copyProperties(areaEntry,entity);
        entity.setLevel(Integer.parseInt(String.valueOf(areaEntry.getParentLevel() == null ? 0 : areaEntry.getParentLevel() + 1)));
        if (entity.getAreaId() != null && entity.getAreaId().longValue() != 0) {
            areaMapper.updateById(entity);
        } else {
            areaMapper.addAndId(entity);
            ArrayList<String> path = new ArrayList<>();
            for (String i : areaEntry.getParentPath().split(",")) {
                if (!"".equals(i)) path.add(i);
            }
            path.add(entity.getAreaId().toString());
            areaMapper.updatePath(String.join(",", String.join(",", path)), entity.getAreaId());
        }
        return entity.getAreaId();
    }

    public Integer update(AreaEntry areaEntry, AreaParam condition) {
        Area entity = new Area();
        BeanUtils.copyProperties(areaEntry,entity);
        entity.setLevel(Integer.parseInt(String.valueOf(areaEntry.getParentLevel() == null ? 0 : areaEntry.getParentLevel() + 1)));
        return areaMapper.update(entity, condition);
    }
}