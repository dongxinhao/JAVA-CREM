package com.a.SSH.biz.customer.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/28.
 */

public interface CustService {
    public int saveOrUpdate(Map<String, Object> params);
    public List<Map<String, Object>> queryCustList(Map<String, Object> params);
    public int queryCount(Map<String, Object> params);
    public List<Map<String,Object>> selectAllStatus(Map<String, Object> param);
    public int deleteCust(int params);
}
