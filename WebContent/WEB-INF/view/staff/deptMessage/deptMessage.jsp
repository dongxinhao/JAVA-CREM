<%--
  Created by IntelliJ IDEA.
  User: zh_ge
  Date: 2017/3/28
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/sys/common.jsp" %>

<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=path%>/bizjs/staff/deptMessage/deptMessage.js"></script>
</head>
<body class="easyui-layout">

<div data-options="region:'north',border:false" style="padding:10px;">
    <div align="center">
        请输入部门名称：<input id="deptName" class="easyui-textbox" style="width:20%;height:30px;">
        <a href="#" id="house_query" onclick="queryDeptMsg()" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
           style="width: 80px">查 询</a>
        <a href="#" id="add_house" onclick="showDeptEditWin()" class="easyui-linkbutton"
           data-options="iconCls:'icon-add'" style="width: 80px">添 加</a>
    </div>
</div>

<div data-options="region:'center'">
    <table id="deptGrid"></table>
</div>

<div id="deptEditWin" align="center">
    <input type="hidden" id="dbid">
    </p>
    <br>
    <input id="editDeptName" class="easyui-textbox"  label="部门名称："
           style="width:80%;height:30px">
    <span style="color: red">*</span>
    </p>
    <br>
    <input id="deptIntro" class="easyui-textbox" label="部门描述：" multiline="true" style="width:80%;height:120px">
    <span style="color: red">*</span>
    </p>
    <br>
    <a href="#" onclick="saveDeptMsg()" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
       style="width: 80px">保 存</a>
    <a href="#" onclick="closeDeptEditWin()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
       style="width: 80px">取 消</a>

</div>

<div id="deptMenu" class="easyui-menu" style="width:120px;">
    <div onclick="showDeptUpdateWin()" data-options="iconCls:'icon-add'">编辑</div>
    <div onclick="deleteDeptMsg()" data-options="iconCls:'icon-edit'">删除</div>
    <div onclick='$("#deptGrid").datagrid("load")' data-options="iconCls:'icon-reload'">刷新</div>
</div>

</body>
</html>
