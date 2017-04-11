package com.a.SSH.biz.sys.daoImpl;

import com.a.SSH.biz.sys.dao.RoleDao;
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
 * Created by Administrator on 2017/3/27.
 */
@Repository
public class RoleDaoImpl implements RoleDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public int addRole(Map<String, Object> params) {
        String sql=" INSERT into role(roleName,roleCode,orders,valid)" +
                "         VALUES (:roleName,:roleCode,:orders,:valid)";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        Iterator<String> iterator = params.keySet().iterator();
        String key=null;
        while(iterator.hasNext()){
            key=iterator.next();
            if(!("dbid".equals(key))){
                query.setParameter(key,params.get(key));
            }

        }

        return query.executeUpdate();

    }

    @Override
    public int updateRole(Map<String, Object> params) {
        String sql="UPDATE  role SET roleName=:roleName," +
                    "roleCode=:roleCode,orders=:orders,valid=:valid" +
                    "                WHERE dbid=:dbid";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        Iterator<String> iterator = params.keySet().iterator();
        String key=null;
        //String userName = (String)params.get("userName");
        //System.out.println(userName.toString());
        while(iterator.hasNext()){
            key=iterator.next();
                //System.out.println(key.toString()+params.get(key).toString());
                query.setParameter(key,params.get(key));
        }
        return query.executeUpdate();
    }

    @Override
    public List<Map<String, Object>> queryRoleList(Map<String, Object> params) {
        StringBuilder sb=new StringBuilder("SELECT * from role WHERE 1=1");
        List<String> validParams=new  ArrayList<String>();

        if(!StringUtils.isEmpty(params.get("roleName"))){
            sb.append("  "+"and roleName LIKE CONCAT(CONCAT('%',:roleName),'%')");
            validParams.add("roleName");
        }
        if(!StringUtils.isEmpty(params.get("roleCode"))){
            sb.append("  "+"and roleCode LIKE CONCAT(CONCAT('%',:roleCode),'%')");
            validParams.add("roleCode");
        }
        if(!StringUtils.isEmpty(params.get("valid"))){
            sb.append("  "+"and valid= :valid");
            validParams.add("valid");
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

        StringBuilder sb=new StringBuilder("SELECT count(dbid) from role WHERE 1=1");
        List<String> validParams=new ArrayList<String>();

        if(!StringUtils.isEmpty(params.get("roleName"))){
            sb.append("  "+"and roleName LIKE CONCAT(CONCAT('%',:roleName),'%')");
            validParams.add("roleName");
        }
        if(!StringUtils.isEmpty(params.get("roleCode"))){
            sb.append("  "+"and roleCode LIKE CONCAT(CONCAT('%',:roleCode),'%')");
            validParams.add("roleCode");
        }
        if(!StringUtils.isEmpty(params.get("valid"))){
            sb.append("  "+"and valid= :valid");
            validParams.add("valid");
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
    public List<Integer> queryRepetition(Map<String, Object> params) {
        String sql="SELECT dbid FROM role WHERE dbid!=:dbid AND roleName=:roleName";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("dbid",params.get("dbid"));
        query.setParameter("roleName",params.get("roleName"));
        query.list();
        return query.list();
    }

    @Override
    public List<Integer> queryAddRepetition(Map<String, Object> params) {
        String sql="SELECT dbid FROM role WHERE roleName=:roleName";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("roleName",params.get("roleName"));
        query.list();
        return query.list();
    }

    @Override
    public int deleteRoleById(int id) {
        String sql="DELETE role FROM role WHERE dbid=:dbid";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("dbid",id);
        return query.executeUpdate();
    }

    @Override
    public int deleteRole_AuthByRoleId(int roleId) {
        String sql="DELETE role_auth FROM role_auth WHERE roleId=:roleId";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("roleId",roleId);
        return query.executeUpdate();
    }

    @Override
    public int deleteUser_RoleByRoleId(int roleId) {
        String sql="DELETE user_role FROM user_role WHERE roleId=:roleId";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("roleId",roleId);
        return query.executeUpdate();
    }
}
