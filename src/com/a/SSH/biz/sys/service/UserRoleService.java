package com.a.SSH.biz.sys.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/6.
 */
public interface UserRoleService {
    public Map<String,Object> showValidUser(int userId);
    public int addUserRole(List<Map<String, Object>> list, int id);

}
