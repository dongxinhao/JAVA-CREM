package com.a.SSH.biz.customer.daoImpl;

import com.a.SSH.biz.customer.dao.CustDao;
import com.a.SSH.biz.customer.service.CustService;
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
public class CustDaoImpl implements CustDao {
    @Autowired
    private SessionFactory sessionFactory;
    StringBuilder sb;
    List<String> validParams;
    private void ifCust(Map<String, Object> params){
        if (params.get("valid").equals("")) {
            sb.append("  " + "and ctName LIKE CONCAT(CONCAT('%',:ctName),'%')");
            validParams.add("ctName");
        }else if (params.get("valid").equals("2")) {
            sb.append("  " + "and ctStatus LIKE CONCAT(CONCAT('%',:ctName),'%')");
            validParams.add("ctName");
        }else if (params.get("valid").equals("3")) {
            sb.append("  " + "and ctSource LIKE CONCAT(CONCAT('%',:ctName),'%')");
            validParams.add("ctName");
        }else if (params.get("valid").equals("4")) {
            sb.append("  " + "and ctType LIKE CONCAT(CONCAT('%',:ctName),'%')");
            validParams.add("ctName");
        }else if (params.get("valid").equals("5")) {
            sb.append("  " + "and ctEmp LIKE CONCAT(CONCAT('%',:ctName),'%')");
            validParams.add("ctName");
        }else if (params.get("valid").equals("5")) {
            sb.append("  " + "and ctComp LIKE CONCAT(CONCAT('%',:ctName),'%')");
            validParams.add("ctName");
        }
    }
    @Override
    public List<Map<String, Object>> queryCustList(Map<String, Object> params) {
        sb = new StringBuilder("SELECT * from customer WHERE 1=1");
        validParams = new ArrayList<String>();
        if (!StringUtils.isEmpty(params.get("ctName"))) {
           this.ifCust(params);
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
    public int queryCount(Map<String, Object> params) {
        sb = new StringBuilder("SELECT count(dbid) from customer WHERE 1 = 1");
        validParams = new ArrayList<String>();
        if (!StringUtils.isEmpty(params.get("ctName"))) {
            this.ifCust(params);
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
    public List<Map<String, Object>> selectAllStatus(Map<String, Object> param) {
        String sql="select ctStatus from ctstatus";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return query.list();
    }
    public int deleteCustById(int id) {
        String sql="DELETE  FROM customer WHERE dbid= :dbid";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("dbid",id);
        return query.executeUpdate();
    }
    @Override
    public int addType(Map<String, Object> params) {
        String sql="INSERT into customer(ctName,ctStatus,ctSource,ctEmp,ctType,ctSex,ctTel,ctQQ,ctEmail,ctJob,ctComp,ctMessage) " +
                "VALUES (:ctName,:ctStatus,:ctSource,:ctEmp,:ctType,:ctSex,:ctTel,:ctQQ,:ctEmail,:ctJob,:ctComp,:ctMessage)";
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

    @Override
    public int updateType(Map<String, Object> params) {
        String sql="UPDATE  customer SET ctName= :ctName,ctStatus=:ctStatus,ctSource=:ctSource," +
                "ctEmp=:ctEmp,ctType=:ctType,ctSex=:ctSex,ctTel=:ctTel,ctQQ=:ctQQ,ctEmail=:ctEmail," +
                "ctJob=:ctJob,ctComp=:ctComp,ctMessage=:ctMessage WHERE dbid= :dbid";
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
        String sql="SELECT dbid FROM customer WHERE dbid!= :dbid AND ctName= :ctName";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("dbid",params.get("dbid"));
        query.setParameter("ctName",params.get("ctName"));
        query.list();
        return query.list();
    }

    @Override
    public List<Integer> queryAddRepetition(Map<String, Object> params) {

        String sql="SELECT dbid FROM customer WHERE ctName= :ctName";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("ctName",params.get("ctName"));
        List<Integer> list = query.list();
        return list;
    }
}
