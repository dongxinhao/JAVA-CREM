/**
 * Created by Administrator on 2017/3/2.
 */

$(function(){
    valid(["auth_add","auth_del","auth_update","auth_move"]);
    initAuth();

    if(hasRes("auth_query")){
        queryAuth();
    }

})


function initAuth(){
    $('#authTree').treegrid({
        method: 'get',
        rownumbers: true,
        fit: true,
        fitColumns: true,
        idField: 'dbid',
        treeField: 'authName',
        onContextMenu: onContextMenu,
        columns: [[
            {field: 'authName', title: '权限名称', width: 60},
            {field: 'authCode', title: '权限编码', width: 60},
            {field: 'authURL', title: 'URL', width: 60},
            {
                field: 'type', title: '类型', width: 60,
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "模块";
                    } else {
                        return "<span style='color: red'>资源</span>";
                    }
                }


            },
            {field: 'orders', title: '排序', width: 60},
            {
                field: 'valid', title: '是否有效', width: 60,
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "显示";
                    } else {
                        return "<span style='color: red'>隐藏</span>";
                    }
                }
            },
            {field: 'layer', title: '层级', width: 60},

        ]]
    });

    /*$('#moveCombobox').combotree({
        url:path + '/static/temp/treegrid_data1.json',
        method:'get'
    });*/

}
//加载
function queryAuth() {
    $("#authTree").treegrid("options").url= path + '/auth/getAllAuth';
    $('#authTree').treegrid("load");

}
function onContextMenu(e, row) {
    if (row) {
        e.preventDefault();
        $(this).treegrid('select', row.dbid);
        $('#m1').menu('show', {
            left: e.pageX,
            top: e.pageY
        });
    }
}
//打开添加窗口
$(function () {
    $("#auth_add").click(function () {
        $("#addCPM").window({
            modal: true,
            closed: true,
            closable: false,
            title: "权限增加窗口",
            iconCls: 'icon-save',
            height: 400,
            width: 350,
            collapsible: false,
            minimizable: false,
            maximizable: false,
            footer: '#footer',
            onBeforeClose: function () {
                $("#auth1").empty();
                $("#layer").empty();
                $("#authName").textbox("setValue", "");
                $("#authCode").textbox("setValue", "");
                $("#authURL").textbox("setValue", "");
                $("#orders").numberbox("setValue", "");
                $("#type").combobox("setValue", "1");
                $("#valid").combobox("setValue", "1");
                $(".emptyS").hide();
                $("#addDbid").empty();
                $("#ParentId").empty();
            }
        })
        $('#addCPM').window('open');
        var $gs = $("#authTree").treegrid("getSelected");
        $("#auth1").append($gs.authName);
        $("#layer").append($gs.layer + 1);
        $("#ParentId").append($gs.dbid);
    })


})

//关闭添加窗口
$(function () {
    $("#close1").click(function () {
        $("#addCPM").window('close');
    })
})
//打开更改窗口
$(function () {
    $("#auth_update").click(function () {
        $("#addCPM").window({
            modal: true,
            closed: true,
            closable: false,
            title: "权限更改窗口",
            iconCls: 'icon-save',
            height: 400,
            width: 350,
            collapsible: false,
            minimizable: false,
            maximizable: false,
            footer: '#footer',
            onBeforeClose: function () {
                $("#auth1").empty();
                $("#layer").empty();
                $("#authName").textbox("setValue", "");
                $("#authCode").textbox("setValue", "");
                $("#authURL").textbox("setValue", "");
                $("#orders").numberbox("setValue", "");
                $("#type").combobox("setValue", "1");
                $("#valid").combobox("setValue", "1");
                $(".emptyS").hide();
                $("#authParent").show();
                $("#authSelf").text("添加层级");
                $("#addDbid").empty();
                $("#ParentId").empty();
                $("#authUpdate").empty();
                $("#authUpdate").hide();

                $("#layer").show();
            }
        })

        var $gs = $("#authTree").treegrid("getSelected");
        $("#authParent").hide();
        $("#authSelf").text("更改模块");
        $("#layer").append($gs.layer);
        $("#layer").hide();
        $("#authUpdate").append($gs.authName);
        $("#authUpdate").show();

        $("#authName").textbox("setValue", $gs.authName);
        $("#authCode").textbox("setValue", $gs.authCode);
        $("#authURL").textbox("setValue", $gs.authURL);
        $("#orders").textbox("setValue", $gs.orders);
        $("#type").combobox("setValue", $gs.type);
        $("#valid").combobox("setValue", $gs.valid);
        $("#addDbid").text($gs.dbid);
        $('#addCPM').window('open');
    })


})

//刷新
$(function () {
    $("#authSX").click(function () {
        $('#authTree').treegrid("load");
    })
})

//保存
$(function () {
    //保存按钮
    $("#addSubmit").click(function () {
        var authName = $("#authName").textbox("getValue");
        var authCode = $("#authCode").textbox("getValue");
        var authURL = $("#authURL").textbox("getValue");
        var orders = $("#orders").numberbox("getValue");
        var type = $("#type").combobox("getValue");
        var valid = $("#valid").combobox("getValue");
        var layer = $("#layer").text();
        var parentId = $("#ParentId").text();
        var dbid = $("#addDbid").text();
        var data = {};
        data.dbid = dbid;
        data.authName = authName;
        data.authCode = authCode;
        data.authURL = authURL;
        data.orders = orders;
        data.type = type;
        data.valid = valid;
        data.layer = layer;
        data.parentId = parentId;
        var url = path + "/auth/saveOrUpdate"
        //console.log(data)
        if (authName == null || authName == "") {
            $(".emptyS").show();
        } else {
            $.ajax({
                url: url,
                data: data,
                method: "post",
                dataType: "json",
                success: function (result) {
                    if (result.success) {
                        $.messager.alert('提示', '提交成功!');
                    } else {
                        $.messager.alert('提示', '网络异常!');
                    }
                    $("#addCPM").window('close');
                    $('#authTree').treegrid("load");
                }
            })
        }
    })


})
//删除
$(function () {
    $("#auth_del").click(function () {
        var $gs = $("#authTree").treegrid("getSelected");
        if($gs.layer!=0){
            var data={};
            data.dbid=$gs.dbid;

            var url=path + "/auth/delete";
            $.messager.confirm("删除", "是否删除", function (r) {
                if(r){
                    $.ajax({
                        url:url,
                        data:data,
                        method:"post",
                        dataType:"json",
                        success:function (result) {
                            if(result.success){
                                $.messager.alert('提示', '删除成功!');
                            }else{
                                $.messager.alert('提示', '请先删除其子层!');
                            }
                            $('#authTree').treegrid("load");
                        }
                    })
                }

            })
        }else{
            $.messager.alert('提示', '最高权限不可删除!');

        }

    })
})
//移动-------------------------------------
function moveAuth() {
    var $gs = $("#authTree").treegrid("getSelected");

    var dbid=$gs.dbid;
    var parentId=$gs.parentId;
    var layer=$gs.layer;
    $('#moveCombobox').combobox({
        url:path + '/auth/selectAllAuthByLayer?dbid='+dbid+'&parentId='+parentId+'&layer='+layer,
        method:'get',
        valueField:'dbid',
        panelHeight:100,
        groupField:'group',
        textField:'authName',
        labelPosition: 'top',

    });

}
$(function () {
    $("#auth_move").click(function () {

        $("#moveCPM").window({
            modal: true,
            closed: true,
            closable: false,
            title: "权限移动窗口",
            iconCls: 'icon-save',
            height: 200,
            width: 300,
            collapsible: false,
            minimizable: false,
            maximizable: false,
            footer: '#footer1',
            onBeforeClose: function () {
                $(".emptyS").hide();
            }
        })
        var $gs = $("#authTree").treegrid("getSelected");
        var children = $("#authTree").treegrid("getChildren",$gs.dbid);
        if($gs.layer!=0){
            if(children.length==0){
                moveAuth();
                $('#moveCPM').window('open');
            }else{
                $.messager.alert('提示', '不可移动含有<span style="color: red">子节点</span>的管理类型!');
            }
        }else {
            $.messager.alert('提示', '最高权限不可移动!');
        }
    })
})
//移动权限提交
$(function () {
    $("#moveSubmit").click(function () {
        var $gs = $("#authTree").treegrid("getSelected");
        var parentId=$("#moveCombobox").combobox('getValue');
        var dbid=$gs.dbid
        if(parentId==""){
            $(".emptyS").show();
        }else{
            var data={
                parentId:parentId,
                dbid:dbid
            }
            var url=path+'/auth/moveAuth';
            $.ajax({
                data:data,
                url:url,
                dataType:"json",
                method:"post",
                success:function (result) {
                    $("#moveCPM").window('close');
                    if(result.success){
                        $.messager.alert('提示', '操作成功!');
                        $('#authTree').treegrid("load");
                    }else{
                        $.messager.alert('提示', '网络异常!');
                    }
                }
            })
        }

    })
})
//关闭移动窗口
$(function () {
    $("#close2").click(function () {
        $("#moveCPM").window('close');
    })
})