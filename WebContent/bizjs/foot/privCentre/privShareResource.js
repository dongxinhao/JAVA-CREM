/**
 * Created by Administrator on 2017/3/11.
 */
/**
 * Created by Administrator on 2017/3/9.
 */
/**
 * Created by Administrator on 2017/3/6.
 */
$(function () {
    initFootImgGrid();
    queryFootImg();
})

function initFootImgGrid() {
    $("#privImgGrid").datagrid({
        singleSelect: true,
        fitColumns: "true",
        fit: true,
        autoRowHeight: true,
        //是否分页
        pagination: true,
        onDblClickRow: true,
        nowrap: false,

        onRowContextMenu: onRowContextMenu,
        //每页多少条
        pageSize: 10,

        pageList: [3, 5, 10, 20],
        columns: [[
            {
                field: 'imgURL', title: '图片文件', width: 100, align: 'center',
                formatter: function (value, row, index) {

                    return "<embed  loop='2'  style=' ;width: 100px;' src='" + path + "/upload/" + value + "'  ></embed>";
                }
            },
            {field: 'imgName', title: '图片名称', width: 100, align: 'center'},
            {field: 'userName', title: '分享目标', width: 100, align: 'center'},
            {
                field: 'dbid', title: '下载', width: 100, align: 'center',
                formatter: function (value, row, index) {
                    return "<a href=" + path + "/privShare/imgDown?dbid=" + value + ">下载</a>";
                }
            }
        ]],
        onDblClickRow: function (rowIndex, rowData) {
            openImg(rowIndex, rowData)
        }
    });
}
//搜索
function queryFootImg() {
    var shareUser = userNameCenter;
    var imgName = $("#queryImgName").textbox("getValue");
    var userName = $("#queryType").textbox("getValue");
    var data = {
        shareUser: shareUser,
        imgName: imgName,
        userName: userName
    };
    //console.log(data)
    $("#privImgGrid").datagrid("options").url = path + "/privShare/queryFootImg";
    $("#privImgGrid").datagrid("load", data);
}


//刷新
$(function () {
    $("#refresh").click(function () {
        window.location.reload();
        initFootImgGrid();
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


//关闭窗口

function closeEd() {
    $("#dbid").text("");
    $("#imgName").textbox("setValue", "");
    $("#type").textbox("setValue", "");
    $("#orders").numberbox("setValue", "");
    $("#edCPM").window('close');
}


//图片窗
function openImg(rowIndex, rowData) {
    $("#imgCPM").window({
        modal: true,
        closed: true,
        title: "图片窗口",
        iconCls: 'icon-save',
        fit: true,
        collapsible: false,
        minimizable: false,
        maximizable: false,
        onBeforeClose: function () {

        }
    })
    $("#img1").attr("src", path + "/upload/" + rowData.imgURL)
    $("#imgCPM").window('open');
}
//删除
$(function () {
    $("#img_del").click(function () {

        $.messager.confirm("删除", "是否删除", function (r) {

            if (!r) {
                return;
            }
            var $gs = $("#privImgGrid").datagrid("getSelected");
            var dbid = $gs.dbid;
            var data = {
                dbid: dbid
            }
            //console.log(data)
            var url = path + "/privShare/deleteShareImg"
            $.ajax({
                data: data,
                url: url,
                dataType: "json",
                method: "post",
                success: function (result) {
                    if (result.success) {
                        $.messager.alert("提示", "删除成功")
                    } else {
                        $.messager.alert("提示", "网络异常")
                    }
                    initFootImgGrid();
                }
            })
        })
    })
})
//撤回
$(function () {
    $("#img_back").click(function () {

        $.messager.confirm("撤回", "是否撤回", function (r) {

            if (!r) {
                return;
            }
            var $gs = $("#privImgGrid").datagrid("getSelected");
            var dbid = $gs.dbid;
            var data = {
                dbid: dbid
            }
            //console.log(data)
            var url = path + "/privShare/backShareImg"
            $.ajax({
                data: data,
                url: url,
                dataType: "json",
                method: "post",
                success: function (result) {
                    if (result.success) {
                        $.messager.alert("提示", "成功撤回")
                    } else {
                        $.messager.alert("提示", "网络异常")
                    }
                    initFootImgGrid();
                }
            })
        })
    })
})


