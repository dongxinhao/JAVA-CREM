/**
 * Created by Administrator on 2017/3/2.
 */
/*$(function () {
    var treeData = [
        {
            text: "用户管理",
            url: "/user/toMain"

        },
        {
            text: "角色管理",
            url: "/role/toMain"

        },
        {
            text: "权限管理",
            url: "/auth/toMain"

        }
    ]
    $("#xt").tree({

        data: treeData,
        onClick: function (node) {
            addTab(node.text, node.url);
        }
    })
})*/

function addTab(title, url) {
    if(url==""){
        return;
    }
    var $mT=$("#mainTab").tabs("exists",title);
    if($mT){
        $("#mainTab").tabs("select",title);
    }else{
        $("#mainTab").tabs('add', {
            title: title,
            content: '<iframe style="height:100%;width:100%; " frameborder="0" scrolling="no" src="' + path + url + '"/>',
            closable: true
        })
    }

}
//变换颜色

