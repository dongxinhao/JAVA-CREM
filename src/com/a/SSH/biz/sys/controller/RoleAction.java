package com.a.SSH.biz.sys.controller;


import com.a.SSH.biz.sys.service.RoleService;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/2.
 */
@Controller
public class RoleAction extends CommonBean {
    private String dbid;
    private String orders;
    private String roleName;
    private String roleCode;
    private String valid;
    private int page;
    private int rows;

    @Autowired
    private RoleService roleService;
    public String toMain(){
        return "roleAdmin";
    }

    public void saveOrUpdate(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("dbid", StringUtils.isEmpty(dbid)?null:Integer.parseInt(dbid));
        map.put("roleName",roleName);
        map.put("roleCode",roleCode);
        map.put("valid",valid);
        map.put("orders", StringUtils.isEmpty(orders)?null:Integer.parseInt(orders));

        int i = roleService.saveOrUpdate(map);
        JSONObject obj=new JSONObject();
        if(i>0){
            obj.put("success",true);
        }else{
            obj.put("success",false);
        }
        outJSON(obj.toJSONString());
    }

    public void queryRole(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("roleName",roleName);
        map.put("roleCode",roleCode);
        map.put("valid",valid);
        map.put("rows",rows);
        int start=(page-1)*rows;
        map.put("start",start);
        int i = roleService.queryCount(map);
        List<Map<String, Object>> list = roleService.queryRoleList(map);


        JSONObject obj=new JSONObject();
        obj.put("total",i);
        JSONArray array=new JSONArray();
        if(list!=null&&list.size()>0){
            for(Map<String, Object> map1:list){
                array.add(map1);
            }
        }
        obj.put("rows",array);

        outJSON(obj.toJSONString());
    }

    public void deleteRole() {
        //System.out.println(dbid.toString());
        int i = roleService.deleteRole(Integer.parseInt(dbid));
        JSONObject obj = new JSONObject();
        if (i == 1) {
            obj.put("success", true);
        } else {
            obj.put("success", false);
        }
        outJSON(obj.toJSONString());
    }


    public String getDbid() {
        return dbid;
    }

    public void setDbid(String dbid) {
        this.dbid = dbid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }
}
