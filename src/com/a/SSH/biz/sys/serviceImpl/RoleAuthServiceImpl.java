package com.a.SSH.biz.sys.serviceImpl;


import com.a.SSH.biz.sys.dao.RoleAuthDao;
import com.a.SSH.biz.sys.service.RoleAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/2.
 */
@Service
public class RoleAuthServiceImpl implements RoleAuthService {
    @Autowired
    private RoleAuthDao roleAuthDao;


    @Override
    public List<Map<String, Object>> showValidAuth(int roleId) {
        List<Integer> oldAuths = roleAuthDao.queryRoleId(roleId);
        List<Map<String, Object>> list = roleAuthDao.selectValidAuth();
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if(list!=null&&list.size()>0){
            for(Map<String, Object> map:list){
                int i = Integer.parseInt(map.get("id").toString());
                if(oldAuths.contains(i)){
                    map.put("checked",true);
                }
            }

            Map<String ,Object> parent,son=null;
            List<Map<String ,Object>> children=null;
            for(int i=0;i<list.size();i++){
                parent = list.get(i);
                children=new ArrayList<Map<String ,Object>>();
                for(int j=0;j<list.size();j++){
                    son=list.get(j);
                    if((parent.get("id").toString()).equals((son.get("parentId").toString()))){
                        children.add(son);
                    }
                }
                parent.put("children",children);
            }

            for(Map<String,Object>map:list){
                if("-1".equals(map.get("parentId").toString())){
                    result.add(map);
                }
            }

        }

        return result;
    }



    @Override
    @Transactional
    public int addRoleAuth(List<Map<String, Object>> list ,int id) {
        roleAuthDao.deleteById(id);
        if(list!=null&&list.size()>0){

            return roleAuthDao.addRoleAuth(list);
        }else{
            return 0;
        }


    }
}
