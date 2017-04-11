package com.a.SSH.biz.sys.controller;


import com.a.SSH.biz.sys.service.UserService;
import com.a.SSH.common.util.MD5;
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
public class UserAction extends CommonBean {
    private String userName;
    private String realName;
    private String valid;
    private String password;
    private String dbid;
    //-----------------
    private String  age;
    private String userSex;
    private String nation;
    private String userDept;
    private String education;
    private String marriage;
    private String address;
    private String TEL;
    private String email;

    //--------------------



    private int page;
    private int rows;
    @Autowired
    private UserService userService;

    public String toMain() {
        return "userAdmin";
    }

    public void queryUser() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("userName", userName);
        map.put("realName", realName);
        map.put("valid", valid);
        map.put("rows", rows);
        int start = (page - 1) * rows;
        map.put("start", start);

        int i = userService.queryCount(map);
        //System.out.println(i+"------------------");
        //System.out.println(map.toString());
        List<Map<String, Object>> list = userService.queryUserList(map);
        //System.out.println(list.toString());
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

    public void saveOrUpdate(){
        password = MD5.transformMD5(password);
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("dbid", StringUtils.isEmpty(dbid)?null:Integer.parseInt(dbid));
        map.put("userName",userName);
        map.put("realName",realName);
        map.put("valid",valid);
        map.put("password",password);
        //-------------------------------------
        map.put("age", StringUtils.isEmpty(age)?null:Integer.parseInt(age));
        map.put("userSex",userSex);
        map.put("nation",nation);
        map.put("userDept",userDept);
        map.put("marriage",marriage);
        map.put("address",address);
        map.put("TEL",TEL);
        map.put("email",email);
        map.put("education",education);

        //--------------------------------------

        int i = userService.saveOrUpdate(map);
        JSONObject obj=new JSONObject();
        if(i>0){
            obj.put("success",true);
        }else{
            obj.put("success",false);
        }
        outJSON(obj.toJSONString());
    }
    public void deleteAuth() {
        int i = userService.deleteUser(Integer.parseInt(dbid));
        JSONObject obj = new JSONObject();
        if (i == 1) {
            obj.put("success", true);
        } else {
            obj.put("success", false);
        }
        outJSON(obj.toJSONString());
    }









    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getDbid() {
        return dbid;
    }

    public void setDbid(String dbid) {
        this.dbid = dbid;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getUserDept() {
        return userDept;
    }

    public void setUserDept(String userDept) {
        this.userDept = userDept;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
