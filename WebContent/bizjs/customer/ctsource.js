/**
 * Created by Administrator on 2017/3/6.
 */
$(function () {
    valid(["source_add", "source_del", "source_query", "source_update"])
    initRoleGrid();
    if (hasRes("source_query")) {
        query();
    }
})
function initRoleGrid() {
    $("#sourceGrid").datagrid({
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
            {field: 'ctSource', title: '客户状态', width: 90},
            {field: 'sourceMessage', title: '备注', width: 90},
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
    var ctSource = $("#queryCustName").textbox("getValue");
    var data = {
        ctSource: ctSource,
        valid: valid
    };
    // console.log(data);
    $("#sourceGrid").datagrid("options").url = path + "/ctsource/queryCtsource";
    $("#sourceGrid").datagrid("load", data);
}

$(function () {
    $("#source_add").click(function () {
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
    $("#source_update").click(function () {
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
        var $gs = $("#sourceGrid").datagrid("getSelected");
        $("#ctSource").textbox("setValue", $gs.ctSource);
        $("#dbid").text($gs.dbid);
        $("#sourceMessage").textbox("setValue", $gs.sourceMessage);
        $("#addCPM").window('open');
    })
})
//关闭添加窗口
function closeAdd() {
    $("checkbox").attr("checked", false);
    $(".emptyS").hide();
    $("#addCPM").window('close');
    $("#dbid").text("");
    $("#ctSource").textbox("setValue", "");
    $("#sourceMessage").textbox("setValue", "");
}
//角色添加保存按钮
$(function () {
    $("#addSource").click(function () {
        var dbid = $("#dbid").text();
        var ctSource = $("#ctSource").textbox("getValue");
        var sourceMessage = $("#sourceMessage").textbox("getValue");
        var data = {
            dbid: dbid,
            ctSource: ctSource,
            sourceMessage: sourceMessage,
        };
        var url = path + "/ctsource/saveOrUpdate";
        if (ctSource == ""&&sourceMessage=="" ) {
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
    $("#source_del").click(function () {
        var $gs = $("#sourceGrid").datagrid("getSelected");
        var data = {};
        data.dbid = $gs.dbid;
        var url = path + "/ctsource/deleteAuth";
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