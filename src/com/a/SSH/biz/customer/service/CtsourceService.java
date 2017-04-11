package com.a.SSH.biz.customer.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/28.
 */

public interface CtsourceService {
    public List<Map<String, Object>> queryCtsourceList(Map<String, Object> params);
    public int queryCtsource(Map<String, Object> params);
    public int saveOrUpdate(Map<String, Object> params);
    public int deleteSource(int params);
}
