package com.a.SSH.biz.sys.serviceImpl;


import com.a.SSH.biz.sys.dao.UserDao;
import com.a.SSH.biz.sys.service.UserService;
import com.a.SSH.common.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/28.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public Map<String, Object> login(String name) {
        List<Map<String, Object>> list = userDao.queryByUserName(name);
        if(list!=null&&list.size()>0){
            Map<String, Object> map = list.get(0);
            return map;
        }else{
            return null;
        }

    }

    @Override
    public List<Map<String, Object>> queryUserList(Map<String, Object> params) {
        return userDao.queryUserList(params);
    }

    @Override
    public int queryCount(Map<String, Object> params) {
        return userDao.queryCount(params);
    }

    @Override
    public int saveOrUpdate(Map<String, Object> params) {
        if(params.get("dbid")!=null&&(!"".equals(params.get("dbid").toString()))){
            List<Integer> list = userDao.queryUpdateRepetition(params);
            if(list!=null&&list.size()>0){
                return 0;
            }else {
                return userDao.updateUser(params);
            }

        }else{
            List<Integer> list = userDao.queryAddRepetition(params);
            System.out.println(list.size());
            if(list!=null&&list.size()>0){
                return 0;
            }else {
                return userDao.addUser(params);
            }

        }
    }

    @Override
    @Transactional
    public int deleteUser(int params) {
        userDao.deleteUser_RoleByUserId(params);
        int i = userDao.deleteUserById(params);
        return i;
    }

    @Override
    public List<Map<String, Object>> queryAuthByUserId(int id) {
        List<Map<String, Object>> list = userDao.queryAuthByUserId(id);
        Map<String, Object> father=null;
        Map<String, Object> son;
        List<Map<String, Object>> children=null;
        if(list!=null&&list.size()>0){
            for(int i=0;i<list.size();i++){
                father= list.get(i);
                children=new ArrayList<Map<String,Object>>();
                for(int j=0;j<list.size();j++){
                    son=list.get(j);
                    if((father.get("dbid").toString()).equals(son.get("parentId").toString())){
                        children.add(son);
                    }
                }
                father.put("children",children);
            }
        }

        return list;
    }

    @Override
    public List<String> getResourceByUserId(int id) {
        List<Map<String, Object>> mapList = userDao.getResourceByUserId(id);
        List<String> list=new ArrayList<String>();
        for(Map<String, Object> map:mapList){
            list.add(map.get("authCode").toString());
        }
        return list;

    }
}
