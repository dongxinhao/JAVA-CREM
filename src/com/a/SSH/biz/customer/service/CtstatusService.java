package com.a.SSH.biz.customer.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/28.
 */

public interface CtstatusService {
    public List<Map<String, Object>> queryCtstatusList(Map<String, Object> params);
    public int queryCtstatus(Map<String, Object> params);
    public int saveOrUpdate(Map<String, Object> params);
    public int deleteStatus(int params);
}
