package com.a.SSH.biz.customer.daoImpl;

import com.a.SSH.biz.customer.dao.CtCareDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by Administrator on 2017/3/29.
 */
@Repository
public class CtCareDaoImpl implements CtCareDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Map<String, Object>> queryCtCareList(Map<String, Object> params) {
        StringBuilder sb=new StringBuilder("SELECT * from ctcare WHERE 1=1");
        List<String> validParams=new ArrayList<String>();

        if(!StringUtils.isEmpty(params.get("careMethod"))){
            sb.append("  "+"and careMethod LIKE CONCAT(CONCAT('%',:careMethod),'%')");
            validParams.add("careMethod");
        }
        if(!StringUtils.isEmpty(params.get("careTitle"))){
            sb.append("  "+"and careTitle LIKE CONCAT(CONCAT('%',:careTitle),'%')");
            validParams.add("careTitle");
        }
        if(!StringUtils.isEmpty(params.get("ctName"))){
            sb.append("  "+"and ctName= :ctName");
            validParams.add("ctName");
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
    public int queryCount(Map<String, Object> params) {
        StringBuilder sb=new StringBuilder("SELECT count(dbid) from ctcare WHERE 1=1");
        List<String> validParams=new ArrayList<String>();
        if(params!=null&&params.size()>0){

        }
        if(!StringUtils.isEmpty(params.get("careMethod"))){
            sb.append("  "+"and careMethod LIKE CONCAT(CONCAT('%',:careMethod),'%')");
            validParams.add("careMethod");
        }
        if(!StringUtils.isEmpty(params.get("careTitle"))){
            sb.append("  "+"and careTitle LIKE CONCAT(CONCAT('%',:careTitle),'%')");
            validParams.add("careTitle");
        }
        if(!StringUtils.isEmpty(params.get("ctName"))){
            sb.append("  "+"and ctName= :ctName");
            validParams.add("ctName");
        }

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sb.toString());
        if(validParams.size()>0){
            for(String p:validParams){
                query.setParameter(p,params.get(p));
            }
        }
        List list = query.list();

        return Integer.parseInt(list.get(0).toString());

    }

    @Override
    public List<Map<String, Object>> queryCtList(Map<String, Object> params) {
        StringBuilder sb=new StringBuilder("SELECT * from customer WHERE 1=1");
        List<String> validParams=new ArrayList<String>();

        if(!StringUtils.isEmpty(params.get("ctTel"))){
            sb.append("  "+"and ctTel LIKE CONCAT(CONCAT('%',:ctTel),'%')");
            validParams.add("ctTel");
        }
        if(!StringUtils.isEmpty(params.get("ctName"))){
            sb.append("  "+"and ctName LIKE CONCAT(CONCAT('%',:ctName),'%')");
            validParams.add("ctName");
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
    public int queryCtCount(Map<String, Object> params) {
        StringBuilder sb=new StringBuilder("SELECT count(*) from customer WHERE 1=1");
        List<String> validParams=new ArrayList<String>();

        if(!StringUtils.isEmpty(params.get("ctTel"))){
            sb.append("  "+"and ctTel LIKE CONCAT(CONCAT('%',:ctTel),'%')");
            validParams.add("ctTel");
        }
        if(!StringUtils.isEmpty(params.get("ctName"))){
            sb.append("  "+"and ctName= :ctName");
            validParams.add("ctName");
        }
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sb.toString());
        if(validParams.size()>0){
            for(String p:validParams){
                query.setParameter(p,params.get(p));
            }
        }
        List list = query.list();

        return Integer.parseInt(list.get(0).toString());
    }

    @Override
    public int deleteById(int id) {
        String sql="delete ctcare from ctcare where dbid=:dbid";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("dbid",id);

        return query.executeUpdate();
    }

    @Override
    public int addCtCare(Map<String, Object> params) {
        String sql="insert into ctcare (ctName,careTime,ctemp,conMessage,careTitle,careMethod)" +
                "values(:ctName,:careTime,:ctemp,:conMessage,:careTitle,:careMethod)";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        Iterator<String> iterator = params.keySet().iterator();
        String key=null;
        while(iterator.hasNext()){
            key=iterator.next();
            if(!("dbid".equals(key.toString()))){
                //System.out.println(key.toString()+params.get(key).toString());
                query.setParameter(key,params.get(key));
            }
        }
        return query.executeUpdate();
    }

    @Override
    public int updateCtCare(Map<String, Object> params) {
        String sql="UPDATE ctcare SET " +
                "                ctName=:ctName," +
                "                careTime=:careTime," +
                "                ctemp=:ctemp," +
                "                conMessage=:conMessage," +
                "                careTitle=:careTitle," +
                "                careMethod=:careMethod" +
                "                WHERE dbid=:dbid";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        Iterator<String> iterator = params.keySet().iterator();
        String key=null;
        while(iterator.hasNext()){
            key=iterator.next();
            query.setParameter(key,params.get(key));
            //System.out.println(key+params.get(key).toString());
        }
        return query.executeUpdate();
    }
}
