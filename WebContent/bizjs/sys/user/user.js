/**
 * Created by Administrator on 2017/3/6.
 */
$(function () {
    valid(["user_add","user_del","user_query","user_update"])
    initRoleGrid();
    if(hasRes("user_query")){
        query();
    }
    initSelectDeptWin ();
    initDeptGrid();
})
function initRoleGrid() {
    $("#userGrid").datagrid({
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
            {field: 'userName', title: '用户名称',align:'center', width: 50},
            {field: 'realName', title: '姓名',align:'center', width: 50},
            {field: 'userSex', title: '性别',align:'center', width: 50,
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "男";
                    } else {
                        return "<span style='color: red'>女</span>";
                    }
                }

            },
            {field: 'age', title: '年龄',align:'center', width: 50},
            {field: 'nation', title: '民族',align:'center', width: 50},
            {field: 'userDept', title: '部门', align:'center',width: 50},
            {field: 'education', title: '学历',align:'center', width: 50},
            {field: 'marriage', title: '婚姻', align:'center',width: 50,
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "<span style='color: red'>未婚</span>";
                    } else if(value == "2") {
                        return "已婚";
                    } else {
                        return "离异";
                    }
                }
            },
            {field: 'address', title: '住址',align:'center', width: 150},
            {field: 'TEL', title: '手机',align:'center', width: 120},
            {field: 'email', title: '邮箱',align:'center', width: 120},
            {field: 'valid', title: '状态',align:'center', width: 50,
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "正常";
                    } else {
                        return "<span style='color: red'>冻结</span>";
                    }
                }
            },
            {field: 'dbid', title: '授予角色',align:'center', width: 50,
                formatter: function (value, row,index) {
                    return "<b onclick='sq("+value+")'  style='color: red;cursor: pointer'>授予角色</b>";
                }
            }
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

    var userName = $("#queryUserName").textbox("getValue");
    var realName = $("#queryRealName").textbox("getValue");
    var valid = $("#queryValid").combobox("getValue");
    var data = {
        userName: userName,
        realName: realName,
        valid: valid
    };

   console.log(data);
    $("#userGrid").datagrid("options").url = path + "/user/queryUser";
    $("#userGrid").datagrid("load", data);
}

$(function () {
    $("#user_add").click(function () {
        $("#addCPM").window({
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
        $("#addCPM").window('open');
    })
})
//关闭添加窗口
function closeAdd() {
    $("checkbox").attr("checked",false);
    $(".emptyS").hide();
    $("#addCPM").window('close');
    $("#dbid").text("");
    $("#userName").textbox("setValue", "");
    $("#realName").textbox("setValue", "");
    $("#valid").combobox("setValue", "1");
    $("#password").passwordbox("setValue", "");
    $("#password1").passwordbox("setValue", "");
    //---------------------------------------------------------
    $("#age").numberbox("setValue", "");
    $("#nation").textbox("setValue", "");
    $("#education").textbox("setValue", "");
    $("#address").textbox("setValue", "");
    $("#TEL").numberbox("setValue", "");
    $("#email").textbox("setValue", "");
    $("#userSex").combobox("setValue", "1");
    $("#marriage").combobox("setValue", "1");
    $("#userDept").textbox("setValue", "");


    ////------------------------------------------------------
}
//角色添加保存按钮
$(function () {
    $("#addSubmit").click(function () {
        var dbid = $("#dbid").text();
        var userName = $("#userName").textbox("getValue");
        var realName = $("#realName").textbox("getValue");
        var valid = $("#valid").combobox("getValue");
        var password = $("#password").passwordbox("getValue");
        var password1 = $("#password1").passwordbox("getValue");
        //-------------------------------------------------
        var age=$("#age").numberbox("getValue");
        var userSex=$("#userSex").combobox("getValue");
        var nation=$("#nation").textbox("getValue");
        var userDept=$("#userDept").textbox("getValue");
        var education=$("#education").textbox("getValue");
        var marriage=$("#marriage").combobox("getValue");
        var address=$("#address").textbox("getValue");
        var TEL=$("#TEL").numberbox("getValue");
        var email=$("#email").textbox("getValue");
        //------------------------------------------------
        var data = {
            dbid: dbid,
            userName: userName,
            realName: realName,
            valid: valid,
            password: password,
            //-----------------------------------------
            age: age,
            userSex: userSex,
            nation: nation,
            userDept: userDept,
            education: education,
            marriage: marriage,
            address: address,
            TEL: TEL,
            email: email

            //--------------------------------------------------------
        };
        //console.log(data);

        var url = path + "/user/saveOrUpdate";
        if(password==""||password==null){
            $(".emptyS").show();
            return;
        }
        if(password!=password1){
            $.messager.alert("错误", "密码不一致，请重新输入！")

        }else{
            if (userName == "") {
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
    $("#user_update").click(function () {
        $("#addCPM").window({
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
        })
        var $gs = $("#userGrid").datagrid("getSelected");
        $("#userName").textbox("setValue", $gs.userName);
        $("#realName").textbox("setValue", $gs.realName);
        $("#password").passwordbox("setValue", $gs.password);
        $("#password1").passwordbox("setValue", $gs.password);
        $("#valid").combobox("setValue", $gs.valid);
        $("#dbid").text($gs.dbid);

        //--------------------------------------------------

        $("#age").numberbox("setValue", $gs.age);
        $("#nation").textbox("setValue", $gs.nation);
        $("#education").textbox("setValue", $gs.education);
        $("#address").textbox("setValue", $gs.address);
        $("#TEL").numberbox("setValue", $gs.TEL);
        $("#email").textbox("setValue", $gs.email);
        $("#userSex").combobox("setValue", $gs.userSex);
        $("#marriage").combobox("setValue", $gs.marriage);
        $("#userDept").textbox("setValue", $gs.userDept);

        //--------------------------------------------------

        $("#addCPM").window('open');
    })
})
//授权
function sq(dbid) {
    $("#sqCPM").window({
        modal: true,
        closed: true,
        closable: false,
        title: "授予角色窗口",
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

    var userId=dbid;
    $("#tt").datagrid({
        url: path + '/user_role/getValidRole?userId=' + userId,
        rownumbers: true,
        singleSelect: false,
        fitColumns: "true",
        checkbox:true,
        autoRowHeight: false,
        columns: [[
            {field: 'dbid', title: '' ,checkbox:true, width: 15},
            {field: 'roleName', title: '角色名称', width: 50,
                formatter: function (value, row, index) {
                    return "<b>"+value+"</b>";
                }
            },
            {field: 'roleCode', title: '角色编码' ,width: 50}
        ]],
        onLoadSuccess:function (data) {

            var datas=data.rows;
            for(var i=0;i<datas.length;i++){

                if(datas[i].checked){
                    $("#tt").datagrid("selectRow",i);
                }
            }
        }
    })
}
//授权提交
$(function () {
    $("#sqSubmit").click(function () {
        var arr = [];
        arr.push(0);
        var $gs = $("#userGrid").datagrid("getSelected");
        var userId=$gs.dbid;
        var checkeds = $('#tt').datagrid('getChecked', 'checked');
        for (var i = 0; i < checkeds.length; i++) {
            arr.push(checkeds[i].dbid);
        }
        //alert(arr);
        //alert(userId)
        var data={
            "arr":arr,
            "userId":userId
        }
        url=path+'/user_role/addRole'
        $.ajax({
            url:url,
            data:data,
            method:"post",
            traditional:true,
            dataType:"json",
            success:function (result) {
                if(result.success){
                    $.messager.alert("提示", "操作成功！")
                }else{
                    $.messager.alert("提示", "角色权限以清空！")
                }
                closeSq();
            }
        })
    })
})
//删除
$(function () {
    $("#user_del").click(function () {
        var $gs = $("#userGrid").datagrid("getSelected");
        if ($gs.dbid == 12) {
            $.messager.alert('提示', '<span style="color: red">'+$gs.userName+'</span>用户不可删除!');
            return;
        }
        var data = {};
        data.dbid = $gs.dbid;
        var url = path + "/user/delete";
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


function initSelectDeptWin (){
    $("#selectDeptWin").window({
        modal: true,
        closed: true,
        iconCls: 'icon-save',
        title: "部门选择窗口",
        height: 350,
        width: 430,
        collapsible: false,
        minimizable: false,
        maximizable: false,
        onBeforeClose:function () {
            $("#queryDeptName").textbox("setValue","");
        }
    })
}

function showSelectDeptWin(){
    queryDeptName();
    $("#selectDeptWin").window("open");
}

function closeSelectDeptWin() {
    $("#selectDeptWin").window("close");
}

function initDeptGrid(){
    $("#deptGrid").datagrid({
        rownumbers: true,
        singleSelect: true,
        fitColumns: "true",
        height: 260,
        width:400,
        autoRowHeight: false,
        //是否分页
        pagination: true,
        //每页多少条
        pageSize: 10,
        pageList: [3, 5, 10, 20],
        columns: [[
            {field: 'deptName', title: '部门名称', width: 100}
        ]],
        onDblClickRow:function(rowIndex,rowData){
            $("#userDept").textbox("setValue",rowData.deptName)
            closeSelectDeptWin();
        }
    })
}

function queryDeptName(){
    var deptName = $("#queryDeptName").textbox("getValue");
    var data = {
        deptName: deptName
    };
    $("#deptGrid").datagrid("options").url = path+"/staffAction/queryDept";
    $("#deptGrid").datagrid("load", data);
}