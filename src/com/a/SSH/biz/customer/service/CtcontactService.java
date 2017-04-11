package com.a.SSH.biz.customer.service;

import jxl.write.WritableWorkbook;

import java.util.List;
import java.util.Map;

/**
 * Created by lyz on 2017/3/28.
 */
public interface CtcontactService {
    public List<Map<String, Object>> getAllContact(Map<String, Object> params);
    public int countAllContact(Map<String, Object> params);
    public List<Map<String,Object>> getAllCustomer();
    public int saveOrUpdate(Map<String,Object> params);
    public int deleteContact(int dbid);
    public void export();
}
