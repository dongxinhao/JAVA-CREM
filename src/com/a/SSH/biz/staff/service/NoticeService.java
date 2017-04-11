package com.a.SSH.biz.staff.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/28.
 */
public interface NoticeService {
    public int queryNoticeCont(Map<String, Object> params);
    public List<Map<String, Object>> queryNoticeList(Map<String, Object> params);
    public int deleteNotice(int id);
    public int saveOrUpdate(Map<String,Object> params);
}
