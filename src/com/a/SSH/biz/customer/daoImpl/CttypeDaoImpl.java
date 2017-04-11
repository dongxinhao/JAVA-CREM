package com.a.SSH.biz.customer.daoImpl;

import com.a.SSH.biz.customer.dao.CttypeDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/25.
 */
@Repository
public class CttypeDaoImpl implements CttypeDao {
    @Autowired
    private SessionFactory sessionFactory;
    StringBuilder sb;
    List<String> validParams;
    @Override
    public List<Map<String, Object>> queryCttypeList(Map<String, Object> params) {
        sb = new StringBuilder("SELECT * from cttype WHERE 1=1");
        validParams = new ArrayList<String>();
        if (!StringUtils.isEmpty(params.get("ctType"))) {
            sb.append("  " + "and ctType LIKE CONCAT(CONCAT('%',:ctType),'%')");
            validParams.add("ctType");
        }
        sb.append(" "+"limit :start, :rows");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sb.toString());
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (validParams.size() > 0) {
            for (String p : validParams) {
                query.setParameter(p, params.get(p));
            }
        }
        query.setParameter("start", params.get("start"));
        query.setParameter("rows", params.get("rows"));
        System.out.println(query.list().toString());
        return query.list();
    }
    @Override
    public int queryCttype(Map<String, Object> params) {
        sb = new StringBuilder("SELECT count(dbid) from cttype WHERE 1 = 1");
        validParams = new ArrayList<String>();
        if (!StringUtils.isEmpty(params.get("ctType"))) {
            sb.append("  " + "and ctType LIKE CONCAT(CONCAT('%',:ctType),'%')");
            validParams.add("ctType");
        }
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sb.toString());
        if (validParams.size() > 0) {
            for (String p : validParams) {
                query.setParameter(p, params.get(p));
            }
        }
        List list = query.list();
        return Integer.parseInt(list.get(0).toString());
    }
    @Override
    public int addType(Map<String, Object> params) {
        String sql="INSERT into cttype(ctType)" +
                " "+"VALUES (:ctType)";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        Iterator<String> iterator = params.keySet().iterator();
        String key=null;
        //String userName = (String)params.get("userName");
        //System.out.println(userName.toString());
        while(iterator.hasNext()){
            key=iterator.next();
            if(!key.equals("dbid")){
                //System.out.println(key.toString()+params.get(key).toString());
                query.setParameter(key,params.get(key));
            }

        }

        return query.executeUpdate();
    }

    @Override
    public int updateType(Map<String, Object> params) {
        String sql="UPDATE  cttype SET ctType= :ctType WHERE dbid= :dbid";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        Iterator<String> iterator = params.keySet().iterator();
        String key=null;
        while(iterator.hasNext()){
            key=iterator.next();
            query.setParameter(key,params.get(key));
        }

        return query.executeUpdate();
    }

    @Override
    public List<Integer> queryUpdateRepetition(Map<String, Object> params) {
        String sql="SELECT dbid FROM cttype WHERE dbid!= :dbid AND ctType= :ctType";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("dbid",params.get("dbid"));
        query.setParameter("ctType",params.get("ctType"));
        query.list();
        return query.list();
    }

    @Override
    public List<Integer> queryAddRepetition(Map<String, Object> params) {

        String sql="SELECT dbid FROM cttype WHERE ctType= :ctType";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("ctType",params.get("ctType"));
        List<Integer> list = query.list();
        return list;
    }
    @Override
    public int deleteTypeById(int id) {
        String sql="DELETE  FROM cttype WHERE dbid= :dbid";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("dbid",id);
        return query.executeUpdate();
    }
}
