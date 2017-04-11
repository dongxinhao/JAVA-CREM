$(function () {
    initNoticeGrid();
    query();
});
function initNoticeGrid() {
    $("#noticeGrid").datagrid({
        rownumbers: true,
        singleSelect: true,
        fitColumns: "true",
        fit: true,
        autoRowHeight: false,
        //是否分页
        pagination: true,
        onRowContextMenu: onRowContextMenu,
        //每页多少条
        pageSize: 20,
        pageList: [10,20,30,50],
        columns: [[
            {field: 'noticeName', title: '公告人', width: 30},
            {field: 'theme', title: '公告主题', width: 60},
            {field: 'content', title: '公告内容', width: 160},
            {field: 'start', title: '公告时间', width: 80},
            {field: 'end', title: '公告截止时间', width: 80}
        ]]
    })
}
function query() {
    var noticeContent = $("#queryNoticeContent").textbox("getValue");
    var mode = $("#queryMode").combobox("getValue");
    var data = {
        noticeContent: noticeContent,
        mode: mode
    };
    // console.log(data);
    $("#noticeGrid").datagrid("options").url = path + "/notice/queryNotice";
    $("#noticeGrid").datagrid("load", data);
}
function onRowContextMenu(e, rowIndex, rowData) {
    if (rowData) {
        e.preventDefault();
        $(this).datagrid("selectRow", rowIndex);
        $('#meu1').menu('show', {left: e.pageX, top: e.pageY});
    }
}
/*删除*/
$(function () {
    $("#notice_del").click(function () {
        var $gs = $("#noticeGrid").datagrid("getSelected");
        var noticeName=$gs.noticeName;
        if (userNameCenter!=noticeName){
            $.messager.alert("提示", "你不能删别人的啊！")
        }else {
        var data = {};
        data.dbid = $gs.dbid;
        var url = path + "/notice/deleteNotice";
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
                        initNoticeGrid();
                    }
                })
            }
        })
        }
    })
});
$(function () {
    $("#notice_add").click(function () {
        $("#addNOT").window({
            modal: true,
            closed: true,
            closable: false,
            title: "用户添加窗口",
            iconCls: 'icon-save',
            height: 350,
            width: 600,
            collapsible: false,
            minimizable: false,
            maximizable: false,
            footer: '#footer',
            onBeforeClose: function () {
                /* $(".dl").load(location.href+" .dl");*/
            }
        })
        $("#addNOT").window('open');
    })
});
function closeAdd() {
    $("checkbox").attr("checked",false);
    $(".emptyS").hide();
    $("#addNOT").window('close');
    $("#theme").textbox("setValue", "");
    $("#content").textbox("setValue", "");
    $("#dbid").text("");
};
/*获取当前系统时间*/
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + date.getHours() + seperator2 + date.getMinutes()
        + seperator2 + date.getSeconds();
    return currentdate;
}
//添加保存按钮
$(function () {
    $("#addSubmit").click(function () {
        var dbid = $("#dbid").text();
        var noticeName = userNameCenter;
        var theme = $("#theme").textbox("getValue");
        var content = $("#content").textbox("getValue");
        var start = getNowFormatDate();
        var end = $('#end').datetimebox('getValue');
        if(theme==""){
            $.messager.alert("提示", "主题不能不写啊！")
        }else if (content==""){
            $.messager.alert("提示", "内容不能不写啊！")
        }else {
            var data = {
                dbid: dbid,
                noticeName: noticeName,
                theme: theme,
                content: content,
                start: start,
                end: end
            };
            var url = path + "/notice/saveOrUpdate";
            $.ajax({
                url: url,
                data: data,
                dataType: "json",
                method: "post",
                success: function (result) {
                    if (result.success) {
                        $.messager.alert("提示", "操作成功！")
                        initNoticeGrid();
                    } else {
                        $.messager.alert("错误", "提交异常，角色名称重复或网络异常！")
                    }
                    closeAdd();
                }
            })
        }
    })
});
//更改事件
$(function () {
    $("#notice_update").click(function () {
        $("#addNOT").window({
            modal: true,
            closed: true,
            closable: false,
            title: "用户更改窗口",
            iconCls: 'icon-save',
            height: 350,
            width: 600,
            collapsible: false,
            minimizable: false,
            maximizable: false,
            footer: '#footer',
            onBeforeClose: function () {

            }
        });
        var $gs = $("#noticeGrid").datagrid("getSelected");
        $("#theme").textbox("setValue",$gs.theme);
        $("#content").textbox("setValue", $gs.content);
        $("#dbid").text($gs.dbid);
        $("#addNOT").window('open');
    })
});