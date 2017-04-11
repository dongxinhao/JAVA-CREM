<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/sys/common.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="<%=path%>/bizjs/customer/customer.js"></script>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="padding:10px">
    请输入查询内容:
    <input id="queryCustName" class="easyui-textbox" data-options="prompt:'请输入查询内容'" style="width:20%;height:30px">
    请选择查询方式:
    <select id="queryValid" class="easyui-combobox" data-options="editable:false" style="width:20%;height:30px">
        <option value="">客户姓名</option>
        <option value="2">客户状态</option>
        <option value="3">客户来源</option>
        <option value="4">客户类型</option>
        <option value="5">所属员工</option>
        <option value="6">客户公司</option>
    </select>
    <a id="cust_query" onclick="query()" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
       style="width: 80px">搜 索</a>
    <a id="cust_add" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width: 80px">增 加</a>
    <a id="refresh" class="easyui-linkbutton"
       style=" float: right;color: red;font: bold 17px/17px Arial;border-radius: 5px">刷新</a>
</div>
<div data-options="region:'center',title:''">
    <table id="custGrid">
    </table>
    <div id="m1" class="easyui-menu" style="width:120px;">
        <div id="cust_update" data-options="iconCls:'icon-remove'">编辑</div>
        <div id="cust_del" data-options="iconCls:'icon-no'">删除</div>
    </div>
    <div id="addCPM">
        <table align="center" style="font: bold 12px/17px Arial;">
            <tr>
                <td height="20px"></td>
                <td></td>
            </tr>
            <tr>
                <td>客户姓名：</td>
                <td>
                    <input id="ctName"  required="true" missingMessage="姓名为必填项" class="easyui-textbox" iconWidth="28"
                           style="width:200px;height:30px;padding:10px;">
                </td>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：
                </td>
                <td>
                    <input id="ctStatus"  required="true" missingMessage="状态为必选项" class="easyui-textbox"  readonly="readonly" style="width:56%;height:30px">
                    <a href="#" onclick="showSelectctStatusWin()" class="easyui-linkbutton" data-options="iconCls:'icon-add'"style="width: 80px">选 择</a>
                </td>
            </tr>
            <tr>
                <td>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：</td>
                <td>
                    <input id="ctSource"  required="true" missingMessage="来源为必选项" class="easyui-textbox"  readonly="readonly" style="width:56%;height:30px">
                    <a href="#" onclick="showSelectctSourceWin()" class="easyui-linkbutton" data-options="iconCls:'icon-add'"style="width: 80px;height:30px">选 择</a>
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;所属员工：</td>
                <td>
                    <input id="ctEmp" class="easyui-textbox" iconWidth="28"
                           style="width:200px;height:30px;padding:10px;">
                </td>
            </tr>
            <tr>
                <td>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：</td>
                <td>
                    <input id="ctType"  required="true" missingMessage="类型为必选项"  class="easyui-textbox"  readonly="readonly" style="width:56%;height:30px">
                    <a href="#" onclick="showSelectStaffWin()" class="easyui-linkbutton" data-options="iconCls:'icon-add'"style="width: 80px">选 择</a>
                </td>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：
                </td>
                <td>
                    <select id="ctSex" class="easyui-combobox" data-options="editable:false,panelHeight:'80px'"
                            style="width:200px;height:30px">
                        <option value="1">男</option>
                        <option value="2">女</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：</td>
                <td>
                    <input  id="ctTel"  required="true" missingMessage="手机必须填写" class="easyui-textbox" iconWidth="28"
                           style="width:200px;height:30px;padding:10px">
                </td>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Q&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Q：
                </td>
                <td>
                    <input id="ctQQ" class="easyui-textbox" iconWidth="28"
                           style="width:200px;height:30px;padding:10px">
                </td>
            </tr>
            <tr>
                <td>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</td>
                <td>
                    <input id="ctEmail"  missingMessage="邮件必须填写" required="true" validType="email" invalidMessage="请填写正确的邮件格式" class="easyui-textbox" iconWidth="28"
                           style="width:200px;height:30px;padding:10px">
                </td>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：
                </td>
                <td>
                    <input id="ctJob" class="easyui-textbox" iconWidth="28"
                           style="width:200px;height:30px;padding:10px;">
                </td>
            </tr>
            <tr>
                <td>公&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;司：</td>
                <td>
                    <input id="ctComp" class="easyui-textbox" iconWidth="28"
                           style="width:200px;height:30px;padding:10px;">
                </td>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：
                </td>
                <td>
                    <input id="ctMessage" class="easyui-numberbox" data-options="multiline:true" iconWidth="28"
                           style="width:200px;height:90px;padding:10px;">
                </td>
            </tr>
        </table>
        <div id="footer" style="padding:5px;" align="center">
            <a id="addSubmit" style="border-radius: 25px;color: red;font: bold 12px/17px Arial"
               class="easyui-linkbutton">&nbsp;&nbsp;&nbsp;&nbsp;保存&nbsp;&nbsp;&nbsp;&nbsp;</a>
            <div style="border-radius: 25px;font: bold 12px/17px Arial" class="easyui-linkbutton" onclick="closeAdd()">
                &nbsp;&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;&nbsp;</div>
        </div>
    </div>

    <div id="selectStaffWin" style="padding:10px;">
        类型：<input id="queryStaffName" class="easyui-textbox" style="width:20%;height:30px;">
        <a href="#" id="staff_query" onclick="queryStaffName()" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
           style="width: 80px">查 询</a>
        <table id="staffGrid"></table>
    </div>
    <div id="selectctStatusWin" style="padding:10px;">
    状态：<input id="queryctStatusName" class="easyui-textbox" style="width:20%;height:30px;">
    <a href="#" id="ctStatus_query" onclick="queryctStatusName()" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
       style="width: 80px">查 询</a>
    <table id="ctStatusGrid"></table>
</div>
    <div id="selectctSourceWin" style="padding:10px;">
        来源：<input id="queryctSourceName" class="easyui-textbox" style="width:20%;height:30px;">
        <a href="#" id="ctSource_query" onclick="queryctStatusName()" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
           style="width: 80px">查 询</a>
        <table id="ctSourceGrid"></table>
    </div>
    <div id="sqCPM">
        <table id="tt">
        </table>
        <div id="footer1" style="padding:5px;" align="center">
            <a id="sqSubmit" style="border-radius: 25px;color: red;font: bold 12px/17px Arial"
               class="easyui-linkbutton">&nbsp;&nbsp;&nbsp;&nbsp;保存&nbsp;&nbsp;&nbsp;&nbsp;</a>
            <div style="border-radius: 25px;font: bold 12px/17px Arial" class="easyui-linkbutton" onclick="closeSq()">
                &nbsp;&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;&nbsp;</div>
        </div>
    </div>
</div>
</body>
</html>
