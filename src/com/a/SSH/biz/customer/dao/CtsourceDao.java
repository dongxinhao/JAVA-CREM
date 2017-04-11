package com.a.SSH.biz.customer.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/28.
 */
public interface CtsourceDao {
    public List<Map<String, Object>> queryCtsourceList(Map<String, Object> params);
    public int queryCtsource(Map<String, Object> params);
    public int addSource(Map<String, Object> params);
    public int updateSource(Map<String, Object> params);
    public List<Integer> queryUpdateRepetition(Map<String, Object> params);
    public List<Integer> queryAddRepetition(Map<String, Object> params);
    public int deleteSourceById(int id);

}
