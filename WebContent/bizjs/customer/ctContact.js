$(function () {
    initContactGrid();
    query();

    $("#contactQuery").click(function () {
        query();
    })

    $("#contactAdd").click(function () {
        initContactAddWin();
    })

    $("#contactFresh").click(function () {
        window.location.reload();
    })

    $("#contactUpdate").click(function () {
        initContactUpdateWin();
    })

    $("#contactSubmit").click(function () {
        contactSave();
    })

    $("#contactDelete").click(function () {
        contactDelete();
    })
    
    /*$("#contactOut").click(function () {
        contactOut();
    })*/

    $("#closeWin").click(function () {
        $("#contactAddWin").window('close');
    })
})
function initContactGrid() {
    $("#contactGrid").datagrid({
        rownumbers: true,
        singleSelect: true,
        fitColumns: "true",
        fit: true,
        onRowContextMenu: onRowContextMenu,
        autoRowHeight: false,
        //是否分页
        pagination: true,
        //每页多少条
        pageSize: 5,
        pageList: [3, 5, 10, 20],
        columns: [[
            {field: 'ctName', title: '所属客户',align:'center',width: 100},
            {field: 'conName', title: '联系人姓名',align:'center', width: 100},
            {field: 'conSex', title: '性别', align:'center',width: 100,
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "男";
                    } else {
                        return "女";
                    }
                }
            },
            {field: 'conAge', title: '年龄', align:'center',width: 100},
            {field: 'conTel', title: '联系方式', align:'center',width: 100},
            {field: 'conJob', title: '职业',align:'center', width: 100},
            {field: 'ctCon', title: '与客户关系', align:'center',width: 100}
        ]]

    })
}

function query() {
    var text = $("#queryText").textbox("getValue");
    var type = $("#queryType").combobox("getValue");
    var data = {
        text: text,
        type: type
    };
    $("#contactGrid").datagrid("options").url = path + "/ctcontact/getContact";
    $("#contactGrid").datagrid("load", data);
}

function onRowContextMenu(e, rowIndex, rowData) {
    if (rowData) {
        e.preventDefault();
        $(this).datagrid("selectRow", rowIndex);
        $('#m1').menu('show', {left: e.pageX, top: e.pageY});
    }
}

function initContactAddWin() {
    $("#contactAddWin").window({
        modal: true,
        closed: true,
        closable: false,
        title: "客户操作窗口",
        iconCls: 'icon-save',
        height: 250,
        width: 600,
        collapsible: false,
        minimizable: false,
        maximizable: false,
        footer: '#footer',
        onBeforeClose: closeAddWin
    })
    $("#contactAddWin").window('open');
    $('#ctName').combobox({
        url:path + "/ctcontact/getCustomer",
        method:'get',
        valueField:'dbid',
        panelHeight:'auto',
        panelMaxHeight:100,
        textField:'ctName',
    });
}

function initContactUpdateWin() {
    initContactAddWin();
    var row = $("#contactGrid").datagrid("getSelected");
    $("#ctName").combobox("setValue", row.ctName);
    $("#conName").textbox("setValue", row.conName);
    $("#conSex").combobox("setValue", row.conSex);
    $("#conAge").textbox("setValue", row.conAge);
    $("#conTel").textbox("setValue", row.conTel);
    $("#conJob").textbox("setValue", row.conJob);
    $("#ctCon").textbox("setValue", row.ctCon);
    $("#dbid").text(row.dbid);
}

function contactSave() {
        var dbid = $("#dbid").text();
        var ctName = $("#ctName").combobox("getValue");
        var conName = $("#conName").textbox("getValue");
        var conSex = $("#conSex").combobox("getValue");
        var conAge = $("#conAge").textbox("getValue");
        var conTel = $("#conTel").textbox("getValue");
        var conJob = $("#conJob").textbox("getValue");
        var ctCon = $("#ctCon").textbox("getValue");
        if(ctName==""||ctName==null||conName==""||conName==null||conTel==""||conTel==null){
            $.messager.alert("提示", "请完善信息！");
            return;
        }
        var data = {
            dbid: dbid,
            ctName: ctName,
            conName: conName,
            conSex: conSex,
            conAge: conAge,
            conTel: conTel,
            conJob: conJob,
            ctCon:ctCon
        };
        var url = path + "/ctcontact/saveOrUpdate";
        $.ajax({
            url: url,
            data: data,
            dataType: "json",
            method: "post",
            success: function (result) {
                if (result.success) {
                    $.messager.alert("提示", "操作成功！");
                    query();
                } else {
                    $.messager.alert("错误", "提交异常！")
                }
                $("#contactAddWin").window('close');
            }
        })
}

function contactDelete() {



    var row = $("#contactGrid").datagrid("getSelected");
    var dbid=row.dbid;
    var data={
        dbid:dbid
    };
    var url=path+"/ctcontact/deleteContact";
    $.messager.confirm("删除", "是否删除", function (r) {
        if(!r){
            return;
        }
        $.ajax({
            url:url,
            data:data,
            dataType:"json",
            method:"post",
            success: function (result) {
                if (result.success) {
                    $.messager.alert("提示", "删除成功！");
                    query();
                } else {
                    $.messager.alert("错误", "删除异常！")
                }
                closeAddWin();
            }
        })
    })

}

/*function contactOut() {
    var url=path+"/ctcontact/exportContact";
    $.ajax({
        url:url,
        data:{
            fileName:"联系人信息"
        },
        success:function () {
            $.messager.alert("提示", "导出成功！");
        }
    })
}*/


function closeAddWin() {
    $("#ctName").textbox("setValue", "");
    $("#conName").textbox("setValue", "");
    $("#conSex").combobox("setValue", "1");
    $("#conAge").textbox("setValue", "");
    $("#conTel").textbox("setValue", "");
    $("#conJob").textbox("setValue", "");
    $("#ctCon").textbox("setValue", "");
    $("#dbid").text("");
}



