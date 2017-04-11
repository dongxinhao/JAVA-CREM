<%--
  Created by IntelliJ IDEA.
  User: lyz
  Date: 2017/3/28
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/sys/common.jsp"%>
<html>
<head>
    <title>联系人</title>
</head>
<script type="text/javascript" src="<%=path%>/bizjs/customer/ctContact.js"></script>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="padding:10px">
    请输入查询内容：<input id="queryText" class="easyui-textbox" style="width:20%;height:30px">
    请选择查询方式：<select id="queryType" class="easyui-combobox"  data-options="editable:false" style="width:20%;height:30px">
        <option value="ctn">联系人姓名</option>
        <option value="conn">客户姓名</option>
    </select>
    <a id="contactQuery" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px">搜   索</a>
    <a  id="contactAdd"  class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width: 80px">增   加</a>
    <a href="<%=path%>/ctcontact/exportContact" class="easyui-linkbutton" style=" float: right;color: red;font: bold 17px/17px Arial;border-radius: 5px" >导出EXCEL</a>
    <a id="contactFresh" class="easyui-linkbutton" style=" float: right;color: red;font: bold 17px/17px Arial;border-radius: 5px" >刷新</a>
</div>
<div data-options="region:'center'">
    <table id="contactGrid" ></table>
    <div id="m1" class="easyui-menu" style="width:120px;">
        <div id="contactUpdate"  data-options="iconCls:'icon-remove'">编辑</div>
        <div id="contactDelete"  data-options="iconCls:'icon-no'">删除</div>
    </div>

    <div id="contactAddWin">
        <table align="center" style="font: bold 12px/17px Arial;">
            <tr>
                <td height="20px"></td>
                <td></td>
                <b style="display: none;" id="dbid"></b>
            </tr>
            <tr>
                <td>所属客户：</td>
                <td>
                    <input id="ctName" class="easyui-combobox" required="true" iconWidth="28" style="width:150px;height:30px;padding:10px;">
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系人姓名：</td>
                <td>
                    <input id="conName" class="easyui-textbox" required="true" iconWidth="28" style="width:150px;height:30px;padding:10px;">
                </td>
            </tr>
            <tr>
                <td>性别：</td>
                <td>
                    <select id="conSex" class="easyui-combobox"  data-options="editable:false,panelHeight:'80px'" style="width:150px;height:30px">
                        <option value="1">男</option>
                        <option value="2">女</option>
                    </select>
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年龄：</td>
                <td>
                    <input id="conAge" class="easyui-textbox"  iconWidth="28" style="width:150px;height:30px;padding:10px;">
                </td>
            </tr>
            <tr>
                <td>联系方式：</td>
                <td>
                    <input id="conTel" class="easyui-textbox" required="true" iconWidth="28" style="width:150px;height:30px;padding:10px;">
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;职业：</td>
                <td>
                    <input id="conJob" class="easyui-textbox"  iconWidth="28" style="width:150px;height:30px;padding:10px;">
                </td>
            </tr>
            <tr>
                <td>与客户关系：</td>
                <td>
                    <input id="ctCon" class="easyui-textbox"  iconWidth="28" style="width:150px;height:30px;padding:10px">
                </td>
                <td></td>
                <td></td>
            </tr>

        </table>
        <div id="footer" style="padding:5px;" align="center">
            <a id="contactSubmit" style="border-radius: 25px;color: red;font: bold 12px/17px Arial" class="easyui-linkbutton">&nbsp;&nbsp;&nbsp;&nbsp;保存&nbsp;&nbsp;&nbsp;&nbsp;</a>
            <div id="closeWin" style="border-radius: 25px;font: bold 12px/17px Arial" class="easyui-linkbutton">&nbsp;&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;&nbsp;</div>
        </div>
    </div>
</div>
</body>
</html>
