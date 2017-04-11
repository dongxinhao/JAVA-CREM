
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/sys/common.jsp"%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=path%>/bizjs/email/draftBox.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="padding:10px">
    <input id="queryText" class="easyui-textbox" data-options="prompt:'查询内容'" style="width:40%;height:30px">
    <select id="email_main" class="easyui-combobox" data-options="editable:false" style="width:20%;height:30px">
        <option value="a">全　部</option>
        <option value="0">收件人</option>
        <option value="1">主　题</option>
        <option value="2">文　本</option>
    </select>
    <a href="#" id="queryEmail" onclick="queryEmails()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px;">搜　索</a>

</div>
<div data-options="region:'center'">
    <table id="emailGrid">

    </table>
</div>

<div id="confirmWin" style="padding: 10px">
    <input type="hidden" id="emailId">
    <div style="margin-left: 20%;margin-top: 10%">
        <p style="color: red;font-family: 楷体;font-size: 20px;"><b>确认删除邮件吗?</b></p>
    </div>
</div>
<div id="footer" style="padding:5px;">
    <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="removeEmail()" style="width:80px">Ok</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="confirmWinClose()" style="width:80px">Cancel</a>
    </div>
</div>

<div id="sendConfirmWin" style="padding: 10px">
    <input type="hidden" id="sendEmailId">
    <div style="margin-left: 20%;margin-top: 10%">
        <p style="color: aqua;font-family: 楷体;font-size: 20px;"><b>确认发送邮件吗?</b></p>
    </div>
</div>
<div id="sendFooter" style="padding:5px;">
    <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="sendEmail()" style="width:80px">Ok</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="sendConfirmWinClose()" style="width:80px">Cancel</a>
    </div>
</div>

</body>
</html>
