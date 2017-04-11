<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/sys/common.jsp" %>

<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="<%=path%>/bizjs/foot/resCenter/resource.js"></script>

<body class="easyui-layout">
<div data-options="region:'north',border:false" style="padding:10px">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input id="queryImgName" class="easyui-textbox" data-options="prompt:'图片名称'" style="width:20%;height:30px">
    <input id="queryType" class="easyui-textbox" data-options="prompt:'类型'" style="width:20%;height:30px">

    <a id="img_query" onclick="queryFootImg()" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
       style="width: 80px">搜 索</a>
    <a id="img_add" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width: 80px">添加图片</a>

    <a id="refresh" class="easyui-linkbutton"
       style=" float: right;color: red;font: bold 17px/17px Arial;border-radius: 5px">刷新</a>

</div>
<div data-options="region:'center'">

    <table id="imgGrid">

    </table>

    <div id="m1" class="easyui-menu" style="width:120px;">
        <div id="img_update" data-options="iconCls:'icon-remove'">编辑</div>
        <div id="img_del" data-options="iconCls:'icon-no'">删除</div>
    </div>
    <div id="addCPM" align="center">
        <form align="center" action="<%=path%>/foot/upFile" method="post" enctype="multipart/form-data">
            <div style="margin-top: 15px">
                <div style="margin-bottom:20px">
                    选择文件：<input name="files" name="files" class="easyui-filebox" labelPosition="top"
                                style="width:150px">
                </div>
                <div style="margin-bottom:20px">
                    文件名称：<input class="easyui-textbox" name="imgName" labelPosition="top" style="width:150px">
                </div>
                <div style="margin-bottom:20px">
                    文件类型：<input class="easyui-textbox" name="type" labelPosition="top" style="width:150px">
                </div>
                <div style="margin-bottom:20px">
                    数字排序：<input class="easyui-numberbox" name="orders" labelPosition="top" style="width:150px">
                </div>
                <div style="margin-bottom:20px;display: none">
                    userName：<input value="${sessionScope.username}"  name="userName"  style="width:150px">
                </div>

                <div style="margin-top:100px">
                    <input class="easyui-linkbutton" style="width:150px" type="submit" value="上传">

                </div>


            </div>
        </form>

    </div>
    <div id="edCPM" align="center">
        <b style="display: none" id="dbid"></b>
        <div style="margin-bottom:20px;margin-top:20px ">
            <embed id="img"  loop='2' style=' height: 100px;'></embed>
        </div>
        <div style="margin-bottom:20px;margin-top:20px ">
            文件名称：<input class="easyui-textbox" id="imgName" labelPosition="top" style="width:150px">
        </div>
        <div style="margin-bottom:20px">
            文件类型：<input class="easyui-textbox" id="type" labelPosition="top" style="width:150px">
        </div>
        <div style="margin-bottom:20px">
            数字排序：<input class="easyui-numberbox" id="orders" labelPosition="top" style="width:150px">
        </div>

        <div id="footer1" style="padding:5px;" align="center">
            <a id="edSubmit" style="border-radius: 25px;color: red;font: bold 12px/17px Arial"
               class="easyui-linkbutton">&nbsp;&nbsp;&nbsp;&nbsp;保存&nbsp;&nbsp;&nbsp;&nbsp;</a>
            <div style="border-radius: 25px;font: bold 12px/17px Arial" class="easyui-linkbutton" onclick="closeEd()">
                &nbsp;&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;&nbsp;</div>
        </div>
    </div>
    <div id="imgCPM" align="center" >
        <embed style=" height: 460px;margin-top: 20px" align="center" id="img1" loop='2'></embed>
    </div>
</div>

</body>
</html>
