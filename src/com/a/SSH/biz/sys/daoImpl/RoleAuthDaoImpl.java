package com.a.SSH.biz.sys.daoImpl;

import com.a.SSH.biz.sys.dao.RoleAuthDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by Administrator on 2017/3/27.
 */
@Repository
public class RoleAuthDaoImpl implements RoleAuthDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Integer> queryRoleId(int id) {
        String sql = "SELECT authId FROM role_auth WHERE roleId=:roleId";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("roleId", id);
        return query.list();
    }

    @Override
    public List<Map<String, Object>> selectValidAuth() {
        String sql = "select dbid as id,authName AS text,parentId from auth WHERE valid='1' ";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE role_auth from role_auth where roleId=:roleId";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("roleId", id);
        return query.executeUpdate();
    }

    @Override
    public int addRoleAuth(List<Map<String, Object>> list) {
        StringBuilder sql = new StringBuilder("INSERT INTO role_auth (roleId,authId) VALUES ");
        Map<String, Object> one = new HashMap<String, Object>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < (list.size() - 1)) {
                    Map<String, Object> map = list.get(i);
                    sql.append("(:roleId" + i + "," + ":authId" + i + "),");
                    one.put("roleId" + i, map.get("roleId"));
                    one.put("authId" + i, map.get("authId"));
                } else {
                    Map<String, Object> map = list.get(i);
                    sql.append("(:roleId" + i + "," + ":authId" + i + ")");
                    one.put("roleId" + i, map.get("roleId"));
                    one.put("authId" + i, map.get("authId"));
                }
            }
        }
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql.toString());

        Iterator<String> iterator = one.keySet().iterator();
        String key=null;
        while(iterator.hasNext()){
            key=iterator.next();
            //System.out.println(key+params.get(key).toString());
            query.setParameter(key,one.get(key));
        }
        return query.executeUpdate();

    }



}
