
package com.zhskg.bag.mapper;

import com.zhskg.bag.entity.ThirdPartyInfo;
import com.zhskg.bag.model.ThirdPartyInfoEntry;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by fuhaibo on 2018/02/27.
 */
public interface ThirdPartyInfoMapper {
    ThirdPartyInfo get(@Param("openId") String openId);
    
    ThirdPartyInfo getFirst(@Param("openId") String openId, @Param("accessToken") String accessToken);

    Integer removeById(@Param("openId") String openId);

    Integer add(ThirdPartyInfo thirdPartyInfo);

    Integer updateById(ThirdPartyInfo thirdPartyInfo);
    
    List<ThirdPartyInfo> getList(Map<String, Object> map);
    
    List<ThirdPartyInfo> getPageList(Map<String, Object> map);
    
    Integer getCount(Map<String, Object> map);

    List<ThirdPartyInfo> getByUserId(long userId);

    int setUserId(Map<String,Object> map);

    /**
     * 根据userId和平台类型查询数据
     * @param map
     * @return
     */
    List<ThirdPartyInfo> getByUserIdAndTerraceType(Map<String,Object> map);



}