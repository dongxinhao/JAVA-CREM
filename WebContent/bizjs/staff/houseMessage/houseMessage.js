/**
 * Created by zh_ge on 2017/3/28.
 */
$(function () {
    initHouseGrid();
    queryHouseMsg();
    initHouseEditWin();
    initStaffGrid();
    initSelectStaffWin();
});

function initHouseGrid(){
    $("#houseGrid").datagrid({
        rownumbers: true,
        singleSelect: true,
        fitColumns: "true",
        fit: true,
        // idField:"dbid",
        autoRowHeight: false,
        //是否分页
        pagination: true,
        onRowContextMenu: onRowContextMenu,
        //每页多少条
        pageSize: 10,
        pageList: [3, 5, 10, 20],
        columns: [[
            {field: 'type', title: '户型', width: 100},
            {field: 'staffName', title: '管理员工', width: 100},
            {field: 'address', title: '地址', width: 100},
            {field: 'price', title: '价格(元/㎡)', width: 100},
            {field: 'env', title: '环境', width: 100}
        ]]
    })
}

function queryHouseMsg(){
    var keyword = $("#keyword").textbox("getValue");
    var queryType = $("#queryType").combobox("getValue");
    var data = {
        keyword: keyword,
        queryType: queryType
    };
    $("#houseGrid").datagrid("options").url = path+"/staffAction/queryHouse";
    $("#houseGrid").datagrid("load", data);
}

function queryStaffName(){
    var staffName = $("#queryStaffName").textbox("getValue");
    var data = {
        realName: staffName
    };
    $("#staffGrid").datagrid("options").url = path+"/user/queryUser";
    $("#staffGrid").datagrid("load", data);
}

function initHouseEditWin() {
    $("#houseEditWin").window({
        modal: true,
        closed: true,
        iconCls: 'icon-save',
        title: "房屋信息编辑窗口",
        height: 350,
        width: 400,
        collapsible: false,
        minimizable: false,
        maximizable: false,
        onBeforeClose:function () {
            var dbid=$("#dbid").val("");
            var type=$("#type").textbox("setValue","");
            var staffName=$("#staffName").textbox("setValue","");
            var address=$("#address").textbox("setValue","");
            var price=$("#price").textbox("setValue","");
            var env=$("#env").textbox("setValue","");
        }

    })
}

function showHouseEditWin(){
    $("#houseEditWin").window("open");
}

function closeHouseEditWin() {
    $("#houseEditWin").window("close");
}

function initStaffGrid(){
    $("#staffGrid").datagrid({
        rownumbers: true,
        singleSelect: true,
        fitColumns: "true",
        height: 300,
        autoRowHeight: false,
        pagination: true,
        pageSize: 10,
        pageList: [3, 5, 10, 20],
        columns: [[
            {field: 'realName', title: '员工姓名', width: 50 },
            {field: 'TEL', title: '手机'}
        ]],
        onDblClickRow:function(rowIndex,rowData){
            $("#staffName").textbox("setValue",rowData.realName)
            closeSelectStaffWin();
        }
    })
}

function initSelectStaffWin() {
    $("#selectStaffWin").window({
        modal: true,
        closed: true,
        iconCls: 'icon-save',
        title: "员工选择窗口",
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

function showSelectStaffWin(){
    queryStaffName();
    $("#selectStaffWin").window("open");
}

function closeSelectStaffWin() {
    $("#selectStaffWin").window("close");
}

function saveHouseMsg(){
    var dbid=$("#dbid").val();
    var type=$("#type").textbox("getValue");
    var staffName=$("#staffName").textbox("getValue");
    var address=$("#address").textbox("getValue");
    var price=$("#price").textbox("getValue");
    var env=$("#env").textbox("getValue");

    var data={
        dbid:dbid,
        type:type,
        staffName:staffName,
        address:address,
        price:price,
        env:env
    }

    url=path+"/staffAction/saveOrUpdateHouse"

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
            closeHouseEditWin();
            queryHouseMsg();
        }
    })
}


function onRowContextMenu(e, rowIndex, rowData) {
    if (rowData) {
        e.preventDefault();
        $(this).datagrid("selectRow", rowIndex);
        $('#staffMenu').menu('show', {left: e.pageX, top: e.pageY});

    }
}

function showHouseUpdateWin(){
    var gs = $("#houseGrid").datagrid("getSelected");
    $("#dbid").val(gs.dbid);
    $("#type").textbox("setValue",gs.type);
    $("#staffName").textbox("setValue",gs.staffName);
    $("#address").textbox("setValue",gs.address);
    $("#price").textbox("setValue",gs.price);
    $("#env").textbox("setValue",gs.env);
    showHouseEditWin();
}

function deleteHouseMsg(){
    var gs = $("#houseGrid").datagrid("getSelected");

    var data = {};
    data.dbid = gs.dbid;
    var url = path + "/staffAction/deleteHouse";
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
                    initHouseGrid();
                }
            })
        }
    })
}