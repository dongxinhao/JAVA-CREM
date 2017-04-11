package com.a.SSH.biz.email.controller;

import com.a.SSH.biz.email.service.EmailService;
import com.a.SSH.biz.sys.dao.UserDao;
import com.a.SSH.common.CommonAction.CommonBean;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/27.
 */
@Controller("jumpAction")
public class JumpAction extends CommonBean {

    private String userName;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserDao userDao;

    public String emailJump(){
        return "success";
    }

    public String sendBoxJump(){
        return "success";
    }

    public String draftBoxJump(){
        return "success";
    }

    public void queryEmail(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String queryText = request.getParameter("queryText");
        String queryMethodId = request.getParameter("queryMethodId");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String receiveName = (String)session.getAttribute("userName");
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("queryText",queryText);
        param.put("queryMethodId",queryMethodId);
        param.put("receiveName",receiveName);
        System.out.println("测试啦......4");
        List<Map<String,Object>> list = emailService.queryReceiveEmail(param,receiveName);
        System.out.println("-----执行处(2)-----");

        JSONArray json = new JSONArray();
        if(list!=null&&list.size()>0){
            Iterator iterator = list.iterator();
            while(iterator.hasNext()){
                json.add(iterator.next());
            }
        }
        outJSON(json.toJSONString());
    }

    public void queryEmailFromDraftBox(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String queryText = request.getParameter("queryText");
        String queryMethodId = request.getParameter("queryMethodId");
        HttpSession session = request.getSession();
        String sendName = (String)session.getAttribute("userName");

        System.out.println("-----执行处(1)-----");
        Map<String,Object> param = new HashMap<String,Object>();
        System.out.println("测试啦......1");

        param.put("queryText",queryText);
        param.put("queryMethodId",queryMethodId);
        param.put("sendName",sendName);
        System.out.println("测试啦......4");
        List<Map<String,Object>> list = emailService.querySavedEmail(param,sendName);
        System.out.println("-----执行处(2)-----");

        JSONArray json = new JSONArray();
        if(list!=null&&list.size()>0){
            Iterator iterator = list.iterator();
            while(iterator.hasNext()){
                json.add(iterator.next());
            }
        }
        outJSON(json.toJSONString());
    }


    public void saveEmail(){
        Map<String,Object> param = new HashMap<String,Object>();
        HttpServletRequest request = ServletActionContext.getRequest();
        String senderId = request.getParameter("senderId");
        String receiveId = request.getParameter("receiveId");
        String theme = request.getParameter("theme");
        String detail = request.getParameter("detail");
        String time = request.getParameter("time");

        param.put("senderId",senderId);
        param.put("receiveId",receiveId);
        param.put("theme",theme);
        param.put("detail",detail);
        param.put("time",time);

        int i = emailService.saveEmail(param);
        JSONObject obj = new JSONObject();
        if (i>0){
            obj.put("success",true);
        }else{
            obj.put("success",false);
        }
        outJSON(obj.toJSONString());
    }

    public void sendEmail(){
        Map<String,Object> param = new HashMap<String,Object>();
        HttpServletRequest request = ServletActionContext.getRequest();
        String senderId = request.getParameter("senderId");
        String receiveId = request.getParameter("receiveId");
        String theme = request.getParameter("theme");
        String detail = request.getParameter("detail");
        String time = request.getParameter("time");

        param.put("senderId",senderId);
        param.put("receiveId",receiveId);
        param.put("theme",theme);
        param.put("detail",detail);
        param.put("time",time);

        int i = emailService.sendEmail(param);
        JSONObject obj = new JSONObject();
        if (i>0){
            obj.put("success",true);
        }else{
            obj.put("success",false);
        }
        outJSON(obj.toJSONString());
    }

    public void sendFromDraft(){
        HttpServletRequest request = ServletActionContext.getRequest();
        int dbid = Integer.parseInt(request.getParameter("dbid"));
        int i = emailService.sendFromDraft(dbid);

        JSONObject obj = new JSONObject();
        if (i>1){
            obj.put("success",true);
        }else{
            obj.put("success",false);
        }
        outJSON(obj.toJSONString());
    }

    public void getReceiver(){
        List<Map<String,Object>> receiveList=userDao.getAllUsers();
        JSONArray jsonArray = new JSONArray();
        if (receiveList!=null&&receiveList.size()>0){
            Iterator iterator = receiveList.iterator();
            while (iterator.hasNext()){
                jsonArray.add(iterator.next());
            }
        }
        outJSON(jsonArray.toJSONString());
    }

    public void getSender(){
        List<Map<String,Object>> senderList=userDao.getAllUsers();
        JSONArray jsonArray = new JSONArray();
        if (senderList!=null&&senderList.size()>0){
            Iterator iterator = senderList.iterator();
            while (iterator.hasNext()){
                jsonArray.add(iterator.next());
            }
        }
        outJSON(jsonArray.toJSONString());
    }



    public void removeFromSend(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String str = request.getParameter("dbid");
        int dbid = Integer.parseInt(str);
        int i = emailService.deleteEmailFromSend(dbid);
        JSONObject obj = new JSONObject();
        if (i>0){
            obj.put("success",true);
        }else{
            obj.put("success",false);
        }
        outJSON(obj.toJSONString());
    }

    public void removeFromDraft(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String str = request.getParameter("dbid");
        int dbid = Integer.parseInt(str);
        int i = emailService.deleteEmailFromDraft(dbid);
        JSONObject obj = new JSONObject();
        if (i>0){
            obj.put("success",true);
        }else{
            obj.put("success",false);
        }
        outJSON(obj.toJSONString());
    }






















    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
