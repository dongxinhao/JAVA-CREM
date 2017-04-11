package com.a.SSH.biz.sys.controller;


import com.a.SSH.biz.sys.service.AuthService;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/2.
 */
@Controller
public class AuthAction extends CommonBean {
    private String dbid;
    private String authName;
    private String authCode;
    private String authURL;
    private String orders;
    private String type;
    private String valid;
    private String layer;
    private String parentId;
    @Autowired
    private AuthService authService;

    public String toMain() {
        return "authAdmin";
    }

    public void getAllAuth() {

        Map<String, Object> map = authService.selectRootAuth();
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(map);

        outJSON(jsonArray.toJSONString());
    }

    public void saveOrUpdate() {
        //System.out.println("111111111111");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dbid", StringUtils.isEmpty(dbid) ? null : Integer.parseInt(dbid));
        map.put("parentId", StringUtils.isEmpty(parentId) ? null : Integer.parseInt(parentId));
        map.put("orders", StringUtils.isEmpty(orders) ? null : Integer.parseInt(orders));
        map.put("layer", StringUtils.isEmpty(layer) ? null : Integer.parseInt(layer));
        map.put("authName", authName);
        map.put("authURL", authURL);
        map.put("type", type);
        map.put("valid", valid);
        map.put("authCode", authCode);
        int i = authService.saveOrUpdate(map);
        JSONObject obj = new JSONObject();
        if (i == 1) {
            obj.put("success", true);
        } else {
            obj.put("success", false);
        }
         outJSON(obj.toJSONString());

    }

    public void deleteAuth() {
        int i = authService.deleteAuth(Integer.parseInt(dbid));
        JSONObject obj = new JSONObject();
        if (i == 1) {
            obj.put("success", true);
        } else {
            obj.put("success", false);
        }
         outJSON(obj.toJSONString());
    }


    public void selectAllAuthByLayer() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dbid", dbid);
        map.put("layer", layer);
        map.put("parentId", parentId);

        List<Map<String, Object>> list = authService.selectAllAuthByLayer(map);
        JSONArray list1 = new JSONArray();
        JSONObject obj = null;
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map1 : list) {
                obj = new JSONObject(map1);
                list1.add(obj);
            }
        }
        outJSON(list1.toJSONString());
    }


    public void moveAuth() {
        JSONObject obj=new JSONObject();
        int i = authService.moveAuth(Integer.parseInt(dbid),Integer.parseInt(parentId));
        if(i>0){
           obj.put("success",true);
        }else{
            obj.put("success",false);
        }

        outJSON(obj.toJSONString());
    }














    public String getDbid() {
        return dbid;
    }

    public void setDbid(String dbid) {
        this.dbid = dbid;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getAuthURL() {
        return authURL;
    }

    public void setAuthURL(String authURL) {
        this.authURL = authURL;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
