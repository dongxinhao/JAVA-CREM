package com.a.SSH.biz.customer.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/28.
 */

public interface CttypeService {
    public List<Map<String, Object>> queryCttypeList(Map<String, Object> params);
    public int queryCttype(Map<String, Object> params);
    public int saveOrUpdate(Map<String, Object> params);
    public int deleteType(int params);
}
