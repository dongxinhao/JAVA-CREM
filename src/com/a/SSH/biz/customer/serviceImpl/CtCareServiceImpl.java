package com.a.SSH.biz.customer.serviceImpl;

import com.a.SSH.biz.customer.dao.CtCareDao;
import com.a.SSH.biz.customer.service.CtCareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/29.
 */
@Service
public class CtCareServiceImpl implements CtCareService {
    @Autowired
    private CtCareDao ctCareDao;
    @Override
    public List<Map<String, Object>> queryCtCareList(Map<String, Object> params) {
        //System.out.println("11111111111111");
        return ctCareDao.queryCtCareList(params);
    }

    @Override
    public int queryCount(Map<String, Object> params) {
        //System.out.println("22222222222");
        return ctCareDao.queryCount(params);
    }

    @Override
    public List<Map<String, Object>> queryCtList(Map<String, Object> params) {
        return ctCareDao.queryCtList(params);
    }

    @Override
    public int queryCtCount(Map<String, Object> params) {
        return ctCareDao.queryCtCount(params);
    }

    @Override
    public int deleteById(int id) {
        return ctCareDao.deleteById(id);
    }

    @Override
    public int saveOrUpdate(Map<String, Object> params) {
        if(params.get("dbid")!=null&&(!"".equals(params.get("dbid").toString()))){
            System.out.println("update");
            return ctCareDao.updateCtCare(params);

        }else{
            System.out.println("add");
            return ctCareDao.addCtCare(params);
        }
    }
}
