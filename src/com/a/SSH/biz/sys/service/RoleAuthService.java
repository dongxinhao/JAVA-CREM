package com.a.SSH.biz.sys.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/6.
 */
public interface RoleAuthService {
    public List<Map<String,Object>> showValidAuth(int roleId);

    public int addRoleAuth(List<Map<String, Object>> list, int id);
}
