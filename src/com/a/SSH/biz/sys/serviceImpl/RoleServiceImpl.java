package com.a.SSH.biz.sys.serviceImpl;


import com.a.SSH.biz.sys.dao.RoleDao;
import com.a.SSH.biz.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/2.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public int saveOrUpdate(Map<String, Object> params) {
        if(params.get("dbid")!=null&&(!"".equals(params.get("dbid").toString()))){
            List<Integer> list = roleDao.queryRepetition(params);
            if(list!=null&&list.size()>0){
                return 0;
            }else {
                return roleDao.updateRole(params);
            }

        }else{
            List<Integer> list = roleDao.queryAddRepetition(params);
            if(list!=null&&list.size()>0){
                return 0;
            }else {
                return roleDao.addRole(params);
            }

        }

    }

    @Override
    public List<Map<String, Object>> queryRoleList(Map<String, Object> params) {
        List<Map<String, Object>> list = roleDao.queryRoleList(params);
        return list;
    }

    @Override
    public int queryCount(Map<String, Object> params) {
        int i = roleDao.queryCount(params);
        return i;
    }

    @Override
    @Transactional
    public int deleteRole(int params) {
        //System.out.println("22222222");
        roleDao.deleteRole_AuthByRoleId(params);
        //System.out.println("333333");
        roleDao.deleteUser_RoleByRoleId(params);
        //System.out.println("2444444");
        int i = roleDao.deleteRoleById(params);
        //System.out.println("25555555");
        return i;
    }


}
