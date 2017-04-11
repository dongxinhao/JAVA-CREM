<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/sys/common.jsp" %>

<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="<%=path%>/bizjs/foot/privCentre/privGetResource.js"></script>

<body class="easyui-layout">
<div data-options="region:'north',border:false" style="padding:10px">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input id="queryImgName" class="easyui-textbox" data-options="prompt:'图片名称'" style="width:20%;height:30px">
    <input id="queryType" class="easyui-textbox" data-options="prompt:'分享人'" style="width:20%;height:30px">

    <a id="img_query" onclick="queryFootImg()" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
       style="width: 80px">搜 索</a>

    <a id="refresh" class="easyui-linkbutton"
       style=" float: right;color: red;font: bold 17px/17px Arial;border-radius: 5px">刷新</a>

</div>
<div data-options="region:'center'">

    <table id="privImgGrid">

    </table>

    <%--<div id="m1" class="easyui-menu" style="width:120px;">
        &lt;%&ndash;<div id="img_del" data-options="iconCls:'icon-no'">删除</div>
        <div id="img_back" data-options="iconCls:'icon-back'">撤回</div>&ndash;%&gt;
    </div>--%>

    <div id="imgCPM" align="center" >
        <embed style=" height: 460px;margin-top: 20px" align="center" id="img1" loop='2'></embed>
    </div>

</div>

</body>
</html>
