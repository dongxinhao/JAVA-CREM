/**
 * Created by zh_ge on 2017/3/28.
 */
$(function () {
    initDeptGrid();
    queryDeptMsg();
    initDeptEditWin()
});

function initDeptGrid(){
    $("#deptGrid").datagrid({
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
            {field: 'deptName', title: '部门名称', width: 100},
            {field: 'deptIntro', title: '部门描述', width: 100}
        ]]
    })
}

function queryDeptMsg(){
    var deptName = $("#deptName").textbox("getValue");
    var data = {
        deptName: deptName
    };
    $("#deptGrid").datagrid("options").url = path+"/staffAction/queryDept";
    $("#deptGrid").datagrid("load", data);
    $("#deptName").textbox("setValue","");
}

function initDeptEditWin() {
    $("#deptEditWin").window({
        modal: true,
        closed: true,
        iconCls: 'icon-save',
        title: "部门信息编辑窗口",
        height: 280,
        width: 400,
        collapsible: false,
        minimizable: false,
        maximizable: false,
        onBeforeClose:function () {
            var dbid=$("#dbid").val("");
            var deptName=$("#editDeptName").textbox("setValue","");
            var deptIntro=$("#deptIntro").textbox("setValue","");
        }

    })
}

function showDeptEditWin(){
    $("#deptEditWin").window("open");
}

function closeDeptEditWin() {
    $("#deptEditWin").window("close");
}

function onRowContextMenu(e, rowIndex, rowData) {
    if (rowData) {
        e.preventDefault();
        $(this).datagrid("selectRow", rowIndex);
        $('#deptMenu').menu('show', {left: e.pageX, top: e.pageY});

    }
}


function showDeptUpdateWin(){
    var gs = $("#deptGrid").datagrid("getSelected");
    $("#dbid").val(gs.dbid);
    $("#editDeptName").textbox("setValue",gs.deptName);
    $("#deptIntro").textbox("setValue",gs.deptIntro);
    showDeptEditWin();
}

function saveDeptMsg(){
    var dbid=$("#dbid").val();
    var deptName=$("#editDeptName").textbox("getValue");
    var deptIntro=$("#deptIntro").textbox("getValue");

    var data={
        dbid:dbid,
        deptName:deptName,
        deptIntro:deptIntro,
    }

    url=path+"/staffAction/saveOrUpdateDept"

    $.ajax({
        url:url,
        data:data,
        dataType:"json",
        method:"post",
        success:function(response){
            if (response.success){
                $.messager.alert("提示","操作成功！")
            }else{
                $.messager.alert("错误","网络异常！")
            }
            closeDeptEditWin();
            queryDeptMsg();
        }
    })
}

function deleteDeptMsg(){
    var gs = $("#deptGrid").datagrid("getSelected");

    var data = {};
    data.dbid = gs.dbid;
    data.deptName = gs.deptName;
    var url = path + "/staffAction/deleteDept";
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
                    initDeptGrid();
                }
            })
        }
    })
}