package com.a.SSH.biz.sys.serviceImpl;


import com.a.SSH.biz.sys.dao.AuthDao;
import com.a.SSH.biz.sys.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/2.
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthDao authDao;
    @Override
    public Map<String, Object> selectRootAuth() {
        List<Map<String, Object>> allAuth = authDao.selectAllAuth();
        Map<String, Object> father=null;
        Map<String, Object> son;
        List<Map<String, Object>> children=null;
        if(allAuth!=null&&allAuth.size()>0){
            for(int i=0;i<allAuth.size();i++){
                father= allAuth.get(i);
                children=new ArrayList<Map<String,Object>>();
                for(int j=0;j<allAuth.size();j++){
                    son=allAuth.get(j);
                    if((father.get("dbid").toString()).equals(son.get("parentId").toString())){
                        children.add(son);
                    }
                }
                father.put("children",children);
            }
        }
        Map<String, Object> zong=null;
        for (Map<String, Object> a :allAuth) {
            if("-1".equals(a.get("parentId").toString())){
                zong=a;
            }
        }
        return zong;
    }

    @Override
    public int saveOrUpdate(Map<String, Object> params) {
        if(params.get("dbid")!=null&&(!"".equals(params.get("dbid").toString()))){
            System.out.println("update");
            return authDao.updateAuth(params);

        }else{
            System.out.println("add");
            return authDao.addAuth(params);
        }
    }

    @Override
    @Transactional
    public int deleteAuth(int param) {
        List<Map<String, Object>> list = authDao.selectDbidByParentId(param);
        if(list!=null&&list.size()>0){
            return 0;
        }else{
            authDao.deleteRole_AuthByAuthId(param);
            int i = authDao.deleteAuthById(param);
            return i;
        }

    }

    @Override
    public List<Map<String, Object>> selectAllAuthByLayer(Map<String,Object> param) {
        List<Map<String, Object>> list = authDao.selectAuthByLayer(param);
        return list;
    }

    @Override
    public int moveAuth(int dbid, int parentId) {
        List<Integer> list = authDao.selectLayerAuth(parentId);

        if(list!=null&&list.size()>0){
            int layer=list.get(0);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("dbid",dbid);
            map.put("layer",layer+1);
            map.put("parentId",parentId);
            int i = authDao.moveAuth(map);
            return i;
        }else{
            return 0;
        }


    }
}
