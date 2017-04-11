package com.a.SSH.biz.sys.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/6.
 */
public interface RoleAuthDao {
    public List<Integer> queryRoleId(int id);
    public List<Map<String,Object>> selectValidAuth();

    public int deleteById(int id);
    public int addRoleAuth(List<Map<String, Object>> list);
}
