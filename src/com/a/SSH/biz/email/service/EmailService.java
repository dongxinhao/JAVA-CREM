package com.a.SSH.biz.email.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/28.
 */
public interface EmailService {

    public int sendEmail(Map<String, Object> param);

    public int saveEmail(Map<String, Object> param);

    public int deleteEmailFromSend(int dbid);

    public int deleteEmailFromDraft(int dbid);

    public List<Map<String,Object>> queryReceiveEmail(Map<String, Object> param, String receiveName);

    public List<Map<String,Object>> querySavedEmail(Map<String, Object> param, String receiveName);

    public int sendFromDraft(int dbid);
}
