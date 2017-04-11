package com.a.SSH.biz.staff.controller;

import com.a.SSH.biz.staff.service.StaffService;
import com.a.SSH.common.CommonAction.CommonBean;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zh_ge on 2017/3/28.
 */

@Controller("staffAction")
public class StaffAction extends CommonBean{
    private String dbid;
    private String type;
    private String address;
    private String price;
    private String env;
    private String staffName;
    private String keyword;
    private String queryType;
    private String deptName;
    private String deptIntro;

    private int rows;
    private int page;

    @Autowired
    private StaffService staffService;

    public String toHouseMain(){
        return "success";
    }

    public void queryHouse(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("keyword",keyword);
        map.put("queryType",queryType);
        map.put("rows",rows);
        int start=(page-1)*rows;
        map.put("start",start);
        int i = staffService.queryHouseCount(map);
        List<Map<String, Object>> list = staffService.queryHouseList(map);
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


    public String toDeptMain(){
        return "success";
    }

    public void queryDept(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("deptName",deptName);
        map.put("rows",rows);
        int start=(page-1)*rows;
        map.put("start",start);
        int i = staffService.queryDeptCount(map);
        List<Map<String, Object>> list = staffService.queryDeptList(map);
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

    public void saveOrUpdateHouse(){
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("dbid", StringUtils.isEmpty(dbid)?null:Integer.parseInt(dbid));
        map.put("type",type);
        map.put("address",address);
        map.put("price",price);
        map.put("env",env);
        map.put("staffName",staffName);

        int i = staffService.saveOrUpdateHouse(map);
        JSONObject obj=new JSONObject();
        if(i>0){
            obj.put("success",true);
        }else{
            obj.put("success",false);
        }
        outJSON(obj.toJSONString());
    }

    public void deleteHouse(){
        int i = staffService.deleteHouse(Integer.parseInt(dbid));
        JSONObject obj = new JSONObject();
        if (i == 1) {
            obj.put("success", true);
        } else {
            obj.put("success", false);
        }
        outJSON(obj.toJSONString());
    }


    public void saveOrUpdateDept(){
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("dbid", StringUtils.isEmpty(dbid)?null:Integer.parseInt(dbid));
        map.put("deptName",deptName);
        map.put("deptIntro",deptIntro);

        int i = staffService.saveOrUpdateDept(map);
        JSONObject obj=new JSONObject();
        if(i>0){
            obj.put("success",true);
        }else{
            obj.put("success",false);
        }
        outJSON(obj.toJSONString());
    }

    public void deleteDept(){
        int i = staffService.deleteDept(Integer.parseInt(dbid),deptName);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptIntro() {
        return deptIntro;
    }

    public void setDeptIntro(String deptIntro) {
        this.deptIntro = deptIntro;
    }
}
