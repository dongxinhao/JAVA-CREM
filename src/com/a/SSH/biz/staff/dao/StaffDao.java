package com.a.SSH.biz.staff.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by zh_ge on 2017/3/28.
 */
public interface StaffDao {
    public int queryHouseCount(Map<String,Object> params);

    public List<Map<String,Object>> queryHouseList(Map<String,Object> params);

    public int addHouse(Map<String,Object> params);

    public int updateHouse(Map<String,Object> params);

    public int deleteHouse(int dbid);

    public int queryDeptCount(Map<String,Object> params);

    public List<Map<String,Object>> queryDeptList(Map<String,Object> params);

    public int addDept(Map<String,Object> params);

    public int updateDept(Map<String,Object> params);

    public int deleteDept(int dbid);

    public String queryDeptNameById(int dbid);

}
