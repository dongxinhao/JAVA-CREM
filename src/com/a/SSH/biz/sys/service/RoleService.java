package com.a.SSH.biz.sys.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/2.
 */

public interface RoleService {
    public int saveOrUpdate(Map<String, Object> params);
    public List<Map<String, Object>> queryRoleList(Map<String, Object> params);
    public int queryCount(Map<String, Object> params);
    public int deleteRole(int params);

}
