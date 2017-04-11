
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/sys/common.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="<%=path%>/bizjs/sys/role/role.js"></script>

<body class="easyui-layout">
<div data-options="region:'north',border:false" style="padding:10px">
    <input id="queryRoleName" class="easyui-textbox"   data-options="prompt:'角色名称'" style="width:20%;height:30px">
    <input id="queryRoleCode" class="easyui-textbox"   data-options="prompt:'角色编码'" style="width:20%;height:30px">
    <select id="queryValid" class="easyui-combobox"  data-options="editable:false" style="width:20%;height:30px">
        <option value="">全部</option>
        <option value="1">有效</option>
        <option value="2">无效</option>
    </select>
    <a id="role_query"  onclick="queryRole()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px">搜   索</a>
    <a id="role_add" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width: 80px">增   加</a>

    <a id="refresh" class="easyui-linkbutton" style=" float: right;color: red;font: bold 17px/17px Arial;border-radius: 5px" >刷新</a>

</div>
<div data-options="region:'center'">

    <table id="roleGrid" >

    </table>
    <div id="m1" class="easyui-menu" style="width:120px;">
    <div id="role_update" data-options="iconCls:'icon-remove'">编辑</div>
    <div id="role_del" data-options="iconCls:'icon-no'">删除</div>
</div>
    <div id="addCPM">
        <table align="center" style="font: bold 12px/17px Arial;">
            <tr>
                <td height="20px"></td>
                <td></td>
                <b style="display: none;" id="dbid"></b>
            </tr>
            <tr>
                <td>角色名称：</td>
                <td>
                    <input id="roleName" class="easyui-textbox" style="width:150px;height:30px">
                    <b class="emptyS" style="color: red;display: none;font-size: 18px">*</b>
                </td>
            </tr>
            <tr>
                <td>角色编码：</td>
                <td>
                    <input id="roleCode" class="easyui-textbox" style="width:150px;height:30px">
                </td>
            </tr>
            <tr>
                <td>排序：</td>
                <td>
                    <input id="orders" class="easyui-numberbox" style="width:150px;height:30px">

                </td>
            </tr>
            <tr>
                <td>是否有效：</td>
                <td>
                    <select id="valid" class="easyui-combobox"  data-options="editable:false,panelHeight:'80px'" style="width:150px;height:30px">
                        <option value="1">有效</option>
                        <option value="2">无效</option>
                    </select>
                </td>
            </tr>
        </table>
        <div id="footer" style="padding:5px;" align="center">
            <a id="addSubmit" style="border-radius: 25px;color: red;font: bold 12px/17px Arial" class="easyui-linkbutton">&nbsp;&nbsp;&nbsp;&nbsp;保存&nbsp;&nbsp;&nbsp;&nbsp;</a>
            <div style="border-radius: 25px;font: bold 12px/17px Arial" class="easyui-linkbutton" onclick="closeAdd()">&nbsp;&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;&nbsp;</div>
        </div>
    </div>
    <div id="sqCPM">
        <ul id="tt" class="easyui-tree" ></ul>

        <div id="footer1" style="padding:5px;" align="center">
            <a id="sqSubmit" style="border-radius: 25px;color: red;font: bold 12px/17px Arial" class="easyui-linkbutton">&nbsp;&nbsp;&nbsp;&nbsp;保存&nbsp;&nbsp;&nbsp;&nbsp;</a>
            <div style="border-radius: 25px;font: bold 12px/17px Arial" class="easyui-linkbutton" onclick="closeSq()">&nbsp;&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;&nbsp;</div>
        </div>
    </div>
</div>

</body>
</html>
