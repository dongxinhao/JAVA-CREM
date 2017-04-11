package com.a.SSH.biz.customer.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/29.
 */
public interface CtCareService {
    public List<Map<String, Object>> queryCtCareList(Map<String, Object> params);
    public int queryCount(Map<String, Object> params);
    public List<Map<String, Object>> queryCtList(Map<String, Object> params);
    public int queryCtCount(Map<String, Object> params);
    public int deleteById(int id);
    public int saveOrUpdate(Map<String, Object> params);

}
