/**
 * Created by Administrator on 2017/2/21.
 */
//登录数据连接
$(function () {
    var $che=$.cookie("rem1");

    if($che){
        var $name=$.cookie("username");
        var $psw=$.cookie("password");

        $("input:eq(0)").val($name);
        $("input:eq(1)").val($psw);
        $("#remember_me").attr("checked",true);
    }
})

$(function(){
    $("#sub1").click(function(){
        var $re=$("#remember_me").is(":checked");

        var $name=$("input:eq(0)").val();
        var $psw=$("input:eq(1)").val();

        if($name==""||$psw==""){
            alert("不能为空");
        }else{
            $.ajax({
                url:path+"/login/login",
                data:{"userName":$name,"password":$psw},
                dataType:"json",
                type : "post",
                success:function(data){
                    if (data.success) {
                        if($re){
                           $.cookie("username",$name,{expires:7});
                           $.cookie("password",$psw,{expires:7});
                           $.cookie("rem1",$re,{expires:7});
                        }else{
                            $.cookie("username","",{expires:-1});
                            $.cookie("password","",{expires:-1});
                            $.cookie("rem1","",{expires:-1});
                        }
                       location.href=path+"/login/index";
                    }else{

                        $.messager.alert("登录提示",data.reason1)
                    }
                }
            });
        }

    });

});
//注销登陆
$(function () {
    $("#logout").click(function () {

        window.location.replace(path+"/login/logout")
    })
})

