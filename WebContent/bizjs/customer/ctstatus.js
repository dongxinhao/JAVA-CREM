/**
 * Created by Administrator on 2017/3/6.
 */
$(function () {
    valid(["status_add", "status_del", "status_query", "status_update"])
    initRoleGrid();
    if (hasRes("status_query")) {
        query();
    }
})
function initRoleGrid() {
    $("#statusGrid").datagrid({
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
            {field: 'ctStatus', title: '客户状态', width: 90},
            {field: 'statusMessage', title: '备注', width: 90},
        ]]
    })
}
//刷新
$(function () {
    $("#refresh").click(function () {
        window.location.reload();
    })
})
//搜索
function query() {
    var ctStatus = $("#queryCustName").textbox("getValue");
    var data = {
        ctStatus: ctStatus,
        valid: valid
    };
    // console.log(data);
    $("#statusGrid").datagrid("options").url = path + "/ctstatus/queryCtstatus";
    $("#statusGrid").datagrid("load", data);
}

$(function () {
    $("#status_add").click(function () {
        $("#addCPM").window({
            modal: true,
            closed: true,
            closable: false,
            title: "客户状态添加窗口",
            iconCls: 'icon-save',
            height: 300,
            width: 300,
            collapsible: false,
            minimizable: false,
            maximizable: false,
            footer: '#footer',
            onBeforeClose: function () {
                /* $(".dl").load(location.href+" .dl");*/
            }
        })
        $("#addCPM").window('open');
    })
})
//更改事件
$(function () {
    $("#status_update").click(function () {
        $("#addCPM").window({
            modal: true,
            closed: true,
            closable: false,
            title: "客户状态更改窗口",
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
        var $gs = $("#statusGrid").datagrid("getSelected");
        $("#ctStatus").textbox("setValue", $gs.ctStatus);
        $("#statusMessage").textbox("setValue", $gs.statusMessage);
        $("#dbid").text($gs.dbid);
        $("#addCPM").window('open');
    })
})
//关闭添加窗口
function closeAdd() {
    $("checkbox").attr("checked", false);
    $(".emptyS").hide();
    $("#addCPM").window('close');
    $("#dbid").text("");
    $("#ctStatus").textbox("setValue", "");
    $("#statusMessage").textbox("setValue", "");
}
//角色添加保存按钮
$(function () {
    $("#addStatus").click(function () {
        var dbid = $("#dbid").text();
        var ctStatus = $("#ctStatus").textbox("getValue");
        var statusMessage = $("#statusMessage").textbox("getValue");
        var data = {
            dbid: dbid,
            ctStatus: ctStatus,
            statusMessage: statusMessage,
        };
        var url = path + "/ctstatus/saveOrUpdate";
        if (ctStatus == ""&&statusMessage=="" ) {
            $(".emptyS").show();
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
                        $.messager.alert("错误", "提交异常，客户状态名称重复或网络异常！")
                    }
                    closeAdd();
                }
            })
        }
    })
})

function onRowContextMenu(e, rowIndex, rowData) {
    if (rowData) {
        e.preventDefault();
        $(this).datagrid("selectRow", rowIndex);
        $('#m1').menu('show', {left: e.pageX, top: e.pageY});

    }
}
//删除
$(function () {
    $("#status_del").click(function () {
        var $gs = $("#statusGrid").datagrid("getSelected");
        var data = {};
        data.dbid = $gs.dbid;
        var url = path + "/ctstatus/deleteAuth";
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