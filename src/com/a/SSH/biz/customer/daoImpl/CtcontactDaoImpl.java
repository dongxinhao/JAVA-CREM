package com.a.SSH.biz.customer.daoImpl;

import com.a.SSH.biz.customer.dao.CtcontactDao;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by lyz on 2017/3/28.
 */
@Repository
public class CtcontactDaoImpl implements CtcontactDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Map<String, Object>> getAllContact(Map<String, Object> params) {
        StringBuilder sql=new StringBuilder("select * from ctcontact");
        if (params.get("type").equals("ctn")){
            sql.append(" where ctName like '%"+params.get("text")+"%'");
        }else {
            sql.append(" where conName like '%"+params.get("text")+"%'");
        }
        sql.append(" limit "+params.get("start")+", "+params.get("rows"));
        System.out.println(params.get("start"));
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

    @Override
    public int countAllContact(Map<String, Object> params) {
        StringBuilder sql=new StringBuilder("select count(*) from ctcontact");
        if (params.get("type").equals("ctn")){
            sql.append(" where ctName like '%"+params.get("text")+"%'");
        }else {
            sql.append(" where conName like '%"+params.get("text")+"%'");
        }
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql.toString());
        List list = query.list();
        return Integer.parseInt(list.get(0).toString());
    }

    @Override
    public List<Map<String, Object>> getAllCustomer() {
        String sql="select dbid,ctName from customer";
        Session session = sessionFactory.getCurrentSession();
        Query query=session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

    @Override
    public int addContact(Map<String, Object> params) {
        String sql="insert into ctcontact (ctName,conName,conSex,conAge,conTel,conJob,ctCon)"+
                " values (:ctName,:conName,:conSex,:conAge,:conTel,:conJob,:ctCon)";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        Iterator<String> iterator = params.keySet().iterator();
        String key=null;
        while (iterator.hasNext()) {
            key = iterator.next();
            if (!key.equals("dbid")) {
                query.setParameter(key, params.get(key));
            }
        }
        return query.executeUpdate();
    }

    @Override
    public int updateContact(Map<String, Object> params) {
        String sql="update ctcontact set ctName=:ctName,conName=:conName,conSex=:conSex,conAge=:conAge,conTel=:conTel,conJob=:conJob,ctCon=:ctCon where dbid=:dbid";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        Iterator<String> iterator = params.keySet().iterator();
        String key=null;
        while (iterator.hasNext()) {
            key = iterator.next();
            query.setParameter(key, params.get(key));

        }
        return query.executeUpdate();
    }

    @Override
    public int deleteContact(int dbid) {
        String sql="delete from ctcontact where dbid=:dbid";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setParameter("dbid",dbid);
        return query.executeUpdate();
    }

    @Override
    public void outExecl(List<Map<String,Object>> list) {
        WritableWorkbook wwb=null;
        try {
            /*String fileName="D://信息表.xls";
            File file=new File(fileName);
            if(!file.exists()){
                file.createNewFile();
            }*/
            HttpServletResponse response = ServletActionContext.getResponse();
            response.addHeader("Content-Disposition", "attachment;filename=contact.xlsx");
            response.setContentType("application/msexcel");
            wwb = Workbook.createWorkbook(response.getOutputStream());
            WritableSheet ws = wwb.createSheet("联系人表", 0);
            Label labelDbid= new Label(0, 0, "编号(dbid)");//表示第
            Label labelCtName= new Label(1, 0, "所属客户(ctName)");
            Label labelConName= new Label(2, 0, "联系人姓名(conName)");
            Label labelConSex= new Label(3, 0, "性别(conSex)");
            Label labelConAge= new Label(4, 0, "年龄(conAge)");
            Label labelConTel= new Label(5, 0, "电话(conTel)");
            Label labelConJob= new Label(6, 0, "职业(conJob)");
            Label labelCtcon= new Label(7, 0, "关系(ctCon)");
            ws.addCell(labelDbid);
            ws.addCell(labelCtName);
            ws.addCell(labelConName);
            ws.addCell(labelConSex);
            ws.addCell(labelConAge);
            ws.addCell(labelConTel);
            ws.addCell(labelConJob);
            ws.addCell(labelCtcon);
            for (int i = 0; i < list.size(); i++) {
                Label labelDbid_i= new Label(0, i+1, list.get(i).get("dbid").toString()+"");
                Label labelCtName_i= new Label(1, i+1, list.get(i).get("ctName").toString()+"");
                Label labelConName_i= new Label(2, i+1, list.get(i).get("conName").toString()+"");
                Label labelConSex_i= new Label(3, i+1, list.get(i).get("conSex").toString()+"");
                Label labelConAge_i= new Label(4, i+1, list.get(i).get("conAge").toString()+"");
                Label labelConTel_i= new Label(5, i+1, list.get(i).get("conTel").toString()+"");
                Label labelConJob_i= new Label(6, i+1, list.get(i).get("conJob").toString()+"");
                Label labelCtcon_i= new Label(7, i+1, list.get(i).get("ctCon").toString()+"");
                ws.addCell(labelDbid_i);
                ws.addCell(labelCtName_i);
                ws.addCell(labelConName_i);
                ws.addCell(labelConSex_i);
                ws.addCell(labelConAge_i);
                ws.addCell(labelConTel_i);
                ws.addCell(labelConJob_i);
                ws.addCell(labelCtcon_i);
            }

            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Map<String, Object>> getAlllist() {
        String sql="select * from ctcontact";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

}
