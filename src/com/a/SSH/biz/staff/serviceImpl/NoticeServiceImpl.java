package com.a.SSH.biz.staff.serviceImpl;

import com.a.SSH.biz.staff.dao.NoticeDao;
import com.a.SSH.biz.staff.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/28.
 */
@Service
public class NoticeServiceImpl implements NoticeService{
    @Autowired
    private NoticeDao noticeDao;
    @Override
    public int queryNoticeCont(Map<String, Object> params) {
        return noticeDao.queryNoticeCont(params);
    }

    @Override
    public List<Map<String, Object>> queryNoticeList(Map<String, Object> params) {
        return noticeDao.queryNoticeList(params);
    }

    @Override
    public int deleteNotice(int id) {
        return noticeDao.deleteNotice(id);
    }

    @Override
    public int saveOrUpdate(Map<String, Object> params) {
        if(params.get("dbid")!=null&&(!"".equals(params.get("dbid").toString()))){
           int i=noticeDao.updateNotice(params);
           return i;
        }else {
            return noticeDao.addNotice(params);
        }
    }
}
