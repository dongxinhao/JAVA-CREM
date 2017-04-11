package com.a.SSH.biz.customer.serviceImpl;

import com.a.SSH.biz.customer.dao.CtcontactDao;
import com.a.SSH.biz.customer.service.CtcontactService;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lyz on 2017/3/28.
 */
@Service
public class CtcontactServiceImpl implements CtcontactService {
    @Autowired
    private CtcontactDao ctcontactDao;

    @Override
    public List<Map<String, Object>> getAllContact(Map<String, Object> params) {
        return ctcontactDao.getAllContact(params);
    }

    @Override
    public int countAllContact(Map<String, Object> params) {
        return ctcontactDao.countAllContact(params);
    }

    @Override
    public List<Map<String, Object>> getAllCustomer() {
        List<Map<String, Object>> allCustomer = ctcontactDao.getAllCustomer();
        return allCustomer;
    }

    @Override
    public int saveOrUpdate(Map<String, Object> params) {
        int i=0;
        if(params.get("dbid")!=null&&!"".equals(params.get("dbid").toString())){
            i = ctcontactDao.updateContact(params);
            return i;
        }else {
            i = ctcontactDao.addContact(params);
            return i;
        }
    }

    @Override
    public int deleteContact(int dbid) {
        return ctcontactDao.deleteContact(dbid);
    }

    @Override
    public void export() {
        List<Map<String, Object>> list = ctcontactDao.getAlllist();
        ctcontactDao.outExecl(list);
    }
}