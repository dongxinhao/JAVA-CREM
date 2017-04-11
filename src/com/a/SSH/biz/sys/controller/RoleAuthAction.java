package com.a.SSH.biz.sys.controller;


import com.a.SSH.biz.sys.service.RoleAuthService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/2.
 */
@Controller
public class RoleAuthAction extends CommonBean {
    private int roleId;
    private int arr[];
    @Autowired
    private RoleAuthService roleAuthService;

    public void addAuth() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        JSONObject obj = new JSONObject();
        for (int id : arr) {
            if (id != 0) {
                map = new HashMap<String, Object>();
                map.put("roleId", roleId);
                map.put("authId", id);
                list.add(map);
            }
        }
        int i = roleAuthService.addRoleAuth(list, roleId);
        if(i>0){
            obj.put("success", true);
        }else{
            obj.put("success", false);
        }



        outJSON(obj.toJSONString());
    }


    public void getValidAuth() {
        List<Map<String, Object>> list = roleAuthService.showValidAuth(roleId);
        JSONArray array = new JSONArray();
        for (Map<String, Object> map : list) {
            array.add(new JSONObject(map));
        }
        outJSON(array.toJSONString());

    }





    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }
}
