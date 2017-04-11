package com.a.SSH.biz.sys.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/6.
 */
public interface RoleDao {
    public int addRole(Map<String, Object> params);
    public int updateRole(Map<String, Object> params);
    public List<Map<String, Object>> queryRoleList(Map<String, Object> params);
    public int queryCount(Map<String, Object> params);
    public List<Integer> queryRepetition(Map<String, Object> params);
    public List<Integer> queryAddRepetition(Map<String, Object> params);
    public int deleteRoleById(int id);
    public int deleteRole_AuthByRoleId(int roleId);
    public int deleteUser_RoleByRoleId(int roleId);


}
