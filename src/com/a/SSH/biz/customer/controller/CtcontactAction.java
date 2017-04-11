package com.a.SSH.biz.customer.controller;

import com.a.SSH.biz.customer.service.CtcontactService;
import com.a.SSH.common.CommonAction.CommonBean;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jxl.write.WritableWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lyz on 2017/3/28.
 */
@Controller(value = "ctcontactAction")
public class CtcontactAction extends CommonBean {
    private String text;
    private String type;
    private int rows;
    private int page;
    private String dbid;
    private String ctName;
    private String conName;
    private String conSex;
    private String conAge;
    private String conTel;
    private String conJob;
    private String ctCon;
    private String fileName;
    @Autowired
    private CtcontactService ctcontactService;
    public String toCtcontact(){
        return "contact";
    }

    public void getContact(){
        Map<String,Object> params=new LinkedHashMap<String,Object>();
        params.put("text",text);
        params.put("type",type);
        params.put("rows",rows);
        int start=(page - 1) * rows;
        params.put("start",start);
        int i = ctcontactService.countAllContact(params);
        List<Map<String, Object>> list = ctcontactService.getAllContact(params);
        JSONArray arr=new JSONArray();
        JSONObject obj=new JSONObject();
        obj.put("total",i);
        if(list!=null&&list.size()>0){
            for(Map<String, Object> map:list){
                arr.add(map);
            }
        }
        obj.put("rows",arr);
        System.out.println(obj.toJSONString());
        outJSON(obj.toJSONString());
    }

    public void getAllCustomer(){
        List<Map<String, Object>> list = ctcontactService.getAllCustomer();
        JSONArray arr=new JSONArray();
        JSONObject obj=null;
        if(list!=null&&list.size()>0){
            for(Map<String, Object> map:list){
                obj=new JSONObject(map);
                arr.add(obj);
            }
        }
        outJSON(arr.toJSONString());
    }

    public void saveOrUpdate(){
        Map<String,Object> params=new LinkedHashMap<String,Object>();
        params.put("ctName",ctName);
        params.put("conName",conName);
        params.put("conSex",conSex);
        params.put("conAge",conAge);
        params.put("conTel",conTel);
        params.put("conJob",conJob);
        params.put("ctCon",ctCon);
        params.put("dbid", StringUtils.isEmpty(dbid)?null:Integer.parseInt(dbid));
        int i = ctcontactService.saveOrUpdate(params);
        JSONObject obj=new JSONObject();
        System.out.println(i);
        if(i>0){
            obj.put("success",true);
        }else {
            obj.put("success",false);
        }
        outJSON(obj.toJSONString());
    }

    public void deleteContact(){
        int i = ctcontactService.deleteContact(Integer.parseInt(dbid));
        JSONObject obj = new JSONObject();
        if (i == 1) {
            obj.put("success", true);
        } else {
            obj.put("success", false);
        }
        outJSON(obj.toJSONString());
    }

    public void exportContact(){
        ctcontactService.export();
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getDbid() {
        return dbid;
    }

    public void setDbid(String dbid) {
        this.dbid = dbid;
    }

    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        this.ctName = ctName;
    }

    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }

    public String getConSex() {
        return conSex;
    }

    public void setConSex(String conSex) {
        this.conSex = conSex;
    }

    public String getConAge() {
        return conAge;
    }

    public void setConAge(String conAge) {
        this.conAge = conAge;
    }

    public String getConTel() {
        return conTel;
    }

    public void setConTel(String conTel) {
        this.conTel = conTel;
    }

    public String getConJob() {
        return conJob;
    }

    public void setConJob(String conJob) {
        this.conJob = conJob;
    }

    public String getCtCon() {
        return ctCon;
    }

    public void setCtCon(String ctCon) {
        this.ctCon = ctCon;
    }
}
