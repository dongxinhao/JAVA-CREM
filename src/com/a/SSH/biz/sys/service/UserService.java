package com.a.SSH.biz.sys.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/28.
 */

public interface UserService {
    public Map<String,Object> login(String name);
    public List<Map<String, Object>> queryUserList(Map<String, Object> params);
    public int queryCount(Map<String, Object> params);
    public int saveOrUpdate(Map<String, Object> params);
    public int deleteUser(int params);
    public List<Map<String, Object>> queryAuthByUserId(int id);
    public List<String> getResourceByUserId(int id);




}
