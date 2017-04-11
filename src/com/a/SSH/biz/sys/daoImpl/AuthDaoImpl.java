package com.a.SSH.biz.sys.daoImpl;

import com.a.SSH.biz.sys.dao.AuthDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/27.
 */
@Repository
public class AuthDaoImpl implements AuthDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Map<String, Object>> selectAllAuth() {
        String sql="select * from auth ORDER  BY orders";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

    @Override
    public int addAuth(Map<String, Object> params) {
        String sql="INSERT into auth(authName,authCode,authURL,type,parentId,orders,valid,layer)" +
                "         VALUES (:authName,:authCode,:authURL,:type,:parentId,:orders,:valid,:layer)";
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
    public int updateAuth(Map<String, Object> params) {
        String sql="UPDATE  auth SET" +
                "                authName=:authName," +
                "                authCode=:authCode," +
                "                authURL=:authURL," +
                "                type=:type," +
                "                orders=:orders," +
                "                valid=:valid" +
                "                WHERE dbid=:dbid";

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        Iterator<String> iterator = params.keySet().iterator();
        String key=null;
        while(iterator.hasNext()){
            key=iterator.next();
            if((!"parentId".equals(key))&&(!"layer".equals(key))){
                query.setParameter(key,params.get(key));
            }
            //System.out.println(key+params.get(key).toString());

        }

        return query.executeUpdate();
    }

    @Override
    public List<Map<String, Object>> selectDbidByParentId(int param) {
        String sql="select * from auth WHERE parentId=:parentId";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("parentId",param);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return query.list();

    }

    @Override
    public List<Map<String, Object>> selectAuthByLayer(Map<String, Object> param) {
        String sql="select * from auth WHERE layer <= :layer AND dbid!=:dbid AND dbid!=:parentId  AND type='1' ";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("dbid",param.get("dbid"));
        query.setParameter("layer",param.get("layer"));
        query.setParameter("parentId",param.get("parentId"));
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return query.list();
    }

    @Override
    public List<Integer> selectLayerAuth(int dbid) {
        String sql=" select layer from auth WHERE dbid=:dbid";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("dbid",dbid);
        return query.list();
    }

    @Override
    public int moveAuth(Map<String, Object> params) {
        String sql="UPDATE  auth SET" +
                "                parentId=:parentId," +
                "                layer=:layer" +
                "                WHERE dbid=:dbid";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("dbid",params.get("dbid"));
        query.setParameter("layer",params.get("layer"));
        query.setParameter("parentId",params.get("parentId"));
        return query.executeUpdate();
    }

    @Override
    public int deleteAuthById(int param) {
        String sql="DELETE auth FROM auth where dbid=:dbid";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("dbid",param);
        return query.executeUpdate();

    }

    @Override
    public int deleteRole_AuthByAuthId(int param) {
        String sql="DELETE role_auth FROM role_auth where authId=:authId";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("authId",param);
        return query.executeUpdate();
    }
}
