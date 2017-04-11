<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/sys/common.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="<%=path%>/bizjs/staff/notice/notice.js"></script>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="padding:10px">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    查询内容：<input id="queryNoticeContent" class="easyui-textbox"    style="width:20%;height:30px">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    查询类型：<select id="queryMode" class="easyui-combobox"  data-options="editable:false" style="width:10%;height:30px">
        <option value="1">公告主题</option>
        <option value="2">公告内容</option>
    </select>
    <a id="user_query" onclick="query()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px">搜   索</a>

    <a  id="notice_add"  class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width: 80px">增   加</a>
</div>
<div data-options="region:'center',title:''">
    <table id="noticeGrid" >
    </table>
    <div id="meu1" class="easyui-menu" style="width:120px;">
        <div id="notice_update"  data-options="iconCls:'icon-remove'">编辑</div>
        <div id="notice_del"  data-options="iconCls:'icon-no'">删除</div>
    </div>
    <div id="addNOT">
        <table align="center" style="font: bold 12px/17px Arial;">
            <tr>
            <td>
                <input type="hidden" id="dbid">
            </td>
            </tr>
            <tr>
                <td style="width: 100px">公告主题：</td>
                <td>
                    <input id="theme" class="easyui-textbox"  iconWidth="10" style="width:150px;height:30px;padding:10px;">
                    <b class="emptyS" style="color: red;display: none;font-size: 18px">*</b>
                </td>
            </tr>
            <tr>
                <td>结束时间：</td>
                <td>
                    <input class="easyui-datetimebox" name="birthday" id="end"
                           data-options="required:true,showSeconds:false" value="3/4/2010 2:3" style="width:150px">
                </td>
            </tr>
            <tr>
                <td>公告内容：</td>
                <td>
                    <input id="content" class="easyui-textbox" multiline="true"  iconWidth="10" style="width:300px;height:200px;padding:10px">
                    <b class="emptyS" style="color: red;display: none;font-size: 18px">*</b>

                </td>
            </tr>
        </table>
        <div id="footer" style="padding:5px;" align="center">
            <a id="addSubmit" style="border-radius: 25px;color: red;font: bold 12px/17px Arial" class="easyui-linkbutton">&nbsp;&nbsp;&nbsp;&nbsp;保存&nbsp;&nbsp;&nbsp;&nbsp;</a>
            <div style="border-radius: 25px;font: bold 12px/17px Arial" class="easyui-linkbutton" onclick="closeAdd()">&nbsp;&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;&nbsp;</div>
        </div>
    </div>
    <%-- <div id="sqCPM">
         <table id="tt" >

         </table>

         <div id="footer1" style="padding:5px;" align="center">
             <a id="sqSubmit" style="border-radius: 25px;color: red;font: bold 12px/17px Arial" class="easyui-linkbutton">&nbsp;&nbsp;&nbsp;&nbsp;保存&nbsp;&nbsp;&nbsp;&nbsp;</a>
             <div style="border-radius: 25px;font: bold 12px/17px Arial" class="easyui-linkbutton" onclick="closeSq()">&nbsp;&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;&nbsp;</div>
         </div>
     </div>--%>
</div>
</body>
</html>
