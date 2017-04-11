<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/sys/common.jsp"%>

<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="<%=path%>/bizjs/sys/user/user.js"></script>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="padding:10px">
    <input id="queryUserName" class="easyui-textbox"   data-options="prompt:'用户名称'" style="width:20%;height:30px">
    <input id="queryRealName" class="easyui-textbox"   data-options="prompt:'真实姓名'" style="width:20%;height:30px">
    <select id="queryValid" class="easyui-combobox"  data-options="editable:false" style="width:20%;height:30px">
        <option value="">全部</option>
        <option value="1">正常</option>
        <option value="2">冻结</option>
    </select>
    <a id="user_query" onclick="query()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px">搜   索</a>
    <a  id="user_add"  class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width: 80px">增   加</a>
    <a id="refresh" class="easyui-linkbutton" style=" float: right;color: red;font: bold 17px/17px Arial;border-radius: 5px" >刷新</a>
</div>
<div data-options="region:'center',title:''">
    <table id="userGrid">
    </table>
    <div id="m1" class="easyui-menu" style="width:120px;">
        <div id="user_update"  data-options="iconCls:'icon-remove'">编辑</div>
        <div id="user_del"  data-options="iconCls:'icon-no'">删除</div>
    </div>
    <div id="addCPM">
        <table align="center" style="font: bold 12px/17px Arial;">
            <tr>
                <td height="20px"></td>
                <td></td>
                <b style="display: none;" id="dbid"></b>
            </tr>
            <tr>
                <td>用户名称：</td>
                <td>
                    <input id="userName" class="easyui-textbox"  iconWidth="28" style="width:150px;height:30px;padding:10px;">
                    <b class="emptyS" style="color: red;display: none;font-size: 18px">*</b>
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学　　历：</td>
                <td>
                    <input id="education" class="easyui-textbox"  iconWidth="28" style="width:150px;height:30px;padding:10px;">
                </td>
            </tr>
            <tr>
                <td>真实姓名：</td>
                <td>
                    <input id="realName" class="easyui-textbox"  iconWidth="28" style="width:150px;height:30px;padding:10px;">
                    <b class="emptyS" style="color: red;display: none;font-size: 18px">*</b>
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;婚　　姻：</td>
                <td>
                    <select id="marriage" class="easyui-combobox"  data-options="editable:false,panelHeight:'80px'" style="width:150px;height:30px">
                        <option value="1">未婚</option>
                        <option value="2">已婚</option>
                        <option value="3">离异</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>密　　码：</td>
                <td>
                    <input id="password" class="easyui-passwordbox"  iconWidth="28" style="width:150px;height:30px;padding:10px">
                    <b class="emptyS" style="color: red;display: none;font-size: 18px">*</b>

                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;住　　址：</td>
                <td>
                    <input id="address" class="easyui-textbox"  iconWidth="28" style="width:150px;height:30px;padding:10px">

                </td>
            </tr>
            <tr>
                <td>密　　码：</td>
                <td>
                    <input id="password1" class="easyui-passwordbox"  iconWidth="28" style="width:150px;height:30px;padding:10px">
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手　　机：</td>
                <td>
                    <input id="TEL" class="easyui-numberbox"  iconWidth="28" style="width:150px;height:30px;padding:10px">
                </td>
            </tr>
            <tr>
                <td>性　　别：</td>
                <td>
                    <select id="userSex" class="easyui-combobox"  data-options="editable:false,panelHeight:'80px'" style="width:150px;height:30px">
                        <option value="1">男</option>
                        <option value="2">女</option>
                    </select>
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;部　　门：</td>
                <td>
                    <input id="userDept" class="easyui-textbox" readonly="readonly"  iconWidth="28" style="width:70px;height:30px;">
                    <a href="#" onclick="showSelectDeptWin()" class="easyui-linkbutton" data-options="iconCls:'icon-add'"style="width: 80px">选 择</a>
                </td>
            </tr>
            <tr>
                <td>民　　族：</td>
                <td>
                    <input id="nation" class="easyui-textbox"  iconWidth="28" style="width:150px;height:30px;padding:10px;">
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮　　箱：</td>
                <td>
                    <input id="email" class="easyui-textbox"  iconWidth="28"  labelPosition="top" data-options="prompt:'Enter a email address...',validType:'email'" style="width:150px;height:30px;padding:10px;">
                </td>
            </tr>
            <tr>
                <td>年　　龄：</td>
                <td>
                    <input id="age" class="easyui-numberbox"  iconWidth="28" style="width:150px;height:30px;padding:10px;">
                </td>

                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户状态：</td>
                <td>
                    <select id="valid" class="easyui-combobox"  data-options="editable:false,panelHeight:'80px'" style="width:150px;height:30px">
                        <option value="1">正常</option>
                        <option value="2">冻结</option>
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
        <table id="tt" >
        </table>
        <div id="footer1" style="padding:5px;" align="center">
            <a id="sqSubmit" style="border-radius: 25px;color: red;font: bold 12px/17px Arial" class="easyui-linkbutton">&nbsp;&nbsp;&nbsp;&nbsp;保存&nbsp;&nbsp;&nbsp;&nbsp;</a>
            <div style="border-radius: 25px;font: bold 12px/17px Arial" class="easyui-linkbutton" onclick="closeSq()">&nbsp;&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;&nbsp;</div>
        </div>
    </div>

    <div id="selectDeptWin" style="padding:10px;">
        请输入部门名称：<input id="queryDeptName" class="easyui-textbox" style="width:20%;height:30px;">
        <a href="#" id="staff_query" onclick="queryDeptName()" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
           style="width: 80px">查 询</a>
        <table id="deptGrid"></table>
    </div>
</div>
</body>
</html>
