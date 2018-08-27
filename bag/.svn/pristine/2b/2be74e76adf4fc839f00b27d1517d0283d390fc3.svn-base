package com.zhskg.bag.controller.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.model.FileTempEntry;
import com.zhskg.bag.param.FileTempParam;
import com.zhskg.bag.service.FileService;
import com.zhskg.bag.util.FastDFSUtil;
import com.zhskg.bag.util.ReturnMapByBack;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping(value = "web/file/temp/")
public class WebFileTempController {
    @Reference(version = "1.0")
    private FileService fileTempService;

    /**
     * 获取文件列表
     *
     * @param page  当前页码
     * @param rows  每页显示数量
     * @param param 条件
     * @return 文件列表
     */
    @RequestMapping(value = "page/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getPageList(Integer page, Integer rows, FileTempParam param) {
        List<FileTempEntry> list = new ArrayList<>();
        int total = fileTempService.getCount(param);
        if (total > 0) {
            list = fileTempService.getPageList(page, rows, param);
            if (list != null && list.size() > 0) {
                return ReturnMapByBack.result(1, "获取成功！",list,total);
            }
        }
        return ReturnMapByBack.result(0, "获取失败！",list,total);
    }

    /**
     * 清理过期文件
     *
     * @return
     */
    @RequestMapping(value = "clear/over/list", method = RequestMethod.GET)
    @ResponseBody
    public Object dealOverdueList() {
        List<FileTempEntry> list = fileTempService.getOverdueFile();
        if (list != null && list.size() > 0) {
            for (FileTempEntry item : list) {
                FastDFSUtil.deleteFile(item.getFilePath());
            }
            boolean flag = fileTempService.clearOverdueFile();
            if (flag) {
                return ReturnMapByBack.result(1, "清理完成！");
            }else{
            	return ReturnMapByBack.result(0, "清理失败！");
            }
        }else{
        	return ReturnMapByBack.result(1, "无可清理的文件！");
        }
    }

}
