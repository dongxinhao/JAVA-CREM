
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/sys/common.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="<%=path%>/bizjs/customer/ctCare/ctCare.js"></script>

<body class="easyui-layout">
<div data-options="region:'north',border:false" style="padding:10px">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input id="queryContext" class="easyui-textbox"   data-options="prompt:'查询内容'" style="width:20%;height:30px">
    <select id="queryType" class="easyui-combobox"  data-options="editable:false" style="width:20%;height:30px">
        <option value="ctName">关怀客户</option>
        <option value="careTitle">关怀主题</option>
        <option value="careMethod">关怀方式</option>
    </select>
    <a id="role_query"  onclick="queryRole()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px">搜   索</a>
    <a id="role_add" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width: 80px">增   加</a>

    <a id="refresh" class="easyui-linkbutton" style=" float: right;color: red;font: bold 17px/17px Arial;border-radius: 5px" >刷新</a>

</div>
<div data-options="region:'center'">

    <table id="ctCareGrid" >

    </table>
    <div id="m1" class="easyui-menu" style="width:120px;">
        <div id="role_update" data-options="iconCls:'icon-remove'">编辑</div>
        <div id="role_del" data-options="iconCls:'icon-no'">删除</div>
    </div>
    <div id="addCPM">
        <table align="center" style="font: bold 12px/17px Arial;">
            <tr>
                <td height="20px"></td>
                <td></td>
                <b style="display: none;" id="dbid"></b>
            </tr>
            <tr>
                <td>客户<b id="ctNameCon" style="cursor: pointer;color: red">搜索</b>：</td>
                <td >
                    <input id="ctName" class="easyui-textbox" required="true" style="width:150px;height:30px">
                </td>
            </tr>
            <tr>
                <td>关怀主题：</td>
                <td>
                    <input id="careTitle" class="easyui-textbox" style="width:150px;height:30px">
                </td>
            </tr>
            <tr>
                <td>关怀方式：</td>
                <td>
                    <select id="careMethod" class="easyui-combobox"  data-options="editable:false,panelHeight:'80px'" style="width:150px;height:30px">
                        <option value="发短信">发短信</option>
                        <option value="打电话">打电话</option>
                        <option value="送礼品">送礼品</option>
                        <option value="上门问候">上门问候</option>
                    </select>

                </td>
            </tr>
            <tr>
                <td>关怀时间：</td>
                <td>
                    <input id="careTime" required="true" class="easyui-datetimebox"   style="width:150px;height:30px">

                </td>
            </tr>
            <tr>
                <td>关怀员工：</td>
                <td>
                    <input id="ctemp" required="true" class="easyui-textbox"   style="width:150px;height:30px">

                </td>
            </tr>
            <tr>
                <td>备注：</td>
                <td>
                    <input id="conMessage" class="easyui-textbox" style="width:150px;height:60px" multiline="true" >
                </td>
            </tr>
        </table>
        <div id="footer" style="padding:5px;" align="center">
            <a id="addSubmit" style="border-radius: 25px;color: red;font: bold 12px/17px Arial" class="easyui-linkbutton">&nbsp;&nbsp;&nbsp;&nbsp;保存&nbsp;&nbsp;&nbsp;&nbsp;</a>
            <div style="border-radius: 25px;font: bold 12px/17px Arial" class="easyui-linkbutton" onclick="closeAdd()">&nbsp;&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;&nbsp;</div>
        </div>
    </div>
    <div id="selCustomerCPM">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input id="queryCt" class="easyui-textbox"   data-options="prompt:'客户名'" style="width:20%;height:30px">
        <input id="queryTEl" class="easyui-textbox"   data-options="prompt:'手机'" style="width:20%;height:30px">
        <a id="Ct_query"  onclick="queryCt()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px">搜   索</a>

        <table id="CustomerGrid">

        </table>

        <div id="footer2" style="padding:5px;" align="center">
            <a id="CtSubmit" style="border-radius: 25px;color: red;font: bold 12px/17px Arial" class="easyui-linkbutton">&nbsp;&nbsp;&nbsp;&nbsp;保存&nbsp;&nbsp;&nbsp;&nbsp;</a>
            <div style="border-radius: 25px;font: bold 12px/17px Arial" class="easyui-linkbutton" onclick="closeCt()">&nbsp;&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;&nbsp;</div>
        </div>
    </div>
    <div id="sqCPM">
        <ul id="tt" class="easyui-tree" ></ul>
        <div id="footer1" style="padding:5px;" align="center">
            <a id="sqSubmit" style="border-radius: 25px;color: red;font: bold 12px/17px Arial" class="easyui-linkbutton">&nbsp;&nbsp;&nbsp;&nbsp;保存&nbsp;&nbsp;&nbsp;&nbsp;</a>
            <div style="border-radius: 25px;font: bold 12px/17px Arial" class="easyui-linkbutton" onclick="closeSq()">&nbsp;&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;&nbsp;</div>
        </div>
    </div>
</div>

</body>
</html>
