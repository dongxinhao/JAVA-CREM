<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/sys/common.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="<%=path%>/bizjs/customer/cttype.js"></script>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="padding:10px">
    请输入查询内容:
    <input id="queryCustName" class="easyui-textbox"   data-options="prompt:'请输入查询内容'" style="width:20%;height:30px">
    <a id="type_query" onclick="query()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px">搜   索</a>
    <a  id="type_add"  class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width: 80px">增   加</a>
    <a id="refresh" class="easyui-linkbutton" style=" float: right;color: red;font: bold 17px/17px Arial;border-radius: 5px" >刷新</a>
</div>
<div data-options="region:'center',title:''">
    <table id="typeGrid" >
    </table>
    <div id="m1" class="easyui-menu" style="width:120px;">
        <div id="type_update"  data-options="iconCls:'icon-remove'">编辑</div>
        <div id="type_del"  data-options="iconCls:'icon-no'">删除</div>
    </div>
    <div id="addCPM">
        <table align="center" style="font: bold 12px/17px Arial;">
            <tr>
                <td height="20px"></td>
                <td></td>
                <b style="display: none;" id="dbid"></b>
            </tr>
            <tr>
                <td>客户类型：</td>
                <td>
                    <input id="ctType" class="easyui-textbox"  iconWidth="28" style="width:150px;height:30px;padding:10px;">
                    <b class="emptyS" style="color: red;display: none;font-size: 18px">*</b>
                </td>
            </tr>
        </table>
        <div id="footer" style="padding:5px;" align="center">
            <a id="addType" style="border-radius: 25px;color: red;font: bold 12px/17px Arial" class="easyui-linkbutton">&nbsp;&nbsp;&nbsp;&nbsp;保存&nbsp;&nbsp;&nbsp;&nbsp;</a>
            <div style="border-radius: 25px;font: bold 12px/17px Arial" class="easyui-linkbutton" onclick="closeAdd()">&nbsp;&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;&nbsp;</div>
        </div>
    </div>
    <div id="sqCPM">
        <table id="tt" >
        </table>
        <div id="footer1" style="padding:5px;" align="center">
            <a id="sqSubmit" style="border-radius: 25px;color: red;font: bold 12px/17px Arial" class="easyui-linkbutton">&nbsp;&nbsp;&nbsp;&nbsp;确认&nbsp;&nbsp;&nbsp;&nbsp;</a>
            <div style="border-radius: 25px;font: bold 12px/17px Arial" class="easyui-linkbutton" onclick="closeSq()">&nbsp;&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;&nbsp;</div>
        </div>
    </div>
</div>
</body>
</html>
