package com.a.SSH.biz.customer.serviceImpl;

import com.a.SSH.biz.customer.dao.CttypeDao;
import com.a.SSH.biz.customer.service.CttypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/28.
 */
@Service
public class CttypeServiceImpl implements CttypeService {
    @Autowired
    private CttypeDao cttypeDao;
    @Override
    public List<Map<String, Object>> queryCttypeList(Map<String, Object> params) {
        return cttypeDao.queryCttypeList(params);
    }

    @Override
    public int queryCttype(Map<String, Object> params) {
        return cttypeDao.queryCttype(params);
    }
    public int saveOrUpdate(Map<String, Object> params) {
        if(params.get("dbid")!=null&&(!"".equals(params.get("dbid").toString()))){
            System.out.println(params.get("dbid"));
            System.out.println(params.get("ctType"));
            List<Integer> list = cttypeDao.queryUpdateRepetition(params);
            System.out.println(list.size());
            if(list!=null&&list.size()>0){
                return 0;
            }else {
                return cttypeDao.updateType(params);
            }

        }else{
            List<Integer> list = cttypeDao.queryAddRepetition(params);
            System.out.println(list.size());
            if(list!=null&&list.size()>0){
                return 0;
            }else {
                return cttypeDao.addType(params);
            }

        }
    }
    @Override
    public int deleteType(int params) {
        int i = cttypeDao.deleteTypeById(params);
        return i;
    }

}
