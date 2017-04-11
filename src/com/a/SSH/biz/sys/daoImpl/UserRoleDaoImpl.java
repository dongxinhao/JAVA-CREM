package com.a.SSH.biz.sys.daoImpl;

import com.a.SSH.biz.sys.dao.UserRoleDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/27.
 */
@Repository
public class UserRoleDaoImpl implements UserRoleDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Integer> queryUserId(int id) {
        String sql="SELECT roleId FROM user_role WHERE userId=:userId";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("userId",id);
        return query.list();
    }

    @Override
    public List<Map<String, Object>> selectValidUser() {
        String sql="select dbid, roleName,roleCode from role WHERE valid='1'" +
                "        ORDER BY orders";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

    @Override
    public int deleteByUserId(int id) {
        String sql="DELETE user_role from user_role where userId=:userId";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("userId",id);

        return query.executeUpdate();
    }

    @Override
    public int addUserRole(List<Map<String, Object>> list) {
        StringBuilder sql=new StringBuilder("INSERT INTO user_role (userId,roleId) VALUES");
        Map<String, Object> one = new HashMap<String, Object>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < (list.size() - 1)) {
                    Map<String, Object> map = list.get(i);
                    sql.append("(:userId" + i + "," + ":roleId" + i + "),");
                    one.put("userId" + i, map.get("userId"));
                    one.put("roleId" + i, map.get("roleId"));
                } else {
                    Map<String, Object> map = list.get(i);
                    sql.append("(:userId" + i + "," + ":roleId" + i + ")");
                    one.put("userId" + i, map.get("userId"));
                    one.put("roleId" + i, map.get("roleId"));
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
