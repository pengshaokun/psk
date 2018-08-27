
package com.zhskg.bag.mapper;


import com.zhskg.bag.entity.AccountRole;
import com.zhskg.bag.param.AccountRoleParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
public interface AccountRoleMapper {
    Integer add(AccountRole accountRole);

    Long addAndId(AccountRole accountRole);

    Integer batchAdd(List<AccountRole> accountRoleList);

    Integer realRemoveById(@Param("id") Long id);

    Integer realRemoveByAccountId(@Param("accountId") Long accountId);

    Integer realRemove(AccountRoleParam condition);

    AccountRole get(@Param("id") Long id);

    AccountRole getFirst(AccountRoleParam condition);

    List<AccountRole> getList(AccountRoleParam condition);

    List<AccountRole> getRoleByAccountId(@Param("accountId") Long accountId, @Param("roleCode") String roleCode);

    List<AccountRole> getPageList(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize, @Param("condition") AccountRoleParam condition);

    Integer getCount(AccountRoleParam condition);

    Integer updateById(AccountRole accountRole);

    Integer update(@Param("accountRole") AccountRole accountRole, @Param("condition") AccountRoleParam condition);

    List<AccountRole> getAccountRoleList(Map<String, Object> map);
}