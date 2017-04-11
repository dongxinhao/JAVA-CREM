package com.a.SSH.biz.staff.daoImpl;

import com.a.SSH.biz.staff.dao.NoticeDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.xml.transform.Transformer;
import java.util.*;

/**
 * Created by Administrator on 2017/3/28.
 */
@Service
public class NoticeDaoImpl implements NoticeDao{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public int queryNoticeCont(Map<String, Object> params) {
        StringBuilder sb=new StringBuilder("SELECT count(dbid) from notice WHERE 1=1");
        List<String> validParams=new ArrayList<String>();
        String mode=(String) params.get("mode");
        if ("1".equals(mode)&&!StringUtils.isEmpty(params.get("noticeContent"))){
            sb.append(" "+"and theme like CONCAT(CONCAT('%',:noticeContent),'%')");
            validParams.add("noticeContent");
        }else if ("2".equals(mode)&&!StringUtils.isEmpty(params.get("noticeContent"))) {
            sb.append(" "+"and content like CONCAT(CONCAT('%',:noticeContent),'%')");
            validParams.add("noticeContent");
        }
        Session session = sessionFactory.getCurrentSession();
        Query query =session.createSQLQuery(sb.toString());
        if (validParams.size()>0){
            for (String p:validParams) {
                query.setParameter(p,params.get(p));
            }
        }
        List list =query.list();
        return Integer.parseInt(list.get(0).toString());
    }

    @Override
    public List<Map<String, Object>> queryNoticeList(Map<String, Object> params) {
        StringBuilder sb=new StringBuilder("SELECT * from notice WHERE 1=1");
        List<String> validParams=new  ArrayList<String>();
        String mode=(String) params.get("mode");
        if ("1".equals(mode)&&!StringUtils.isEmpty(params.get("noticeContent"))){
            sb.append(" "+"and theme like CONCAT(CONCAT('%',:noticeContent),'%')");
            validParams.add("noticeContent");
        }else if ("2".equals(mode)&&!StringUtils.isEmpty(params.get("noticeContent"))) {
            sb.append(" "+"and content like CONCAT(CONCAT('%',:noticeContent),'%')");
            validParams.add("noticeContent");
        }
        sb.append(" "+"limit :start, :rows");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sb.toString());
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if(validParams.size()>0){
            for(String p:validParams){
                query.setParameter(p,params.get(p));
            }
        }
        query.setParameter("start",params.get("start"));
        query.setParameter("rows",params.get("rows"));
        return query.list();
    }

    @Override
    public int deleteNotice(int id) {
        String sql =" DELETE  FROM notice WHERE dbid = :dbid ";
        Session session =sessionFactory.getCurrentSession();
        Query query =session.createSQLQuery(sql);
        query.setParameter("dbid",id);
        return query.executeUpdate();
    }

    @Override
    public int updateNotice(Map<String, Object> params) {
        String sql="UPDATE  notice SET " +
                "theme= :theme,content=:content," +
                "end= :end" +
                " "+"WHERE dbid= :dbid";
        params.remove("noticeName");
        params.remove("start");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        Iterator<String> iterator = params.keySet().iterator();
        String key=null;
        while(iterator.hasNext()){
            key=iterator.next();
            System.out.println(key+"         "+params.get(key));
            query.setParameter(key,params.get(key));
        }
        return query.executeUpdate();
    }

    @Override
    public int addNotice(Map<String, Object> params) {
        String sql="INSERT into notice(noticeName,theme,content,start,end)" +
                " "+"VALUES (:noticeName,:theme,:content,:start,:end)";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        Iterator<String> iterator = params.keySet().iterator();
        String key=null;
        while(iterator.hasNext()){
            key=iterator.next();
            if(!key.equals("dbid")){
                query.setParameter(key,params.get(key));
            }
        }
        return query.executeUpdate();
    }
}
