package com.a.SSH.biz.customer.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/28.
 */
public interface CustDao {
    public List<Map<String, Object>> queryCustList(Map<String, Object> params);
    public int queryCount(Map<String, Object> params);
    public List<Map<String,Object>> selectAllStatus(Map<String, Object> param);
    public int deleteCustById(int id);
    public int addType(Map<String, Object> params);
    public int updateType(Map<String, Object> params);
    public List<Integer> queryUpdateRepetition(Map<String, Object> params);
    public List<Integer> queryAddRepetition(Map<String, Object> params);
}
