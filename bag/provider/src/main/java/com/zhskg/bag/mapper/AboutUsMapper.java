
package com.zhskg.bag.mapper;

import com.zhskg.bag.entity.AboutUs;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by fuhaibo on 2017/12/12.
 */
public interface AboutUsMapper {
    AboutUs get(@Param("aboutId") Integer aboutId);

    Integer removeById(@Param("aboutId") Integer aboutId);

    Integer addAndId(AboutUs aboutUs);

    Integer updateById(AboutUs aboutUs);

    List<AboutUs> getList(Map<String, Object> map);

    AboutUs getDetail();

}