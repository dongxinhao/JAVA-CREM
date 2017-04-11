package com.a.SSH.biz.sys.controller;


import com.a.SSH.biz.sys.service.UserRoleService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/2.
 */
@Controller
public class UserRoleAction extends CommonBean {
    private int userId;
    private int arr[];
    @Autowired
    private UserRoleService userRoleService;

    public void getValidRole() {

        Map<String, Object> map = userRoleService.showValidUser(userId);
        JSONObject obj=new JSONObject(map);
        outJSON(obj.toJSONString());

    }
    public void addRole() {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        JSONObject obj = new JSONObject();

        for (int id : arr) {
            if (id != 0) {
                map = new HashMap<String, Object>();
                map.put("userId", userId);
                map.put("roleId", id);
                list.add(map);
            }
        }
        int i = userRoleService.addUserRole(list, userId);
        if(i>0){
            obj.put("success", true);
        }else{
            obj.put("success", false);
        }
        outJSON(obj.toJSONString());
    }







    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }
}
