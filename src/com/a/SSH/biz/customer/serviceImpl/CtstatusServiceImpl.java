package com.a.SSH.biz.customer.serviceImpl;

import com.a.SSH.biz.customer.dao.CtstatusDao;
import com.a.SSH.biz.customer.service.CtstatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/28.
 */
@Service
public class CtstatusServiceImpl implements CtstatusService {
    @Autowired
    private CtstatusDao cttypeDao;
    @Override
    public List<Map<String, Object>> queryCtstatusList(Map<String, Object> params) {
        return cttypeDao.queryCtstatusList(params);
    }

    @Override
    public int queryCtstatus(Map<String, Object> params) {
        return cttypeDao.queryCtstatus(params);
    }
    public int saveOrUpdate(Map<String, Object> params) {
        if(params.get("dbid")!=null&&(!"".equals(params.get("dbid").toString()))){
            List<Integer> list = cttypeDao.queryUpdateRepetition(params);
            if(list!=null&&list.size()>0){
                return 0;
            }else {
                return cttypeDao.updateStatus(params);
            }

        }else{
            List<Integer> list = cttypeDao.queryAddRepetition(params);
            System.out.println(list.size());
            if(list!=null&&list.size()>0){
                return 0;
            }else {
                return cttypeDao.addStatus(params);
            }

        }
    }
    @Override
    public int deleteStatus(int params) {
        int i = cttypeDao.deleteStatusById(params);
        return i;
    }

}
