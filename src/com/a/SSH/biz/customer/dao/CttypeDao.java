package com.a.SSH.biz.customer.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/28.
 */
public interface CttypeDao {
    public List<Map<String, Object>> queryCttypeList(Map<String, Object> params);
    public int queryCttype(Map<String, Object> params);
    public int addType(Map<String, Object> params);
    public int updateType(Map<String, Object> params);
    public List<Integer> queryUpdateRepetition(Map<String, Object> params);
    public List<Integer> queryAddRepetition(Map<String, Object> params);
    public int deleteTypeById(int id);

}
