package com.zhskg.bag.controller.web;

import com.alibaba.dubbo.config.annotation.Reference;

import com.zhskg.bag.model.AreaEntry;
import com.zhskg.bag.param.AreaParam;
import com.zhskg.bag.service.AreaService;
import com.zhskg.bag.util.ReturnMapByBack;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "web/area/")
public class WebAreaController {
    @Reference(version = "1.0")
    private AreaService areaService;

    /**
     * 根据主键id获取区域详细信息
     * @param id 主键id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") Long id) {
        try {
            return ReturnMapByBack.result(1, "获取成功！", areaService.get(id));
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "获取失败！", null);
        }
    }

    /**
     * 保存区域信息
     * @param data
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody AreaEntry data) {
        try {
            Long areaId = areaService.save(data);
            if (areaId != null && areaId > 0) {
                return ReturnMapByBack.result(1, "保存成功！");
            }
            return ReturnMapByBack.result(0, "保存失败！");
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "保存失败！");
        }
    }

    /**
     * 获取区域异步树列表
     * @param id
     * @return
     */
    @RequestMapping(value = "asyn/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getAsynList(Long id) {
        try {
            if (id == null) {
                id = 0L;
            }
            List<AreaEntry> list = areaService.getAsyncTreeList(id);
            return ReturnMapByBack.result(1, "获取成功！", list,list.size());
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 获取区域异步树
     * @param id
     * @return
     */
    @RequestMapping(value = "asyn/tree", method = RequestMethod.GET)
    @ResponseBody
    public Object getAsynTree(Long id) {
        try {
            if (id == null) id = 0l;
            return areaService.getAsyncTree(id);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 获取区域列表
     * @param parentId
     * @return
     */
    @RequestMapping(value="/get",method = RequestMethod.GET)
    @ResponseBody
    public Object getArea(@RequestParam(defaultValue = "0") long parentId)
    {
        AreaParam param = new AreaParam();
        param.setParentId(parentId);
        List<AreaEntry> list=areaService.getList(param);
        return ReturnMapByBack.result(1,"success",list);
    }
}
