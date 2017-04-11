package com.a.SSH.common.util;

import com.alibaba.fastjson.JSONArray;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/8.
 */
public class ModelUtil {
    public static String getChild(String parentId, HttpSession session){
        List<Map<String,Object>> auths = (List)session.getAttribute("auths");
        List<Map<String,Object>> childs = new ArrayList<Map<String,Object>>();
        JSONArray obj=new JSONArray();
        if(auths!=null&&auths.size()>0){

            for(Map<String,Object> auth:auths ){
                if(parentId.equals(auth.get("parentId").toString())){
                    childs.add(auth);
                }
            }
            for(Map<String,Object> child:childs ){
                obj.add(child);
            }
        }
        return obj.toJSONString();
    }
}
