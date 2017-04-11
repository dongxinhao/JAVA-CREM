package com.a.SSH.biz.sys.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/2.
 */

public interface AuthDao {
    public List<Map<String,Object>> selectAllAuth();
    public int addAuth(Map<String, Object> params);
    public int updateAuth(Map<String, Object> params);
    public List<Map<String,Object>> selectDbidByParentId(int param);
    public List<Map<String,Object>> selectAuthByLayer(Map<String, Object> param);
    public List<Integer> selectLayerAuth(int dbid);
    public int moveAuth(Map<String, Object> params);
    public int deleteAuthById(int param);
    public int deleteRole_AuthByAuthId(int param);


}
