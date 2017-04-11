package com.a.SSH.biz.sys.daoImpl;

import com.a.SSH.biz.sys.dao.UserDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Map<String,Object>> queryByUserName(String name) {
        String sql="select * from user where userName= :userName";
        Session session = sessionFactory.getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setParameter("userName",name);
        System.out.println(name);
        List<Map<String,Object>> list = query.list();
        return list;
    }

    @Override
    public List<Map<String, Object>> queryUserList(Map<String, Object> params) {
        StringBuilder sb=new StringBuilder("SELECT * from user WHERE 1=1");
        List<String> validParams=new  ArrayList<String>();

        if(!StringUtils.isEmpty(params.get("userName"))){
            sb.append("  "+"and userName LIKE CONCAT(CONCAT('%',:userName),'%')");
            validParams.add("userName");
        }
        if(!StringUtils.isEmpty(params.get("realName"))){
            sb.append("  "+"and realName LIKE CONCAT(CONCAT('%',:realName),'%')");
            validParams.add("realName");
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
        StringBuilder sb=new StringBuilder("SELECT count(dbid) from user WHERE 1=1");
        List<String> validParams=new  ArrayList<String>();

        if(!StringUtils.isEmpty(params.get("userName"))){
            sb.append("  "+"and userName LIKE CONCAT(CONCAT('%',:userName),'%')");
            validParams.add("userName");
        }
        if(!StringUtils.isEmpty(params.get("realName"))){
            sb.append("  "+"and realName LIKE CONCAT(CONCAT('%',:realName),'%')");
            validParams.add("realName");
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
    public int addUser(Map<String, Object> params) {
    String sql="INSERT into user(userName,realName,password,valid," +
            "age,userSex,education,marriage,address,TEL," +
            "nation,userDept,email)" +

            " "+"VALUES (:userName,:realName,:password,:valid," +
            ":age,:userSex,:education,:marriage,:address,:TEL," +
            ":nation,:userDept,:email)";
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
    public int updateUser(Map<String, Object> params) {
        String sql="UPDATE  user SET " +
                "userName= :userName,realName=:realName," +
                "password= :password,valid= :valid," +

                "age= :age,userSex= :userSex," +
                "education= :education,marriage= :marriage," +
                "address= :address,TEL= :TEL," +
                "nation= :nation,userDept= :userDept," +
                "email= :email" +
                " "+"WHERE dbid= :dbid";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        Iterator<String> iterator = params.keySet().iterator();
        String key=null;
        while(iterator.hasNext()){
            key=iterator.next();
            //System.out.println(key+params.get(key).toString());
            query.setParameter(key,params.get(key));
        }

        return query.executeUpdate();
    }

    @Override
    public List<Integer> queryUpdateRepetition(Map<String, Object> params) {
        String sql="SELECT dbid FROM user WHERE dbid!= :dbid AND userName= :userName";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("dbid",params.get("dbid"));
        query.setParameter("userName",params.get("userName"));
        query.list();
        return query.list();
    }

    @Override
    public List<Integer> queryAddRepetition(Map<String, Object> params) {

        String sql="SELECT dbid FROM user WHERE userName= :userName";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("userName",params.get("userName"));
        List<Integer> list = query.list();
        //System.out.println(list.toString());
        return list;
    }

    @Override
    public int deleteUserById(int id) {
        String sql="DELETE user FROM user WHERE dbid= :dbid";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("dbid",id);
        return query.executeUpdate();
    }

    @Override
    public int deleteUser_RoleByUserId(int userId) {
        String sql="DELETE user_role FROM user_role WHERE userId= :userId";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("userId",userId);
        return query.executeUpdate();
    }

    @Override
    public List<Map<String, Object>> queryAuthByUserId(int id) {
        String sql="select *,authName as text,dbid as id,authURL as url from auth where dbid in" +
                "(select authId from role_auth where roleId IN " +
                "(select roleId from user_role where userId= :userId)) " +
                "and type='1' AND valid='1' order BY orders";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setParameter("userId",id);

        return query.list();
    }

    @Override
    public List<Map<String,Object>> getResourceByUserId(int id) {
        String sql = "SELECT authCode from auth WHERE dbid in" +
                "(SELECT authId from role_auth WHERE roleId in" +
                "(select roleId from user_role WHERE userId=:userId))" +
                "and type='2'" +
                "order BY orders";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setParameter("userId", id);

        return query.list();
    }


    @Override
    public int updateUserDept(String oldDeptName, String newDeptName) {
        String sql = "update user set userDept = :newDeptName where userDept = :oldDeptName";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("newDeptName",newDeptName);
        query.setParameter("oldDeptName",oldDeptName);
        return query.executeUpdate();
    }



    @Override
    public List<Map<String, Object>> getAllUsers() {
        String sql="select userName ,dbid from user order by userName";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String,Object>> list = query.list();
        return list;
    }

    @Override
    public String queryUserNameById(int dbid) {
        String sql = "select userName from user where dbid = :dbid";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("dbid",dbid);
        return (String)query.uniqueResult();
    }

}
