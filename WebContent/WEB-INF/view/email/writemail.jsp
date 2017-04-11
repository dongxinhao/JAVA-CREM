<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/27
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/sys/common.jsp"%>
<html>
<head>
    <title>写邮件</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/easyui/themes/color.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/easyui/demo/demo.css">
    <script type="text/javascript" src="<%=path%>/bizjs/email/writemail.js"></script>
</head>
<body>
    <div>
        <form action="<%=path%>/sys/email" id="form2" method="post" name="form2" onsubmit="return checkForm('form2');">
            <table class="editTable" cellspacing="1" cellpadding="0" width="100%" align="center" border="0">
                <tbody>
                <tr class="editHeaderTr">
                    <td class="editHeaderTd" colspan="7">  请输入邮件的详细信息
                    </td>
                </tr>


                <tr>
                    <td style="background-color: #FFFDF0"><div align="center">收件人：</div></td>
                    <td colspan="3" style="background-color:#FFFFFF">
                        <input id="receiver" name="receiver" value="请选择收件人" >
                    </td>
                </tr>
                <tr>
                    <td style="background-color: #FFFDF0"><div align="center">主题：</div></td>
                    <td colspan="3" style="background-color: #FFFFFF">
                        <%--<input type="text" style="width:578px" check_str="主题" name="emailTheme">--%>
                            <div style="width:578px;">
                                <input id="theme" class="easyui-textbox" labelPosition="top" style="width:100%;height:32px">
                            </div>
                    </td>
                </tr>


                <tr>
                    <td style="background-color: #FFFDF0"><div align="center">内容：</div></td>
                    <td colspan="3" style="background-color: #FFFFFF"><input id="detail" name="detail" style="width:578px; height:300px;"></td>
                </tr>
                <tr>
                    <td style="background-color: #FFFDF0"><div align="center">发件人：</div></td>
                    <td colspan="3" style="background-color: #FFFFFF">
                        <input id="sender" name="sender" value="${sessionScope.username}" >
                    </td>
                    <td>
                        <input type="hidden" value="time" name="emailTime">
                    </td>
                </tr>
                </tbody></table>
            <table class="editTable" cellspacing="1" cellpadding="0" width="100%" align="center" border="0">
                <tbody><tr style="background-color:#ECF3FD">
                    <td width="6%">&nbsp;</td>
                    <td width="15%">
                            <a  class="easyui-linkbutton c4" style="width:80px" onclick="send()">发送</a>
                    </td>
                    <td width="15%">
                            <a  class="easyui-linkbutton c6" style="width:80px" onclick="save()">存草稿</a>
                    </td>
                    <td width="40%">
                            <a  class="easyui-linkbutton c5" style="width:80px" onclick="resetEmail()">重写</a>
                    </td>

                </tr>
                </tbody></table>

        </form>

    </div>

</body>
</html>
