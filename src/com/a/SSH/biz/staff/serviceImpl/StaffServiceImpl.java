package com.a.SSH.biz.staff.serviceImpl;

import com.a.SSH.biz.staff.dao.StaffDao;
import com.a.SSH.biz.staff.service.StaffService;
import com.a.SSH.biz.sys.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zh_ge on 2017/3/28.
 */
@Service
public class StaffServiceImpl implements StaffService  {

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private UserDao userDao;

    @Override
    public int queryHouseCount(Map<String, Object> params) {
        return staffDao.queryHouseCount(params);
    }

    @Override
    public List<Map<String, Object>> queryHouseList(Map<String, Object> params) {
        return staffDao.queryHouseList(params);
    }

    @Override
    public int saveOrUpdateHouse(Map<String, Object> params) {
        if(params.get("dbid")!=null&&(!"".equals(params.get("dbid").toString()))){
                return staffDao.updateHouse(params);
        }else{
                return staffDao.addHouse(params);
        }
    }

    @Override
    public int deleteHouse(int dbid) {
        return staffDao.deleteHouse(dbid);
    }

    @Override
    public int queryDeptCount(Map<String, Object> params) {
        return staffDao.queryDeptCount(params);
    }

    @Override
    public List<Map<String, Object>> queryDeptList(Map<String, Object> params) {
        return staffDao.queryDeptList(params);
    }

    @Override
    public int saveOrUpdateDept(Map<String, Object> params) {
        if(params.get("dbid")!=null&&(!"".equals(params.get("dbid").toString()))){
            String oldDeptName = staffDao.queryDeptNameById(Integer.parseInt(params.get("dbid").toString()));
            userDao.updateUserDept(oldDeptName, params.get("deptName").toString());
            return staffDao.updateDept(params);
        }else{
            return staffDao.addDept(params);
        }
    }

    @Override
    public int deleteDept(int dbid, String deptName) {
        userDao.updateUserDept(deptName,null);
        return staffDao.deleteDept(dbid);
    }
}
