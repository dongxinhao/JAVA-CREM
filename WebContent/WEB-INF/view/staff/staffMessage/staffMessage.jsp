<%@ page import="java.util.UUID" %><%--
  Created by IntelliJ IDEA.
  User: zh_ge
  Date: 2017/3/28
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/sys/common.jsp" %>
<html>
<head>
    <script type="text/javascript" src="<%=path%>/bizjs/staff/staffMessage/staffMessage.js?version=<%=UUID.randomUUID().toString()%>"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="padding:10px">
    请输入查询内容：<input id="queryStaffName" class="easyui-textbox"   style="width:20%;height:30px;margin-left: 50px">
    请选择查询方式：<select id="queryType" class="easyui-combobox"  data-options="editable:false,panelHeight:'90px'" style="width:20%;height:30px;margin-left: 50px">
        <option value="">员工姓名</option>
        <option value="">部门名称</option>
        <option value="">角色名称</option>
        <option value="">员工学历</option>
    </select>
    <a href="#" id="staff_query" onclick="queryStaff()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px">查   询</a>
</div>

<div data-options="region:'center'">
    <table id="staffGrid"></table>
</div>

<%--<div id="roleEditWin"   align="center">--%>
    <%--<input type="hidden" id="dbid">--%>
    <%--</p>--%>
    <%--<input id="roleName" class="easyui-textbox" &lt;%&ndash;data-options="events:{blur:checkRoleName}"&ndash;%&gt; label="角色名称："  style="width:80%;height:30px">--%>
    <%--<span style="color: red">*</span>--%>
    <%--</p>--%>
    <%--<input id="roleCode" class="easyui-textbox"  label="角色编码："  style="width:80%;height:30px">--%>
    <%--<span style="color: red">*</span>--%>
    <%--</p>--%>
    <%--<select id="valid" class="easyui-combobox" label="是否有效：" data-options="editable:false,panelHeight:'48px'" style="width:80%;height:30px">--%>
        <%--<option value="1">有效</option>--%>
        <%--<option value="0">无效</option>--%>
    <%--</select>--%>
    <%--<span style="color: red">*</span>--%>
    <%--</p>--%>
    <%--<input id="orders" class="easyui-numberbox"  label="排序："  style="width:80%;height:30px">--%>
    <%--<span style="color: red">*</span>--%>
    <%--</p>--%>
    <%--<a href="#" onclick="saveRoleInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width: 80px">保   存</a>--%>
    <%--<a href="#" onclick="closeRoleEditWin()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"  style="width: 80px">取   消</a>--%>

<%--</div>--%>

<%--<div id="grantWin"   style="padding:10px;">--%>
    <%--<input type="hidden" id="roleId">--%>
    <%--<ul id="authTree"  data-options=""></ul>--%>
<%--</div>--%>

<%--<div id="footer"   align="center">--%>
    <%--<a href="#" onclick="saveGrantInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width: 80px">保   存</a>--%>
    <%--<a href="#" onclick="closeGranWin()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"  style="width: 80px">取   消</a>--%>
<%--</div>--%>

<div id="staffMenu" class="easyui-menu" style="width:120px;">
    <div onclick="showStaffAddWin()" data-options="iconCls:'icon-add'">增加子节点</div>
    <div onclick="showStaffEditWin()" data-options="iconCls:'icon-edit'">编辑本节点</div>
    <div onclick='$("#authTree").treegrid("load")' data-options="iconCls:'icon-reload'">刷新</div>
</div>

</body>
</html>
