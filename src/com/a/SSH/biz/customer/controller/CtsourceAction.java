package com.a.SSH.biz.customer.controller;

import com.a.SSH.biz.customer.service.CtsourceService;
import com.a.SSH.biz.sys.controller.CommonBean;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/2.
 */
@Controller
public class CtsourceAction extends CommonBean {
    private String ctSource;
    private int page;
    private int rows;
    private String dbid;
    private String sourceMessage;
    @Autowired
    private CtsourceService ctstatusService;

    public String toMain() {
        return "customerAdmin";
    }
    public void queryCtsource() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("ctSource", ctSource);
        map.put("rows", rows);
        int start = (page - 1) * rows;
        map.put("start", start);
        int i = ctstatusService.queryCtsource(map);
        List<Map<String, Object>> list = ctstatusService.queryCtsourceList(map);
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


    public void saveOrUpdate(){
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("dbid", StringUtils.isEmpty(dbid)?null:Integer.parseInt(dbid));
        map.put("ctSource",ctSource);
        map.put("sourceMessage",sourceMessage);
        int i = ctstatusService.saveOrUpdate(map);
        JSONObject obj=new JSONObject();
        if(i>0){
            obj.put("success",true);
        }else{
            obj.put("success",false);
        }
        outJSON(obj.toJSONString());
    }
    public void deleteAuth() {
        int i = ctstatusService.deleteSource(Integer.parseInt(dbid));
        JSONObject obj = new JSONObject();
        if (i == 1) {
            obj.put("success", true);
        } else {
            obj.put("success", false);
        }
        outJSON(obj.toJSONString());
    }

    public String getCtSource() {
        return ctSource;
    }

    public void setCtSource(String ctSource) {
        this.ctSource = ctSource;
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

    public String getSourceMessage() {
        return sourceMessage;
    }

    public void setSourceMessage(String sourceMessage) {
        this.sourceMessage = sourceMessage;
    }
}
