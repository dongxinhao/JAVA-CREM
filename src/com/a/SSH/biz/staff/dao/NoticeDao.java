package com.a.SSH.biz.staff.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/28.
 */
public interface NoticeDao {
    public int queryNoticeCont(Map<String, Object> params);
    public List<Map<String, Object>> queryNoticeList(Map<String, Object> params);
    public int deleteNotice(int id);
    public int updateNotice(Map<String,Object> params);
    public int addNotice(Map<String,Object> params);
}
