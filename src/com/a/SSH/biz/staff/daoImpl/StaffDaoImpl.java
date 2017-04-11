package com.a.SSH.biz.staff.daoImpl;

import com.a.SSH.biz.staff.dao.StaffDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zh_ge on 2017/3/28.
 */
@Repository
public class StaffDaoImpl implements StaffDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int queryHouseCount(Map<String, Object> params) {
        StringBuilder sb =new StringBuilder("select count(*) from house where 1 = 1");
        if(!StringUtils.isEmpty(params.get("keyword"))){
            sb.append("  "+"and "+params.get("queryType")+" LIKE CONCAT(CONCAT('%','"+params.get("keyword")+"'),'%')");
        }

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sb.toString());

        List list = query.list();

        return Integer.parseInt(list.get(0).toString());
    }

    @Override
    public List<Map<String, Object>> queryHouseList(Map<String, Object> params) {
        StringBuilder sb =new StringBuilder("select * from house where 1=1");
        if(!StringUtils.isEmpty(params.get("keyword"))){
            sb.append("  "+"and "+params.get("queryType")+" LIKE CONCAT(CONCAT('%','"+params.get("keyword")+"'),'%')");
        }
        sb.append(" "+"limit :start, :rows");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sb.toString());
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setParameter("start",params.get("start"));
        query.setParameter("rows",params.get("rows"));
        return query.list();
    }

    @Override
    public int addHouse(Map<String, Object> params) {
        String sql="INSERT into house(type,address,price,env,staffName)" +
                " "+"VALUES (:type,:address,:price,:env,:staffName)";
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
    public int updateHouse(Map<String, Object> params) {
        String sql="UPDATE  house SET " +
                "type= :type,address=:address," +
                "price= :price,env= :env,staffName=:staffName" +
                " "+"WHERE dbid= :dbid";
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
    public int deleteHouse(int dbid) {
        String sql="delete from house where dbid = :dbid";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("dbid",dbid);
        return query.executeUpdate();
    }

    @Override
    public int queryDeptCount(Map<String, Object> params) {
        StringBuilder sb =new StringBuilder("select count(*) from dept where 1 = 1");
        if(!StringUtils.isEmpty(params.get("deptName"))){
            sb.append("  "+"and deptName LIKE CONCAT(CONCAT('%','"+params.get("deptName")+"'),'%')");
        }

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sb.toString());

        List list = query.list();

        return Integer.parseInt(list.get(0).toString());
    }

    @Override
    public List<Map<String, Object>> queryDeptList(Map<String, Object> params) {
        StringBuilder sb =new StringBuilder("select * from dept where 1=1");
        if(!StringUtils.isEmpty(params.get("deptName"))){
            sb.append("  "+"and deptName LIKE CONCAT(CONCAT('%','"+params.get("deptName")+"'),'%')");
        }
        sb.append(" "+"limit :start, :rows");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sb.toString());
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setParameter("start",params.get("start"));
        query.setParameter("rows",params.get("rows"));
        return query.list();
    }

    @Override
    public int addDept(Map<String, Object> params) {
        String sql="INSERT into dept(deptName,deptIntro)" +
                " "+"VALUES (:deptName,:deptIntro)";
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
    public int updateDept(Map<String, Object> params) {
        String sql="UPDATE  dept SET " +
                "deptName= :deptName,deptIntro=:deptIntro" +
                " "+"WHERE dbid= :dbid";
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
    public int deleteDept(int dbid) {
        String sql="delete from dept where dbid = :dbid";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("dbid",dbid);
        return query.executeUpdate();
    }

    @Override
    public String queryDeptNameById(int dbid) {
        String sql = "select deptName from dept where dbid = :dbid";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("dbid",dbid);
        return query.list().get(0).toString();
    }
}
