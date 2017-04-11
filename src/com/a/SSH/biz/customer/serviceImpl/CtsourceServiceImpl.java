package com.a.SSH.biz.customer.serviceImpl;

import com.a.SSH.biz.customer.dao.CtsourceDao;
import com.a.SSH.biz.customer.service.CtsourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/28.
 */
@Service
public class CtsourceServiceImpl implements CtsourceService {
    @Autowired
    private CtsourceDao ctsourceDao;
    @Override
    public List<Map<String, Object>> queryCtsourceList(Map<String, Object> params) {
        return ctsourceDao.queryCtsourceList(params);
    }

    @Override
    public int queryCtsource(Map<String, Object> params) {
        return ctsourceDao.queryCtsource(params);
    }
    public int saveOrUpdate(Map<String, Object> params) {
        if(params.get("dbid")!=null&&(!"".equals(params.get("dbid").toString()))){
            System.out.println(params.get("ctSource"));
            List<Integer> list = ctsourceDao.queryUpdateRepetition(params);
            if(list!=null&&list.size()>0){
                return 0;
            }else {
                return ctsourceDao.updateSource(params);
            }

        }else{
            List<Integer> list = ctsourceDao.queryAddRepetition(params);
            System.out.println(list.size());
            if(list!=null&&list.size()>0){
                return 0;
            }else {
                return ctsourceDao.addSource(params);
            }

        }
    }
    @Override
    public int deleteSource(int params) {
        int i = ctsourceDao.deleteSourceById(params);
        return i;
    }

}
