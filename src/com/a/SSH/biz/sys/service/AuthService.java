package com.a.SSH.biz.sys.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/2.
 */

public interface AuthService {
    public Map<String,Object> selectRootAuth();
    public int saveOrUpdate(Map<String, Object> params);
    public int deleteAuth(int param);
    public List<Map<String,Object>> selectAllAuthByLayer(Map<String, Object> param);
    public int moveAuth(int dbid, int parentId);

}
