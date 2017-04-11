package com.a.SSH.biz.sys.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/6.
 */
public interface UserRoleDao {
    public List<Integer> queryUserId(int id);
    public List<Map<String,Object>> selectValidUser();
    public int deleteByUserId(int id);
    public int addUserRole(List<Map<String, Object>> list);

}
