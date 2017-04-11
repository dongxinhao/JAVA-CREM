package com.a.SSH.biz.sys.serviceImpl;


import com.a.SSH.biz.sys.dao.UserRoleDao;
import com.a.SSH.biz.sys.service.UserRoleService;
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
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;


    @Override
    public Map<String, Object> showValidUser(int userId) {
        List<Integer> oldAuths = userRoleDao.queryUserId(userId);
        List<Map<String, Object>> list = userRoleDao.selectValidUser();
        Map<String,Object> map1=new HashMap<String,Object>();
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if(list!=null&&list.size()>0) {
            for (Map<String, Object> map : list) {
                int i = Integer.parseInt(map.get("dbid").toString());
                if (oldAuths.contains(i)) {
                    map.put("checked", true);
                }else{
                    map.put("checked", false);
                }
                result.add(map);
            }
            map1.put("rows",result);


        }

        return map1;
    }

    @Override
    @Transactional
    public int addUserRole(List<Map<String, Object>> list, int id) {
        userRoleDao.deleteByUserId(id);
        if(list!=null&&list.size()>0){

            return userRoleDao.addUserRole(list);
        }else{
            return 0;
        }
    }
}
