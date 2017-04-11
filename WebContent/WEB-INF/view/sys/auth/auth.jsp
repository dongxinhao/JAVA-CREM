<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/sys/common.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="<%=path%>/bizjs/sys/auth/auth.js"></script>
<body>

<table id="authTree" style="font: bold 12px/17px Arial">
</table>


<div id="m1" class="easyui-menu" style="width:120px;">
    <div id="auth_add" data-options="iconCls:'icon-add'">添加</div>
    <div id="auth_update" data-options="iconCls:'icon-remove'">更改</div>
    <div id="auth_del" data-options="iconCls:'icon-no'">删除</div>
    <div id="auth_move" data-options="iconCls:'icon-back'">移动</div>
    <div id="authSX" data-options="iconCls:'icon-reload'">刷新</div>
</div>
<%--添加--%>
<div id="addCPM">
    <table align="center" style="font: bold 12px/17px Arial;">

        <tr>
            <td height="20px"></td>
            <td></td>
            <b style="display: none;" id="addDbid"></b>
            <b style="display: none;" id="ParentId"></b>
        </tr>
        <tr id="authParent">
            <td>上级模块：</td>
            <td><span id="auth1"></span></td>
        </tr>
        <tr>
            <td id="authSelf">添加层级：</td>
            <td id="authUpdate" style="display: none"></td>
            <td><span id="layer"></span></td>
        </tr>
        <tr>
            <td>权限名称：</td>
            <td>
                <input id="authName" class="easyui-textbox" style="width:200px;height:30px">
                <b class="emptyS" style="color: red;display: none">*</b>
            </td>
        </tr>
        <tr>
            <td>权限编码：</td>
            <td>
                <input id="authCode" class="easyui-textbox" style="width:200px;height:30px">
            </td>
        </tr>
        <tr>
            <td>URL：</td>
            <td>
                <input id="authURL" class="easyui-textbox" style="width:200px;height:30px">

            </td>
        </tr>
        <tr>
            <td>排序：</td>
            <td>
                <input id="orders" class="easyui-numberbox" style="width:200px;height:30px">

            </td>
        </tr>
        <tr>
            <td>类型：</td>
            <td>
                <select id="type" class="easyui-combobox" data-options="editable:false,panelHeight:'50px'"
                        style="width:200px;height:30px">
                    <option value="1">模块</option>
                    <option value="2" style="color: red">资源</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>是否有效：</td>
            <td>
                <select id="valid" class="easyui-combobox" data-options="editable:false,panelHeight:'50px'"
                        style="width:200px;height:30px">
                    <option value="1">显示</option>
                    <option value="2">隐藏</option>
                </select>
            </td>
        </tr>
    </table>
    <div id="footer" style="padding:5px;" align="center">
        <a id="addSubmit" style="border-radius: 25px;color: red;font: bold 12px/17px Arial"
           class="easyui-linkbutton">&nbsp;&nbsp;&nbsp;&nbsp;保存&nbsp;&nbsp;&nbsp;&nbsp;</a>
        <div style="border-radius: 25px;font: bold 12px/17px Arial" class="easyui-linkbutton" id="close1">&nbsp;&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;&nbsp;</div>
    </div>
</div>
<div id="moveCPM">
    <table align="center" style="font: bold 12px/17px Arial;">
        <tr style="height: 30px">
            <td></td>
        </tr>
        <tr>
            <td style="width: 80px">父级权限名：</td>
            <td>
                <input id="moveCombobox" class="easyui-combobox" style="width:150px;">
                <b class="emptyS" style="color: red;display: none">*</b>

            </td>
        </tr>
    </table>
    <div id="footer1" style="padding:5px;" align="center">
        <a id="moveSubmit" style="border-radius: 25px;color: red;font: bold 12px/17px Arial"
           class="easyui-linkbutton">&nbsp;&nbsp;&nbsp;&nbsp;保存&nbsp;&nbsp;&nbsp;&nbsp;</a>
        <div style="border-radius: 25px;font: bold 12px/17px Arial" class="easyui-linkbutton" id="close2">&nbsp;&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;&nbsp;</div>
    </div>
</div>

</body>
</html>
