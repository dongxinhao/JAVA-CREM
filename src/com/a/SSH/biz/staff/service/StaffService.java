package com.a.SSH.biz.staff.service;

import java.util.List;
import java.util.Map;

/**
 * Created by zh_ge on 2017/3/28.
 */
public interface StaffService {

    public int queryHouseCount(Map<String,Object> params);

    public List<Map<String,Object>> queryHouseList(Map<String,Object> params);

    public int saveOrUpdateHouse(Map<String,Object> params);

    public int deleteHouse(int dbid);

    public int queryDeptCount(Map<String,Object> params);

    public List<Map<String,Object>> queryDeptList(Map<String,Object> params);

    public int saveOrUpdateDept(Map<String,Object> params);

    public int deleteDept(int dbid, String deptName);
}
