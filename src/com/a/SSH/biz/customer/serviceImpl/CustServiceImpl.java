package com.a.SSH.biz.customer.serviceImpl;
import com.a.SSH.biz.customer.dao.CustDao;
import com.a.SSH.biz.customer.service.CustService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/28.
 */
@Service
public class CustServiceImpl implements CustService {
    @Autowired
    private CustDao custDao;
    @Override
    public List<Map<String, Object>> queryCustList(Map<String, Object> params) {
        return custDao.queryCustList(params);
    }

    @Override
    public int queryCount(Map<String, Object> params) {
        return custDao.queryCount(params);
    }
    @Override
    public List<Map<String, Object>> selectAllStatus(Map<String,Object> param) {
        List<Map<String, Object>> list = custDao.selectAllStatus(param);
        return list;
    }
    @Override
    public int deleteCust(int params) {
        int i = custDao.deleteCustById(params);
        return i;
    }
    public int saveOrUpdate(Map<String, Object> params) {
        if(params.get("dbid")!=null&&(!"".equals(params.get("dbid").toString()))){
            List<Integer> list = custDao.queryUpdateRepetition(params);
            System.out.println(list.size());
            if(list!=null&&list.size()>0){
                return 0;
            }else {
                return custDao.updateType(params);
            }

        }else{
            List<Integer> list = custDao.queryAddRepetition(params);
            System.out.println(list.size());
            if(list!=null&&list.size()>0){
                return 0;
            }else {
                return custDao.addType(params);
            }

        }
    }


}
