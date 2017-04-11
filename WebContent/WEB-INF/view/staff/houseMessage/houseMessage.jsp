<%--
  Created by IntelliJ IDEA.
  User: zh_ge
  Date: 2017/3/28
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/sys/common.jsp"%>

<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=path%>/bizjs/staff/houseMessage/houseMessage.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="padding:10px;">
    请输入查询内容：<input id="keyword" class="easyui-textbox" style="width:20%;height:30px;">
    请选择查询方式：<select id="queryType" class="easyui-combobox" data-options="editable:false,panelHeight:'48px'"
                    style="width:20%;height:30px;">
    <option value="type">房屋类型</option>
    <option value="address">房屋地址</option>
</select>
    <a href="#" id="house_query" onclick="queryHouseMsg()" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
       style="width: 80px">查 询</a>
    <a href="#" id="add_house" onclick="showHouseEditWin()" class="easyui-linkbutton"
       data-options="iconCls:'icon-add'" style="width: 80px">添 加</a>
</div>

<div data-options="region:'center'">
    <table id="houseGrid"></table>
</div>

<div id="houseEditWin" align="center">
    <input type="hidden" id="dbid">
    </p>
    <br>
    <input id="type" class="easyui-textbox"  label="户型："
           style="width:80%;height:30px">
    <span style="color: red">*</span>
    </p>
    <input id="staffName" class="easyui-textbox" label="管理员工：" readonly="readonly" style="width:58%;height:30px">
    <a href="#" onclick="showSelectStaffWin()" class="easyui-linkbutton" data-options="iconCls:'icon-add'"style="width: 80px">选 择</a>
    <span style="color: red">*</span>
    </p>
    <input id="address" class="easyui-textbox" label="房屋地址：" style="width:80%;height:30px">
    <span style="color: red">*</span>
    </p>
    <input id="price" class="easyui-textbox" label="房屋价格(㎡)：" style="width:80%;height:30px">
    <span style="color: red">*</span>
    </p>
    <input id="env" class="easyui-textbox" label="房屋环境：" multiline="true" style="width:80%;height:120px">
    <span style="color: red">*</span>
    </p>
    <br>
    <a href="#" onclick="saveHouseMsg()" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
       style="width: 80px">保 存</a>
    <a href="#" onclick="closeHouseEditWin()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
       style="width: 80px">取 消</a>

</div>

<div id="selectStaffWin" style="padding:10px;">
    员工姓名：<input id="queryStaffName" class="easyui-textbox" style="width:20%;height:30px;">
    <a href="#" id="staff_query" onclick="queryStaffName()" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
       style="width: 80px">查 询</a>
    <table id="staffGrid"></table>
</div>


<div id="staffMenu" class="easyui-menu" style="width:120px;">
    <div onclick="showHouseUpdateWin()" data-options="iconCls:'icon-add'">编辑</div>
    <div onclick="deleteHouseMsg()" data-options="iconCls:'icon-edit'">删除</div>
    <div onclick='$("#houseGrid").datagrid("load")' data-options="iconCls:'icon-reload'">刷新</div>
</div>

</body>
</html>
