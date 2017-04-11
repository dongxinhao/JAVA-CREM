/**
 * Created by Administrator on 2017/3/6.
 */
$(function () {
    /*valid(["email_receive","email_main"]);*/
    initEmailGrid();
    queryEmails();
    initSendConfirmWin();
    initConfirmWin();
    confirmWinClose();
    sendConfirmWinClose();
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
        pageList: [ 5 , 10 , 20 , 50 ],
        columns: [[
            {field: 'itemid', title: '序号', width: 20},
            {field: 'sendName', title: '发件人', width: 100},
            {field: 'receiveName', title: '收件人', width: 100},
            {field: 'theme', title: '主题', width: 100},
            {field: 'detail', title: '邮件内容', width: 100},
            {field: 'time', title: '发送时间', width: 100},
            {field: 'dbid', title: '执行操作', width: 100,align:'center',
                formatter:function () {
                    return "<a href='#' style='color: blue;text-decoration: none;' onclick='sendConfirmWinOpen()'>发送</a>&nbsp;      &nbsp;&nbsp;      &nbsp;<a href='#' style='color: red;text-decoration: none;' onclick='confirmWinOpen()'>删除</a>"
                }
            }
        ]]
    });
}


function removeEmail() {
    var node = $('#emailGrid').datagrid('getSelected');
    var dbid = node.dbid;
    var url = path+'/jump/removeFromDraft';
    var data ={
        dbid:dbid
    };

    $.ajax({
       url:url,
        data:data,
        dataType:"json",
        method:"post",
        success:function (response) {
            if (response.success){
                $.messager.alert("提示","操作成功");


            }else{
                $.messager.alert("提示","网络异常,删除失败!");

            }

            queryEmails();

        }
    });
}

function sendEmail() {
    var node = $("#emailGrid").datagrid("getSelected");
    var dbid = node.dbid;
    var url = path+'/jump/sendFromDraft';
    var data ={
        dbid:dbid
    };
    $.ajax({
       url:url,
        data:data,
        dataType:"json",
        method:"post",
        success:function (response) {
            if (response.success){
                $.messager.alert("提示","邮件发送成功!");
                sendConfirmWinClose();
                refreshEmailDataGrid();
            }else{
                $.messager.alert("提示","网络错误,请联系管理员!");
                refreshEmailDataGrid();
            }
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
        $("#emailGrid").datagrid("options").url = path + "/jump/queryEmailFromDraftBox";
        $("#emailGrid").datagrid("load", data);
    /*}*/
}

function initConfirmWin() {
    $("#confirmWin").window({
        footer:'#footer',
        width:300,
        height:200,
        modal:true,
        title:'确认删除?',
        collapsible:false,
        minimizable:false,
        maximizable:false,
        resizable:false
    });
}

function initSendConfirmWin() {
    $("#sendConfirmWin").window({
        footer:'#sendFooter',
        width:300,
        height:200,
        modal:true,
        title:'确认发送邮件?',
        collapsible:false,
        minimizable:false,
        maximizable:false,
        resizable:false
    });
}




function confirmWinOpen() {
    $.messager.confirm("删除", "是否删除", function (r) {
        if (!r) {
            return;
        }
        removeEmail();
    })
    /*$("#confirmWin").window('open');*/
}

function sendConfirmWinOpen() {
    $("#sendConfirmWin").window('open');
}

function confirmWinClose() {
    $("#confirmWin").window('close');
}


function sendConfirmWinClose() {
    $("#sendConfirmWin").window('close');
}
