/**
 * Created by Administrator on 2017/3/6.
 */
$(function () {
    valid(["type_add", "type_del", "type_query", "type_update"])
    initRoleGrid();
    if (hasRes("type_query")) {
        query();
    }
})
function initRoleGrid() {
    $("#typeGrid").datagrid({
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
            {field: 'ctType', title: '客户类型',align:'center', width: 90},
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
    var ctType = $("#queryCustName").textbox("getValue");
    var data = {
        ctType: ctType,
        valid: valid
    };
    // console.log(data);
    $("#typeGrid").datagrid("options").url = path + "/cttype/queryCttype";
    $("#typeGrid").datagrid("load", data);
}

$(function () {
    $("#type_add").click(function () {
        $("#addCPM").window({
            modal: true,
            closed: true,
            closable: false,
            title: "客户类型添加窗口",
            iconCls: 'icon-save',
            height: 150,
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
    $("#type_update").click(function () {
        $("#addCPM").window({
            modal: true,
            closed: true,
            closable: false,
            title: "客户类型更改窗口",
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
        var $gs = $("#typeGrid").datagrid("getSelected");
        $("#ctType").textbox("setValue", $gs.ctType);
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
    $("#ctType").textbox("setValue", "");
}
//角色添加保存按钮
$(function () {
    $("#addType").click(function () {
        var dbid = $("#dbid").text();
        var ctType = $("#ctType").textbox("getValue");
        var data = {
            dbid: dbid,
            ctType: ctType,
        };
        var url = path + "/cttype/saveOrUpdate";
        if (ctType == "") {
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
                        $.messager.alert("错误", "提交异常，角色名称重复或网络异常！")
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
    $("#type_del").click(function () {
        var $gs = $("#typeGrid").datagrid("getSelected");
        var data = {};
        data.dbid = $gs.dbid;
        var url = path + "/cttype/deleteAuth";
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