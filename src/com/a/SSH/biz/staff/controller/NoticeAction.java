package com.a.SSH.biz.staff.controller;

import com.a.SSH.biz.staff.service.NoticeService;
import com.a.SSH.biz.sys.controller.CommonBean;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/28.
 */
public class NoticeAction extends CommonBean{
    private String noticeContent;
    private String mode;
    private String dbid;
    private String noticeName;
    private String theme;
    private String content;
    private String start;
    private String end;
    private int page;
    private int rows;
    @Autowired
    NoticeService noticeService;
    public String toMain(){
        return "success";
    }
    public  void  queryNotice(){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("noticeContent",noticeContent);
        map.put("mode",mode);
        map.put("rows", rows);
        int start = (page - 1) * rows;
        map.put("start", start);
        int i=noticeService.queryNoticeCont(map);
        List<Map<String, Object>> list = noticeService.queryNoticeList(map);
        JSONObject obj = new JSONObject();
        obj.put("total", i);
        JSONArray array = new JSONArray();
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map1 : list) {
                array.add(map1);
            }
        }
        obj.put("rows", array);
        outJSON(obj.toJSONString());
    }
    public void deleteNotice(){
        int i =noticeService.deleteNotice(Integer.parseInt(dbid));
        JSONObject jsonObject =new JSONObject();
        if (i==1){
            jsonObject.put("success",true);
        }else{
            jsonObject.put("success",false);
        }
        outJSON(jsonObject.toJSONString());
    }
    public void saveOrUpdate(){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("dbid", StringUtils.isEmpty(dbid)?null:Integer.parseInt(dbid));
        map.put("noticeName",noticeName);
        map.put("theme",theme);
        map.put("content",content);
        map.put("start",start);
        map.put("end",end);
        System.out.println(map.toString());
        int i =noticeService.saveOrUpdate(map);
        JSONObject obj=new JSONObject();
        if(i>0){
            obj.put("success",true);
        }else{
            obj.put("success",false);
        }
        outJSON(obj.toJSONString());
    }
    public String toWelfare(){
        return "success";
    }






    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
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

    public String getNoticeName() {
        return noticeName;
    }

    public void setNoticeName(String noticeName) {
        this.noticeName = noticeName;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
