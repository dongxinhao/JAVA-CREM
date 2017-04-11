package com.a.SSH.biz.customer.controller;

import com.a.SSH.biz.customer.service.CtCareService;
import com.a.SSH.common.CommonAction.CommonBean;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/29.
 */
@Controller
public class CtCareAction extends CommonBean {
    private String careMethod;
    private String careTitle;
    private String ctName;
    private String dbid;
    private String ctemp;
    private String conMessage;
    private String careTime;
    private int page;
    private int rows;
    private String ctTel;

    @Autowired
    private CtCareService ctCareService;
    public String toMain() {
        return "ctCareAdmin";
    }
    public void queryCtCare(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("careMethod",careMethod);
        map.put("careTitle",careTitle);
        map.put("ctName",ctName);

        map.put("rows",rows);
        int start=(page-1)*rows;
        map.put("start",start);
        //System.out.println(map.toString());
        int i = ctCareService.queryCount(map);
        List<Map<String, Object>> list = ctCareService.queryCtCareList(map);

        //System.out.println("33333333333333");
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
    public void loadCtContent(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("ctTel",ctTel);
        map.put("ctName",ctName);

        map.put("rows",rows);
        int start=(page-1)*rows;
        map.put("start",start);
        //System.out.println(map.toString());
        int i = ctCareService.queryCtCount(map);
        List<Map<String, Object>> list = ctCareService.queryCtList(map);

        //System.out.println("33333333333333");
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
    public void delete() {
        //System.out.println(dbid.toString());
        int i = ctCareService.deleteById(Integer.parseInt(dbid));
        JSONObject obj = new JSONObject();
        if (i == 1) {
            obj.put("success", true);
        } else {
            obj.put("success", false);
        }
        outJSON(obj.toJSONString());
    }

    public void saveOrUpdate(){
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("dbid", StringUtils.isEmpty(dbid)?null:Integer.parseInt(dbid));

        //-------------------------------------
        map.put("ctName",ctName);
        map.put("careTitle",careTitle);
        map.put("ctemp",ctemp);
        map.put("conMessage",conMessage);
        map.put("careTime",careTime);
        map.put("careMethod",careMethod);

        //--------------------------------------
        int i = ctCareService.saveOrUpdate(map);
        JSONObject obj=new JSONObject();
        if(i>0){
            obj.put("success",true);
        }else{
            obj.put("success",false);
        }
        outJSON(obj.toJSONString());
    }






    public String getCareMethod() {
        return careMethod;
    }

    public void setCareMethod(String careMethod) {
        this.careMethod = careMethod;
    }

    public String getCareTitle() {
        return careTitle;
    }

    public void setCareTitle(String careTitle) {
        this.careTitle = careTitle;
    }

    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        this.ctName = ctName;
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

    public String getDbid() {
        return dbid;
    }

    public void setDbid(String dbid) {
        this.dbid = dbid;
    }

    public String getCtTel() {
        return ctTel;
    }

    public void setCtTel(String ctTel) {
        this.ctTel = ctTel;
    }

    public String getCtemp() {
        return ctemp;
    }

    public void setCtemp(String ctemp) {
        this.ctemp = ctemp;
    }

    public String getConMessage() {
        return conMessage;
    }

    public void setConMessage(String conMessage) {
        this.conMessage = conMessage;
    }

    public String getCareTime() {
        return careTime;
    }

    public void setCareTime(String careTime) {
        this.careTime = careTime;
    }
}
