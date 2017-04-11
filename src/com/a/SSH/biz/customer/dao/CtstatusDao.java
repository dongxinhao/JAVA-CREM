package com.a.SSH.biz.customer.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/28.
 */
public interface CtstatusDao {
    public List<Map<String, Object>> queryCtstatusList(Map<String, Object> params);
    public int queryCtstatus(Map<String, Object> params);
    public int addStatus(Map<String, Object> params);
    public int updateStatus(Map<String, Object> params);
    public List<Integer> queryUpdateRepetition(Map<String, Object> params);
    public List<Integer> queryAddRepetition(Map<String, Object> params);
    public int deleteStatusById(int id);

}
