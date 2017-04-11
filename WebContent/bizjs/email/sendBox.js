/**
 * Created by Administrator on 2017/3/6.
 */
$(function () {
    /*valid(["email_receive","email_main"]);*/
    initEmailGrid();
    queryEmails();
    initConfirmWin();
    cofirmWinClose();
});
function initEmailGrid() {

    $("#emailGrid").datagrid({
        rownumbers: true,
        singleSelect: true,
        fitColumns: "true",
        fit: true,
        autoRowHeight: false,
        pagination: true,
        pageSize: 5,
        pageList: [5, 10, 20, 50],
        columns: [[
            {field: 'itemid', title: '序号', width: 20},
            {field: 'sendName', title: '发件人', width: 100},
            {field: 'receiveName', title: '收件人', width: 100},
            {field: 'theme', title: '主题', width: 100},
            {field: 'detail', title: '邮件内容', width: 100},
            {field: 'time', title: '发送时间', width: 100},
            {
                field: 'dbid', title: '执行操作', width: 100, align: 'center',
                formatter: function () {
                    return "<a href='#' style='color: red;text-decoration: none;' onclick='cofirmWinOpen()'>删除</a>"
                }
            }
        ]]
    });
}


function removeEmail() {
    var node = $('#emailGrid').datagrid('getSelected');
    var dbid = node.dbid;
    var url = path + '/jump/removeFromSend';
    var data = {
        dbid: dbid
    }
    $.ajax({
        url: url,
        data: data,
        dataType: "json",
        method: "post",
        success: function (response) {
            if (response.success) {
                $.messager.alert("提示", "操作成功");
                //cofirmWinClose();
            } else {
                $.messager.alert("提示", "网络异常,删除失败!");
            }
            queryEmails();
        }
    });
}

function refreshEmailDataGrid() {
    $('#emailGrid').datagrid('reload');
}


function queryEmails() {
    /*if (hasPermission("role_query")){*/
    var queryText = $("#queryText").textbox("getValue");
    var queryMethodId = $("#email_main").combobox("getValue");
    var data = {
        queryText: queryText,
        queryMethodId: queryMethodId
    };
    $("#emailGrid").datagrid("options").url = path + "/jump/queryEmail";
    $("#emailGrid").datagrid("load", data);
    /*}*/
}

function initConfirmWin() {
    $("#confirmWin").window({
        cls: 'c2',
        footer: '#footer',
        width: 300,
        height: 200,
        modal: true,
        title: '确认删除?',
        collapsible: false,
        minimizable: false,
        maximizable: false,
        resizable: false
    });
}


function cofirmWinOpen() {
    $.messager.confirm("删除", "是否删除", function (r) {
        if (!r) {
            return;
        }
        removeEmail();
    })


    /*$("#confirmWin").window('open');*/
}

function cofirmWinClose() {
    $("#confirmWin").window('close');
}



