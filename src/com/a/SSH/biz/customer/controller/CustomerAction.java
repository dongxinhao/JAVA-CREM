package com.a.SSH.biz.customer.controller;
import com.a.SSH.biz.customer.service.CustService;
import com.a.SSH.biz.sys.controller.CommonBean;

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
 * Created by Administrator on 2017/3/2.
 */
@Controller
public class CustomerAction extends CommonBean {
    private String ctName;
    private String valid;
    private String ctStatus;
    private String dbid;
    private int page;
    private int rows;
    private String ctSource;
    private String ctEmp;
    private String ctType;
    private String ctSex;
    private String ctTel;
    private String ctQQ;
    private String ctEmail;
    private String ctJob;
    private String ctComp;
    private String ctMessage;
    @Autowired
    private CustService custService;


    public String toMain() {
        return "customerAdmin";
    }

    public void saveOrUpdate(){
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("dbid", StringUtils.isEmpty(dbid)?null:Integer.parseInt(dbid));
        map.put("ctName",ctName);
        map.put("ctStatus",ctStatus);
        map.put("ctSource",ctSource);
        map.put("ctEmp",ctEmp);
        map.put("ctType",ctType);
        map.put("ctSex",ctSex);
        map.put("ctTel",ctTel);
        map.put("ctQQ",ctQQ);
        map.put("ctEmail",ctEmail);
        map.put("ctJob",ctJob);
        map.put("ctComp",ctComp);
        map.put("ctMessage",ctMessage);
        int i = custService.saveOrUpdate(map);
        JSONObject obj=new JSONObject();
        if(i>0){
            obj.put("success",true);
        }else{
            obj.put("success",false);
        }
        outJSON(obj.toJSONString());
    }
    public void queryCustomer() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("ctName", ctName);
        map.put("valid", valid);
        map.put("rows", rows);
        int start = (page - 1) * rows;
        map.put("start", start);
        int i = custService.queryCount(map);
        System.out.println("---------------------------------------------");
        List<Map<String, Object>> list = custService.queryCustList(map);
        JSONObject obj = new JSONObject();
        obj.put("total", i);
        JSONArray array = new JSONArray();
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map1 : list) {
                array.add(map1);
            }
        }
        obj.put("rows", array);
        System.out.println(obj.toJSONString());
        outJSON(obj.toJSONString());
    }
    public void selectAllStatus() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dbid", dbid);
        map.put("ctStatus", ctStatus);

        List<Map<String, Object>> list = custService.selectAllStatus(map);
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

    public void deleteCust() {
        int i = custService.deleteCust(Integer.parseInt(dbid));
        JSONObject obj = new JSONObject();
        if (i == 1) {
            obj.put("success", true);
        } else {
            obj.put("success", false);
        }
        outJSON(obj.toJSONString());
    }



    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        this.ctName = ctName;
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

    public String getDbid() {
        return dbid;
    }

    public void setDbid(String dbid) {
        this.dbid = dbid;
    }

    public String getCtStatus() {
        return ctStatus;
    }

    public void setCtStatus(String ctStatus) {
        this.ctStatus = ctStatus;
    }

    public String getCtSource() {
        return ctSource;
    }

    public void setCtSource(String ctSource) {
        this.ctSource = ctSource;
    }

    public String getCtEmp() {
        return ctEmp;
    }

    public void setCtEmp(String ctEmp) {
        this.ctEmp = ctEmp;
    }

    public String getCtType() {
        return ctType;
    }

    public void setCtType(String ctType) {
        this.ctType = ctType;
    }

    public String getCtSex() {
        return ctSex;
    }

    public void setCtSex(String ctSex) {
        this.ctSex = ctSex;
    }

    public String getCtTel() {
        return ctTel;
    }

    public void setCtTel(String ctTel) {
        this.ctTel = ctTel;
    }

    public String getCtQQ() {
        return ctQQ;
    }

    public void setCtQQ(String ctQQ) {
        this.ctQQ = ctQQ;
    }

    public String getCtEmail() {
        return ctEmail;
    }

    public void setCtEmail(String ctEmail) {
        this.ctEmail = ctEmail;
    }

    public String getCtJob() {
        return ctJob;
    }

    public void setCtJob(String ctJob) {
        this.ctJob = ctJob;
    }

    public String getCtComp() {
        return ctComp;
    }

    public void setCtComp(String ctComp) {
        this.ctComp = ctComp;
    }

    public String getCtMessage() {
        return ctMessage;
    }

    public void setCtMessage(String ctMessage) {
        this.ctMessage = ctMessage;
    }
}
