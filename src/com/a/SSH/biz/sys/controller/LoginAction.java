package com.a.SSH.biz.sys.controller;


import com.a.SSH.biz.sys.service.UserService;
import com.a.SSH.common.util.MD5;
import com.alibaba.fastjson.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by Administrator on 2017/2/26.
 */
@Controller
public class LoginAction extends CommonBean {
    private String userName;
    private String password;
    @Autowired
    private UserService userService;

    public void login(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        JSONObject obj=new JSONObject();
        Map<String,Object> map=new HashMap<String,Object>();
        Map<String, Object> map2 = userService.login(userName);
        //System.out.println(userName+password);
        if(map2==null){
            obj.put("success",false);
            obj.put("reason1","用户不存");
        }else{
            if(map2.get("password").equals(MD5.transformMD5(password))){
                int dbid= (Integer)map2.get("dbid");
                obj.put("success",true);
                List<Map<String, Object>> list = userService.queryAuthByUserId(dbid);
                List<String> resources = userService.getResourceByUserId(dbid);
                session.setAttribute("auths",list);
                session.setAttribute("username",userName);
                session.setAttribute("userName",userName);
                session.setAttribute("resource",resources);
            }else{
                obj.put("success",false);
                obj.put("reason1","密码错误");
            }

        }
        outJSON(obj.toJSONString());

    }

    public String index(){
        return "index";
    }

    public String logout(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("username");
        session.invalidate();
        return "login";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
