package com.a.SSH.biz.email.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/28.
 */
public interface EmailDao {

    public int insertIntoSendBox(Map<String, Object> params);

    public int insertIntoDraftBox(Map<String, Object> params);

    public int deleteFromSendBox(int dbid);

    public int deleteFromDraftBox(int dbid);

    /*public int updateSendBox();

    public int updateDraftBox();*/

    public List<Map<String,Object>> queryReceiveBox(Map<String, Object> param);


    public List<Map<String,Object>> queryDraftBox(Map<String, Object> param);


    public Map<String,Object> queryFromDraftBoxById(int dbid);
}
