/**
 * Created by Administrator on 2017/3/6.
 */
$(function () {
    valid(["cust_add","cust_del","cust_query","cust_update"])
    initRoleGrid();
    if(hasRes("cust_query")){
        query();
    }
    initSelectStaffWin();
    initStaffGrid();
    initSelectctStatusWin();
    initctStatusGrid();
    initSelectctSourceWin();
    initctSourceGrid();
})
function initRoleGrid() {
    $("#custGrid").datagrid({
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
            {field: 'ctName', title: '姓名', width: 90},
            {field: 'ctStatus', title: '状态', width: 90},
            {field: 'ctSource', title: '来源', width: 90},
            {field: 'ctEmp', title: '所属员工', width: 90},
            {field: 'ctType', title: '类型', width: 90},
            {field: 'ctSex', title: '性别', width: 90},
            {field: 'ctTel', title: '手机', width: 90},
            {field: 'ctQQ', title: 'QQ', width: 90},
            {field: 'ctEmail', title: '邮箱', width: 90},
            {field: 'ctJob', title: '职位', width: 90},
            {field: 'ctComp', title: '公司', width: 90},
            {field: 'ctMessage', title: '备注', width: 90},
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
    var ctName = $("#queryCustName").textbox("getValue");
    var valid = $("#queryValid").combobox("getValue");
    var data = {
        ctName: ctName,
        valid: valid
    };
    function ctStatus() {
        var $gs = $("#authTree").treegrid("getSelected");
        var dbid=$gs.dbid;
        var ctStatus=$gs.ctStatus;
        $('#moveCombobox').combobox({
            url:path + '/customer/selectAllStatusByLayer?dbid='+dbid+'&ctStatus='+ctStatus,
            method:'get',
            valueField:'dbid',
            panelHeight:100,
            groupField:'group',
            textField:'authName',
            labelPosition: 'top',

        });
    }
    $("#custGrid").datagrid("options").url = path + "/customer/queryCustomer";
    $("#custGrid").datagrid("load", data);
}
$(function () {
    $("#cust_add").click(function () {
        $("#addCPM").window({
            modal: true,
            closed: true,
            closable: false,
            title: "用户添加窗口",
            iconCls: 'icon-save',
            height: 430,
            width: 700,
            collapsible: false,
            minimizable: false,
            maximizable: false,
            footer: '#footer',
            onBeforeClose: function () {
            }
        })
        $("#addCPM").window('open');
    })
})
//关闭添加窗口
function closeAdd() {
    $("#dbid").text("");
    $("#ctName").textbox("setValue","");
    $("#ctStatus").textbox("setValue","");
    $("#ctSource").textbox("setValue","");
    $("#ctEmp").textbox("setValue","");
    $("#ctType").textbox("setValue","");
    $("#ctSex").combobox("setValue","1");
    $("#ctTel").textbox("setValue","");
    $("#ctQQ").textbox("setValue","");
    $("#ctEmail").textbox("setValue","");
    $("#ctJob").textbox("setValue","");
    $("#ctComp").textbox("setValue","");
    $("#ctMessage").textbox("setValue","");
    $("#addCPM").window('close');
}
//添加保存按钮
$(function () {
    $("#addSubmit").click(function () {
        var dbid = $("#dbid").text();
        var ctName = $("#ctName").textbox("getValue");
        var ctStatus = $("#ctStatus").textbox("getValue");
        var ctSource = $("#ctSource").textbox("getValue");
        var ctEmp = $("#ctEmp").textbox("getValue");
        var ctType = $("#ctType").textbox("getValue");
        var ctSex = $("#ctSex").combobox("getValue");
        var ctTel = $("#ctTel").textbox("getValue");
        var ctQQ = $("#ctQQ").textbox("getValue");
        var ctEmail = $("#ctEmail").textbox("getValue");
        var ctJob = $("#ctJob").textbox("getValue");
        var ctComp = $("#ctComp").textbox("getValue");
        var ctMessage = $("#ctMessage").textbox("getValue");
        var data = {
            dbid: dbid,
            ctName: ctName,
            ctStatus: ctStatus,
            ctSource: ctSource,
            ctEmp: ctEmp,
            ctType: ctType,
            ctSex: ctSex,
            ctTel: ctTel,
            ctQQ: ctQQ,
            ctEmail: ctEmail,
            ctJob: ctJob,
            ctComp: ctComp,
            ctMessage: ctMessage,
        };
        var url = path + "/customer/saveOrUpdate";
        if(ctName==""||ctName==null){
            $(".emptyS").show();
        } else if (ctStatus == "") {
                $(".emptyS").show();
            } else if (!(/^1(3|4|5|7|8)\d{9}$/.test(ctTel))) {
            $.messager.alert('', '请输入正确的手机!', 'info');
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
    $("#cust_update").click(function () {
        $("#addCPM").window({
            modal: true,
            closed: true,
            closable: false,
            title: "客户更改窗口",
            iconCls: 'icon-save',
            height: 430,
            width: 700,
            collapsible: false,
            minimizable: false,
            maximizable: false,
            footer: '#footer',
            onBeforeClose: function () {

            }
        })
        var $gs = $("#custGrid").datagrid("getSelected");
        $("#ctName").textbox("setValue", $gs.ctName);
        $("#ctSource").textbox("setValue", $gs.ctSource);
        $("#ctStatus").textbox("setValue",$gs.ctStatus);
        $("#ctEmp").textbox("setValue", $gs.ctEmp);
        $("#ctType").textbox("setValue", $gs.ctType);
        $("#ctSex").combobox("setValue", $gs.ctSex);
        $("#ctTel").textbox("setValue", $gs.ctTel);
        $("#ctQQ").textbox("setValue", $gs.ctQQ);
        $("#ctEmail").textbox("setValue", $gs.ctEmail);
        $("#ctJob").textbox("setValue", $gs.ctJob);
        $("#ctComp").textbox("setValue", $gs.ctComp);
        $("#ctMessage").textbox("setValue", $gs.ctMessage);
        $("#dbid").text($gs.dbid);
        $("#addCPM").window('open');
    })
})
function closeSq() {
    $("#sqCPM").window('close');
}

//删除
$(function () {
    $("#cust_del").click(function () {
        var $gs = $("#custGrid").datagrid("getSelected");
        var data = {};
        data.dbid = $gs.dbid;
        var url = path + "/customer/deleteCust";
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
                        } else{
                            $.messager.alert('提示', '网络异常!');
                        }
                        initRoleGrid();
                    }
                })
            }
        })
    })
})

function queryStaffName(){
    var ctType = $("#queryStaffName").textbox("getValue");
    var data = {
        ctType: ctType
    };
    $("#staffGrid").datagrid("options").url = path+"/cttype/queryCttype";
    $("#staffGrid").datagrid("load", data);
}
function showSelectStaffWin(){
    queryStaffName();
    $("#selectStaffWin").window("open");
}
function closeSelectStaffWin() {
    $("#selectStaffWin").window("close");
}
function initSelectStaffWin() {
    $("#selectStaffWin").window({
        modal: true,
        closed: true,
        iconCls: 'icon-save',
        title: "类型选择窗口",
        height: 400,
        width: 450,
        collapsible: false,
        minimizable: false,
        maximizable: false,
        onBeforeClose:function () {
            $("#queryStaffName").textbox("setValue","");
        }
    })
}
function initStaffGrid(){
    $("#staffGrid").datagrid({
        rownumbers: true,
        singleSelect: true,
        fitColumns: "true",
        height: 300,
        autoRowHeight: false,
        //是否分页
        pagination: true,
        // onRowContextMenu: onRowContextMenu,
        //每页多少条
        pageSize: 10,
        pageList: [3, 5, 10, 20],
        columns: [[
            {field: 'ctType', title: '类型', width: 50 },
        ]],
        onDblClickRow:function(rowIndex,rowData){
            $("#ctType").textbox("setValue",rowData.ctType)
            closeSelectStaffWin();
        }
    })
}



function queryctStatusName(){
    var ctStatus = $("#queryctStatusName").textbox("getValue");
    var data = {
        ctStatus: ctStatus
    };
    $("#ctStatusGrid").datagrid("options").url = path+"/ctstatus/queryCtstatus";
    $("#ctStatusGrid").datagrid("load", data);
}
function showSelectctStatusWin(){
    queryctStatusName();
    $("#selectctStatusWin").window("open");
}
function closeSelectctStatusWin() {
    $("#selectctStatusWin").window("close");
}
function initSelectctStatusWin() {
    $("#selectctStatusWin").window({
        modal: true,
        closed: true,
        iconCls: 'icon-save',
        title: "状态选择窗口",
        height: 400,
        width: 450,
        collapsible: false,
        minimizable: false,
        maximizable: false,
        onBeforeClose:function () {
            $("#queryctStatusName").textbox("setValue","");
        }
    })
}
function initctStatusGrid(){
    $("#ctStatusGrid").datagrid({
        rownumbers: true,
        singleSelect: true,
        fitColumns: "true",
        height: 300,
        autoRowHeight: false,
        //是否分页
        pagination: true,
        // onRowContextMenu: onRowContextMenu,
        //每页多少条
        pageSize: 10,
        pageList: [3, 5, 10, 20],
        columns: [[
            {field: 'ctStatus', title: '状态', width: 50 },
            {field: 'statusMessage', title: '备注', width: 50 },
        ]],
        onDblClickRow:function(rowIndex,rowData){
            $("#ctStatus").textbox("setValue",rowData.ctStatus)
            closeSelectctStatusWin();
        }
    })
}




/*来源查询*/

function queryctStatusName(){
    var ctStatus = $("#queryctStatusName").textbox("getValue");
    var data = {
        ctStatus: ctStatus
    };
    $("#ctStatusGrid").datagrid("options").url = path+"/ctstatus/queryCtstatus";
    $("#ctStatusGrid").datagrid("load", data);
}
function showSelectctStatusWin(){
    queryctStatusName();
    $("#selectctStatusWin").window("open");
}
function closeSelectctStatusWin() {
    $("#selectctStatusWin").window("close");
}
function initSelectctStatusWin() {
    $("#selectctStatusWin").window({
        modal: true,
        closed: true,
        iconCls: 'icon-save',
        title: "状态选择窗口",
        height: 400,
        width: 450,
        collapsible: false,
        minimizable: false,
        maximizable: false,
        onBeforeClose:function () {
            $("#queryctStatusName").textbox("setValue","");
        }
    })
}
function initctStatusGrid(){
    $("#ctStatusGrid").datagrid({
        rownumbers: true,
        singleSelect: true,
        fitColumns: "true",
        height: 300,
        autoRowHeight: false,
        //是否分页
        pagination: true,
        // onRowContextMenu: onRowContextMenu,
        //每页多少条
        pageSize: 10,
        pageList: [3, 5, 10, 20],
        columns: [[
            {field: 'ctStatus', title: '状态', width: 50 },
            {field: 'statusMessage', title: '备注', width: 50 },
        ]],
        onDblClickRow:function(rowIndex,rowData){
            $("#ctStatus").textbox("setValue",rowData.ctStatus)
            closeSelectctStatusWin();
        }
    })
}






/*来源查询*/

function queryctSourceName(){
    var ctSource = $("#queryctSourceName").textbox("getValue");
    var data = {
        ctSource: ctSource
    };
    $("#ctSourceGrid").datagrid("options").url = path+"/ctsource/queryCtsource";
    $("#ctSourceGrid").datagrid("load", data);
}
function showSelectctSourceWin(){
    queryctSourceName();
    $("#selectctSourceWin").window("open");
}
function closeSelectctSourceWin() {
    $("#selectctSourceWin").window("close");
}
function initSelectctSourceWin() {
    $("#selectctSourceWin").window({
        modal: true,
        closed: true,
        iconCls: 'icon-save',
        title: "状态选择窗口",
        height: 400,
        width: 450,
        collapsible: false,
        minimizable: false,
        maximizable: false,
        onBeforeClose:function () {
            $("#queryctSourceName").textbox("setValue","");
        }
    })
}
function initctSourceGrid(){
    $("#ctSourceGrid").datagrid({
        rownumbers: true,
        singleSelect: true,
        fitColumns: "true",
        height: 300,
        autoRowHeight: false,
        //是否分页
        pagination: true,
        // onRowContextMenu: onRowContextMenu,
        //每页多少条
        pageSize: 10,
        pageList: [3, 5, 10, 20],
        columns: [[
            {field: 'ctSource', title: '状态', width: 50 },
            {field: 'sourceMessage', title: '备注', width: 50 },
        ]],
        onDblClickRow:function(rowIndex,rowData){
            $("#ctSource").textbox("setValue",rowData.ctSource)
            closeSelectctSourceWin();
        }
    })
}