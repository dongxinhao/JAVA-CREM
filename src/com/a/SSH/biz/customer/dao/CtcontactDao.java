package com.a.SSH.biz.customer.dao;

import jxl.write.WritableWorkbook;

import java.util.List;
import java.util.Map;

/**
 * Created by lyz on 2017/3/28.
 */
public interface CtcontactDao {
    public List<Map<String, Object>> getAllContact(Map<String, Object> params);
    public int countAllContact(Map<String, Object> params);
    public List<Map<String,Object>> getAllCustomer();
    public int addContact(Map<String, Object> params);
    public int updateContact(Map<String, Object> params);
    public int deleteContact(int dbid);
    public void outExecl(List<Map<String,Object>> list);
    public List<Map<String, Object>> getAlllist();
}
