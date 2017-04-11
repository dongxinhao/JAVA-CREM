/**
 * Created by Administrator on 2017/3/6.
 */
$(function () {
    initRoleGrid();
    valid(["role_add","role_del","role_query","role_update"])

    if(hasRes("role_query")){
        queryRole();
    }


})

function initRoleGrid() {
    $("#roleGrid").datagrid({
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
            {field: 'roleName', title: '角色名称', width: 100},
            {field: 'roleCode', title: '角色编码', width: 100},
            {
                field: 'valid', title: '是否有效', width: 100,
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "有效";
                    } else {
                        return "<span style='color: red'>无效</span>";
                    }
                }
            },
            {field: 'orders', title: '排序', width: 100},
            {
                field: 'dbid', title: '授权', width: 50,
                formatter: function (value, row, index) {
                    return "<b onclick='sq(" + value + ")'  style='color: red;cursor: pointer'>授权</b>";

                }
            }

        ]]
    });
}
$(function () {
    $("#role_add").click(function () {
        $("#addCPM").window({
            modal: true,
            closed: true,
            closable: false,
            title: "角色添加窗口",
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
        $("#addCPM").window('open');
    })
})
//关闭添加窗口
function closeAdd() {
    $(".emptyS").hide();
    $("#dbid").text("");
    $("#roleName").textbox("setValue", "");
    $("#roleCode").textbox("setValue", "");
    $("#valid").combobox("setValue", "1");
    $("#orders").numberbox("setValue", "");
    $("#addCPM").window('close');
}

//角色添加保存按钮
$(function () {
    $("#addSubmit").click(function () {

        var dbid = $("#dbid").text();
        var roleName = $("#roleName").textbox("getValue");
        var roleCode = $("#roleCode").textbox("getValue");
        var valid = $("#valid").combobox("getValue");
        var orders = $("#orders").numberbox("getValue");
        var data = {
            dbid: dbid,
            roleName: roleName,
            roleCode: roleCode,
            valid: valid,
            orders: orders
        };

        var url = path + "/role/saveOrUpdate";
        if (roleName == "") {
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
//搜索
function queryRole() {
    var roleName = $("#queryRoleName").textbox("getValue");
    var roleCode = $("#queryRoleCode").textbox("getValue");
    var valid = $("#queryValid").combobox("getValue");
    var data = {
        roleName: roleName,
        roleCode: roleCode,
        valid: valid
    };
    $("#roleGrid").datagrid("options").url = path + "/role/queryRole";
    $("#roleGrid").datagrid("load", data);
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
            title: "角色更改窗口",
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
        var $gs = $("#roleGrid").datagrid("getSelected");
        $("#roleName").textbox("setValue", $gs.roleName);
        $("#roleCode").textbox("setValue", $gs.roleCode);
        $("#orders").numberbox("setValue", $gs.orders);
        $("#valid").combobox("setValue", $gs.valid);
        $("#dbid").text($gs.dbid);

        $("#addCPM").window('open');
    })
})
//授权

function sq(dbid) {
    $("#sqCPM").window({
        modal: true,
        closed: true,
        closable: false,
        title: "角色更改窗口",
        iconCls: 'icon-save',
        height: 350,
        width: 300,
        collapsible: false,
        minimizable: false,
        maximizable: false,
        footer: '#footer1',
        onBeforeClose: function () {

        }
    })
    sqTree(dbid);
    $("#sqCPM").window('open');
}
function closeSq() {
    $("#sqCPM").window('close');
}
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
//授权提交
$(function () {
    $("#sqSubmit").click(function () {
        var arr = [];
        arr.push(0);
        var $gs = $("#roleGrid").datagrid("getSelected");
        var roleId = $gs.dbid;
        var checkeds = $('#tt').tree('getChecked', 'checked');
        for (var i = 0; i < checkeds.length; i++) {
            arr.push(checkeds[i].id);
        }
        //alert(arr);
        //alert(roleId)

        var data = {
            "arr": arr,
            "roleId": roleId
        }
        url = path + '/role_auth/addAuth'

        $.ajax({
            url: url,
            data: data,
            method: "post",
            traditional:true,
            dataType: "json",
            success: function (result) {
                if (result.success) {
                    $.messager.alert("提示", "操作成功！")

                } else {
                    $.messager.alert("提示", "角色权限以清空！")

                }
                closeSq();

            }

        })

    })
})

//删除
$(function () {
    $("#role_del").click(function () {

        var $gs = $("#roleGrid").datagrid("getSelected");

        if ($gs.dbid == 1) {
            $.messager.alert('提示', '<span style="color: red">'+$gs.roleName+'</span>角色不可删除!');
            return;
        }
        var data = {};
        data.dbid = $gs.dbid+"";

        var url = path + "/role/delete";
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