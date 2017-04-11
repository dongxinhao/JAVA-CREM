package com.a.SSH.biz.email.serviceImpl;


import com.a.SSH.biz.email.dao.EmailDao;
import com.a.SSH.biz.email.service.EmailService;
import com.a.SSH.biz.sys.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/3/28.
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailDao emailDao;
    @Autowired
    private UserDao userDao;

    @Override
    public int sendEmail(Map<String,Object> param) {
        //int senderId = Integer.parseInt(param.get("senderId").toString());
        int receiveId = Integer.parseInt(param.get("receiveId").toString());
        //String sendName=userDao.queryUserNameById(senderId);
        String receiveName=userDao.queryUserNameById(receiveId);
        Map<String,Object> map = new HashMap<>();
        map.put("sendName",param.get("senderId"));
        map.put("receiveName",receiveName);
        map.put("theme",param.get("theme"));
        map.put("detail",param.get("detail"));
        map.put("time",param.get("time"));
        return emailDao.insertIntoSendBox(map);
    }

    @Override
    public int saveEmail(Map<String, Object> param) {
        //int senderId = Integer.parseInt(param.get("senderId").toString());
        int receiveId = Integer.parseInt(param.get("receiveId").toString());
        //String sendName=userDao.queryUserNameById(senderId);
        String receiveName=userDao.queryUserNameById(receiveId);
        Map<String,Object> map = new HashMap<>();
        map.put("sendName",param.get("senderId"));
        map.put("receiveName",receiveName);
        map.put("theme",param.get("theme"));
        map.put("detail",param.get("detail"));
        map.put("time",param.get("time"));
        return emailDao.insertIntoDraftBox(map);
    }

    @Override
    public int deleteEmailFromSend(int dbid) {

        return emailDao.deleteFromSendBox(dbid);
    }

    @Override
    public int deleteEmailFromDraft(int dbid) {
        return emailDao.deleteFromDraftBox(dbid);
    }

    @Override
    public List<Map<String, Object>> queryReceiveEmail(Map<String,Object> param,String receiveName) {
        String queryMethod ="";
        List<Map<String,Object>> result=new ArrayList<>();
        if (param.get("queryMethodId")!=null&&!"".equals(param.get("queryMethodId"))){
            if ("0".equals(param.get("queryMethodId"))){
                queryMethod="sendName";
            }else if ("1".equals(param.get("queryMethodId"))){
                queryMethod="theme";
            }else if ("2".equals(param.get("queryMethodId"))){
                queryMethod="detail";
            }else if("a".equals(param.get("queryMethodId"))){
                queryMethod="all";
            }
            param.put("queryMethod",queryMethod);
            result = emailDao.queryReceiveBox(param);
        }
        /*Iterator iterator = result.iterator();
        while (iterator.hasNext()){
            System.out.println("迭代结果集:"+iterator.next());
        }*/
        return result;
    }

    @Override
    public List<Map<String, Object>> querySavedEmail(Map<String, Object> param, String sendName) {
        String queryMethod ="";
        List<Map<String,Object>> result=new ArrayList<>();
        if (param.get("queryMethodId")!=null&&!"".equals(param.get("queryMethodId"))){
           if ("0".equals(param.get("queryMethodId"))){
                queryMethod="receiveName";
            }else if ("1".equals(param.get("queryMethodId"))){
                queryMethod="theme";
            }else if ("2".equals(param.get("queryMethodId"))){
                queryMethod="detail";
            }else if("a".equals(param.get("queryMethodId"))){
                queryMethod="all";
            }
            param.put("queryMethod",queryMethod);
            result = emailDao.queryDraftBox(param);
        }
        Iterator iterator = result.iterator();
        while (iterator.hasNext()){
            System.out.println("迭代结果集:"+iterator.next());
        }
        return result;
    }

    @Override
    @Transactional
    public int sendFromDraft(int dbid) {
        Map<String,Object> param = emailDao.queryFromDraftBoxById(dbid);
        int i = 0;
        int temp = 0;
        if (param!=null&&param.size()>0){

            i = emailDao.deleteFromDraftBox(dbid);
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS").format(new Date());
            param.put("time",time);
            temp = emailDao.insertIntoSendBox(param);
        }
        return temp+i;
    }
}
