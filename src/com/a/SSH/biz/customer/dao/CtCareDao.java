package com.a.SSH.biz.customer.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/29.
 */
public interface CtCareDao {
    public List<Map<String, Object>> queryCtCareList(Map<String, Object> params);
    public int queryCount(Map<String, Object> params);
    public List<Map<String, Object>> queryCtList(Map<String, Object> params);
    public int queryCtCount(Map<String, Object> params);
    public int deleteById(int id);
    public int addCtCare(Map<String, Object> params);
    public int updateCtCare(Map<String, Object> params);
}
