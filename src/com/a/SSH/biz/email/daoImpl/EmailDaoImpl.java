package com.a.SSH.biz.email.daoImpl;

import com.a.SSH.biz.email.dao.EmailDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/28.
 */
@Repository
public class EmailDaoImpl implements EmailDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int insertIntoSendBox(Map<String,Object> params) {
        String sql = "insert into sendbox(sendName,receiveName,theme,detail,time)" +
                "values (:sendName,:receiveName,:theme,:detail,:time)";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("sendName",params.get("sendName"));
        query.setParameter("receiveName",params.get("receiveName"));
        query.setParameter("theme",params.get("theme"));
        query.setParameter("detail",params.get("detail"));
        query.setParameter("time",params.get("time"));
        return query.executeUpdate();
    }

    @Override
    public int insertIntoDraftBox(Map<String,Object> params) {
        String sql = "insert into draftbox(sendName,receiveName,theme,detail,time)" +
                "values (:sendName,:receiveName,:theme,:detail,:time)";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("sendName",params.get("sendName"));
        query.setParameter("receiveName",params.get("receiveName"));
        query.setParameter("theme",params.get("theme"));
        query.setParameter("detail",params.get("detail"));
        query.setParameter("time",params.get("time"));
        return query.executeUpdate();
    }

    @Override
    public int deleteFromSendBox(int dbid) {
        String sql = "delete from sendbox where dbid = :dbid";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("dbid",dbid);
        return query.executeUpdate();
    }

    @Override
    public int deleteFromDraftBox(int dbid) {
        String sql = "delete from draftbox where dbid = :dbid";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("dbid",dbid);
        return query.executeUpdate();
    }

    /*@Override
    public int updateSendBox() {
        return 0;
    }

    @Override
    public int updateDraftBox() {
        return 0;
    }
*/
    @Override
    public List<Map<String, Object>> queryReceiveBox(Map<String,Object> param) {
        StringBuilder sb = new StringBuilder("select * from sendbox where receiveName=:receiveName");

        System.out.println("-----执行处(6)-----");

        if (param.get("queryText")!=null&&!param.get("queryText").equals("")){
            if ("sendName".equals(param.get("queryMethod"))){
                sb.append(" "+"and sendName like concat(concat('%',:queryText),'%')");
            }
            if ("theme".equals(param.get("queryMethod"))){
                sb.append(" "+"and theme like concat(concat('%',:queryText),'%')");
            }
            if ("detail".equals(param.get("queryMethod"))){
                sb.append(" "+"and detail like concat(concat('%',:queryText),'%')");
            }
            if ("all".equals(param.get("queryMethod"))){
                sb.append(" "+"and(sendName like concat(concat('%',:queryText),'%') " +
                        "or theme like concat(concat('%',:queryText),'%')" +
                        "or detail like concat(concat('%',:queryText),'%'))");
            }
        }
        System.out.println(sb.toString());
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sb.toString());
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setParameter("receiveName",param.get("receiveName"));
        if (!"".equals(param.get("queryText"))&&param.get("queryText")!=null){
            query.setParameter("queryText",param.get("queryText"));
        }
        return query.list();
    }





    @Override
    public List<Map<String, Object>> queryDraftBox(Map<String,Object> param) {
        StringBuilder sb = new StringBuilder("select * from draftbox where sendName=:sendName");

        System.out.println("-----执行处(6)-----");
        if (param.get("queryText")!=null&&!param.get("queryText").equals("")) {
            if ("receiveName".equals(param.get("queryMethod"))) {
                sb.append(" " + "and receiveName like concat(concat('%',:queryText),'%')");
            }
            if ("theme".equals(param.get("queryMethod"))) {
                sb.append(" " + "and theme like concat(concat('%',:queryText),'%')");
            }
            if ("detail".equals(param.get("queryMethod"))) {
                sb.append(" " + "and detail like concat(concat('%',:queryText),'%')");
            }
            if ("all".equals(param.get("queryMethod"))) {
                sb.append(" " + "and(receiveName like concat(concat('%',:queryText),'%') " +
                        "or theme like concat(concat('%',:queryText),'%')" +
                        "or detail like concat(concat('%',:queryText),'%'))");
            }
        }
        System.out.println(sb.toString());
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sb.toString());
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setParameter("sendName",param.get("sendName"));
        if (!"".equals(param.get("queryText"))&&param.get("queryText")!=null){
            query.setParameter("queryText",param.get("queryText"));
        }

        System.out.println("-----执行处(7)-----");

        return query.list();
    }

    @Override
    public Map<String, Object> queryFromDraftBoxById(int dbid) {
        String sql = "select * from draftbox where dbid =:dbid";
        Session session  = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setParameter("dbid",dbid);
        return (Map<String, Object>)query.uniqueResult();
    }
}
