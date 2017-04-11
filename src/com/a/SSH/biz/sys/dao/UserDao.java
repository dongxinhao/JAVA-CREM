package com.a.SSH.biz.sys.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/28.
 */
public interface UserDao {
    public List<Map<String,Object>> queryByUserName(String name);
    public List<Map<String, Object>> queryUserList(Map<String, Object> params);
    public int queryCount(Map<String, Object> params);
    public int addUser(Map<String, Object> params);
    public int updateUser(Map<String, Object> params);
    public List<Integer> queryUpdateRepetition(Map<String, Object> params);
    public List<Integer> queryAddRepetition(Map<String, Object> params);
    public int deleteUserById(int id);
    public int deleteUser_RoleByUserId(int userId);
    public List<Map<String, Object>> queryAuthByUserId(int id);
    public List<Map<String,Object>> getResourceByUserId(int id);


    public int updateUserDept(String deptName, String newDeptName);



    public List<Map<String,Object>> getAllUsers();

    public String queryUserNameById(int dbid);

}
