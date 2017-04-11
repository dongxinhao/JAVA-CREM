<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.a.SSH.common.util.ModelUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/26
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/sys/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理</title>


    <script type="text/javascript" src="<%=path%>/bizjs/sys/login.js"></script>
    <script type="text/javascript" src="<%=path%>/bizjs/sys/sys.js"></script>
</head>
<body class="easyui-layout" style="font: bold 12px/17px Arial;color: #ffffff">
<div data-options="region:'north',border:false"
     style="height:50px;background:linear-gradient(to bottom,#541888 0,#1d67dc 100%);padding:8px;color:#ffffff">
    <div style="font-size: 25px ;float: left;padding: 5px">房地产管理系统</div>
    <div style="font: bold 18px/24px Arial ;float: right;padding-top: 7px">欢迎：${sessionScope.username}&nbsp;&nbsp;&nbsp;&nbsp;<b
            style="cursor: pointer" id="logout">注销</b>
    </div>
    <div style="width: 30px;font: bold 18px/24px Arial ;color: #ffffff;float: right;padding-top: 10px;padding-right: 20px">

    </div>
    <%--<div  style="font: bold 18px/24px Arial ;color: #ffffff;float: right;padding-top: 5px;padding-right: 20px">
        <button style="background:linear-gradient(rgb(84, 24, 136) 0px, rgb(29, 103, 220) 100%);border:rgb(29, 103, 220);border-radius:50px;color: #ffffff ">
            hahaaaa
        </button>
        <button style="background:linear-gradient(rgb(84, 24, 136) 0px, rgb(29, 103, 220) 100%);border:rgb(29, 103, 220);border-radius:50px;color: #ffffff ">
            ✚
        </button>
    </div>--%>

</div>
<div data-options="region:'west',split:true,title:'功能模块'" style="width:200px;color: #ffffff;font-size:25px">
    <div class="easyui-accordion" data-options="fit:true">
        <%
            List<Map<String, Object>> auths = (List) session.getAttribute("auths");
            if (auths != null && auths.size() > 0) {
                for (Map<String, Object> auth : auths) {
                    if ("1".equals((auth.get("layer").toString()))) {
                        String authName = auth.get("authName").toString();
                        String authId = auth.get("dbid").toString();
                        String treeId = "#" + authId;
                        String child = ModelUtil.getChild(authId, session);


        %>
        <div title="<%=authName%>">
            <ul id="<%=authId%>"></ul>
            <script type="text/javascript">
                var data = '<%=child%>';
                $("<%=treeId%>").tree({
                    data: JSON.parse(data),
                    onClick: function (node) {
                        addTab(node.text, node.url);
                    }
                })
            </script>
        </div>
        <%
                    }
                }

            }
        %>


    </div>
</div>
<div data-options="region:'center',">
    <div data-options="region:'center',fit:true" id="mainTab" class="easyui-tabs" style="background: #444">
        <div title="首页" id="t1" align="center"  >
            <div style="margin-top: 200px">
                <b style="font-size: 45px;margin-bottom: 200px">我的企业网站</b>

            </div>
        </div>
    </div>
</div>

</body>
</html>



