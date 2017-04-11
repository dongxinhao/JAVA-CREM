/**
 * Created by Administrator on 2017/3/29.
 */
/**
 * Created by Administrator on 2017/3/6.
 */
$(function () {
    initRoleGrid();
    /*valid(["role_add","role_del","role_query","role_update"])

    if(hasRes("role_query")){
     queryRole();
    }
*/
    queryRole();
})

function initRoleGrid() {
    $("#ctCareGrid").datagrid({
        rownumbers: true,
        singleSelect: true,
        fitColumns: "true",
        fit: true,
        autoRowHeight: false,
        //是否分页
        pagination: true,
        onRowContextMenu: onRowContextMenu,
        //每页多少条
        pageSize: 10,
        pageList: [3, 5, 10, 20],
        columns: [[
            {field: 'ctName', title: '客户', width: 50},
             {field: 'careTitle', title: '关怀主题', width: 50},
            {field: 'careMethod', title: '关怀方式', width: 50,},
            {field: 'careTime', title: '关怀时间', width: 50},
            {field: 'ctemp', title: '关怀员工', width: 50},
            {field: 'conMessage', title: '备注', width: 100}

        ]]
    });
}
$(function () {
    $("#role_add").click(function () {
        $("#addCPM").window({
            modal: true,
            closed: true,
            closable: false,
            title: "客户关怀添加窗口",
            iconCls: 'icon-save',
            height: 350,
            width: 300,
            collapsible: false,
            minimizable: false,
            maximizable: false,

            footer: '#footer',
            onBeforeClose: function () {
                /* $(".dl").load(location.href+" .dl");*/
            }
        })
        $("#ctemp").textbox("setValue", userNameCenter);
        $("#addCPM").window('open');
    })
})
//关闭添加窗口
function closeAdd() {
    $(".emptyS").hide();
    $("#dbid").text("");
    $("#ctName").textbox("setValue", "");
    $("#careTitle").textbox("setValue", "");
    $("#ctemp").textbox("setValue", "");
    $("#conMessage").textbox("setValue", "");
    $("#careMethod").combobox("setValue", "发短信");
    $("#careTime").datetimebox("setValue", "");
    $("#addCPM").window('close');
}

//角色添加保存按钮
$(function () {
    $("#addSubmit").click(function () {

        var dbid = $("#dbid").text();
        var ctName=$("#ctName").textbox("getValue");
        var careTitle=$("#careTitle").textbox("getValue");
        var ctemp=$("#ctemp").textbox("getValue");
        var conMessage=$("#conMessage").textbox("getValue");
        var careTime=$("#careTime").datetimebox("getValue");
        var careMethod=$("#careMethod").combobox("getValue");

        var data = {
            dbid: dbid,
            ctName: ctName,
            careTitle: careTitle,
            ctemp: ctemp,
            conMessage: conMessage,
            careTime: careTime,
            careMethod: careMethod
        };
        console.log(data);
        var url = path + "/CtCare/saveOrUpdate";
        if (ctName == ""||ctemp==""||careTime=="") {
            $.messager.alert("提示","信息不完整");
        } else {
            $.ajax({
                url: url,
                data: data,
                dataType: "json",
                method: "post",
                success: function (result) {
                    if (result.success) {
                        $.messager.alert("提示", "操作成功！")
                        initRoleGrid();
                    } else {
                        $.messager.alert("错误", "提交异常，角色名称重复或网络异常！")
                    }
                    closeAdd();
                }
            })
        }


    })
})
//搜索
function queryRole() {
    var context = $("#queryContext").textbox("getValue");
    var valid = $("#queryType").combobox("getValue");

    if(valid=="ctName"){
        var data = {
            ctName:context,
            careTitle:"",
            careMethod:""
        };
    }
    if(valid=="careTitle"){
        var data = {
            ctName:"",
            careTitle:context,
            careMethod:""
        };
    }
    if(valid=="careMethod"){
        var data = {
            ctName:"",
            careTitle:"",
            careMethod:context
        };
    }
    //console.log(data);

    $("#ctCareGrid").datagrid("options").url = path + "/CtCare/queryCtCare";
    $("#ctCareGrid").datagrid("load", data);
}
//刷新
$(function () {
    $("#refresh").click(function () {
        window.location.reload();
        initRoleGrid();

    })
})
//右击事件
function onRowContextMenu(e, rowIndex, rowData) {
    if (rowData) {
        e.preventDefault();
        $(this).datagrid("selectRow", rowIndex);
        $('#m1').menu('show', {left: e.pageX, top: e.pageY});

    }
}
//更改事件
$(function () {
    $("#role_update").click(function () {
        $("#addCPM").window({
            modal: true,
            closed: true,
            closable: false,
            title: "信息更改窗口",
            iconCls: 'icon-save',
            height: 350,
            width: 300,
            collapsible: false,
            minimizable: false,
            maximizable: false,
            footer: '#footer',
            onBeforeClose: function () {

            }
        })
        var $gs = $("#ctCareGrid").datagrid("getSelected");
        $("#ctName").textbox("setValue", $gs.ctName);
        $("#careTitle").textbox("setValue", $gs.careTitle);
        $("#ctemp").textbox("setValue", $gs.ctemp);
        $("#conMessage").textbox("setValue", $gs.conMessage);
        $("#careTime").datetimebox("setValue", $gs.careTime);
        $("#careMethod").combobox("setValue", $gs.careMethod);
        $("#dbid").text($gs.dbid);

        $("#addCPM").window('open');
    })
})


function sqTree(dbid) {

    var roleId = dbid;
    $("#tt").tree({
        url: path + '/role_auth/getValidAuth?roleId=' + roleId,
        method: 'get',
        animate: true,
        lines: true,
        checkbox: true,
        cascadeCheck: false,


        onCheck: function (node, checked) {
            if (checked) {
                var parentNode = $("#tt").tree('getParent', node.target);
                if (parentNode != null) {
                    $("#tt").tree('check', parentNode.target);
                }
            } else {
                var childNode = $("#tt").tree('getChildren', node.target);
                if (childNode.length > 0) {
                    for (var i = 0; i < childNode.length; i++) {
                        $("#tt").tree('uncheck', childNode[i].target);
                    }
                }
            }
        }
    });
}


//删除
$(function () {
    $("#role_del").click(function () {

        var $gs = $("#ctCareGrid").datagrid("getSelected");

        var data = {};
        data.dbid = $gs.dbid;
        //console.log(data);
        var url = path + "/CtCare/delete";
        $.messager.confirm("删除", "是否删除", function (r) {
            if (r) {
                $.ajax({
                    url: url,
                    data: data,
                    method: "post",
                    dataType: "json",
                    success: function (result) {
                        if (result.success) {
                            $.messager.alert('提示', '删除成功!');
                        } else {
                            $.messager.alert('提示', '网络异常!');
                        }
                        initRoleGrid();
                    }
                })
            }
        })
    })
})
//获取联系人
$(function () {
    $("#ctNameCon").click(function () {

        $("#selCustomerCPM").window({
            modal: true,
            closed: true,
            closable: false,
            title: "客户查询窗口",
            iconCls: 'icon-save',
            height: 450,
            width: 600,
            collapsible: false,
            minimizable: false,
            maximizable: false,
            footer: '#footer2',
            onBeforeClose: function () {

            }
        })
        $("#addCPM").window('close');

        initCustomerGrid();
        loadCtContent();
        $("#selCustomerCPM").window('open');
    })
})
function initCustomerGrid() {
    $("#CustomerGrid").datagrid({
        rownumbers: true,
        singleSelect: true,
        fitColumns: "true",
        //fit: true,
        height:340,
        autoRowHeight: false,
        //是否分页
        pagination: true,
        onRowContextMenu: onRowContextMenu,
        //每页多少条
        pageSize: 5,
        pageList: [3, 5, 10, 20],
        columns: [[
            {field: 'ctName', title: '客户', width: 100},
            {field: 'ctTel', title: '手机', width: 100}
        ]]
    });
}
function closeCt() {
    $("#selCustomerCPM").window('close');
    $("#addCPM").window('open');
}

$(function () {
    $("#CtSubmit").click(function () {
        var $gs = $("#CustomerGrid").datagrid("getSelected");
        var ctName=$gs.ctName;

        $("#ctName").textbox("setValue",ctName);
        $("#selCustomerCPM").window('close');
        $("#addCPM").window('open');

    })
})


//加载客户信息
function loadCtContent() {
    var ctName = $("#queryCt").textbox("getValue");
    var ctTel = $("#queryTEl").textbox("getValue");
    var data={
        ctName:ctName,
        ctTel:ctTel
    };



    $("#CustomerGrid").datagrid("options").url = path + "/CtCare/loadCtContent";
    $("#CustomerGrid").datagrid("load", data);
}
//加载客户信息搜索
function queryCt() {
    loadCtContent();
}