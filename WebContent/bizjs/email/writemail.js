/**
 * Created by Administrator on 2017/3/27.
 */

$(function () {
    initReceiver();
    initSender();
    initDetail();
});

function initReceiver(){
    $("#receiver").combobox({
        url:path+"/jump/getReceiver",
        panelHeight:100,
        editable:false,
        valueField:"dbid",
        textField:"userName"
    });
}

function initDetail() {
    $("#detail").textbox({
        multiline:true
    });
}

function initSender(){
    $("#sender").combobox({
        url:path+"/jump/getSender",
        panelHeight:100,
        editable:false,
        valueField:"userName",
        textField:"userName"
    });
}

function send() {
    var receiverId = $("#receiver").combobox('getValue');
    var theme = $("#theme").val();
    var detail = $("#detail").val();
    var senderId = $("#sender").combobox('getValue');
    var time =new Date().Format("YYYY-MM-dd HH:mm:SS");

    if (receiverId=="请选择收件人"||receiverId==""){
        $.messager.alert("提示","收件人不能为空!");
        return;
    }
    if (senderId=="请选择收件人"||senderId==""){
        $.messager.alert("提示","发件人不能为空!");
        return;
    }
    if (theme==""||theme==null){
        $.messager.alert("提示","主题不能为空!");
        return;
    }
    if (detail==""||detail==null){
        $.messager.alert("提示","内容不能为空!");
        return;
    }
    /*$.messager.alert("receiver",receiver);
    $.messager.alert("sender",sender);
    $.messager.alert("theme",theme);
    $.messager.alert("content",content);*/
    var data={};
    data.receiveId=receiverId;
    data.theme=theme;
    data.detail=detail;
    data.senderId=senderId;
    data.time=time;

    var url =path+"/jump/sendEmail";
    $.ajax({
        data:data,
        dataType:"json",
        url:url,
        method:"post",
        traditional:true,
        success:function (response) {
            if (response.success){
                $.messager.alert("提示","邮件已成功发送!");
            }else{
                $.messager.alert("提示","网络错误,请联系管理员!");
            }
            resetEmail();
        }
    });
}


function resetEmail() {
    $("#receiver").combobox("setValue","请选择收件人");
    $("#sender").combobox("setValue",userNameCenter);
    $("#theme").textbox("setValue","");
    $("#detail").textbox("setValue","");
}

Date.prototype.Format = function(formatStr)
{
    var str = formatStr;

    str=str.replace(/yyyy|YYYY/,this.getFullYear());
    str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));

    str=str.replace(/MM/,this.getMonth()>9?this.getMonth().toString():'0' + this.getMonth());
    str=str.replace(/M/g,this.getMonth());

    str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());
    str=str.replace(/d|D/g,this.getDate());

    str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());
    str=str.replace(/h|H/g,this.getHours());
    str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());
    str=str.replace(/m/g,this.getMinutes());

    str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());
    str=str.replace(/s|S/g,this.getSeconds());

    return str;
};

function save() {
    var receiverId = $("#receiver").combobox('getValue');
    var theme = $("#theme").val();
    var detail = $("#detail").val();
    var senderId = $("#sender").combobox('getValue');
    var time =new Date().Format("YYYY-MM-dd HH:mm:SS");

    if (receiverId=="请选择收件人"||receiverId==""){
        $.messager.alert("提示","收件人不能为空!");
        return;
    }
    if (senderId=="请选择收件人"||senderId==""){
        $.messager.alert("提示","发件人不能为空!");
        return;
    }
    if (theme==""||theme==null){
        $.messager.alert("提示","主题不能为空!");
        return;
    }
    if (detail==""||detail==null){
        $.messager.alert("提示","内容不能为空!");
        return;
    }
    var data={};
    data.receiveId=receiverId;
    data.theme=theme;
    data.detail=detail;
    data.senderId=senderId;
    data.time=time;

    var url =path+"/jump/saveEmail";
    $.ajax({
        data:data,
        dataType:"json",
        url:url,
        method:"post",
        traditional:true,
        success:function (response) {
            if (response.success){
                $.messager.alert("提示","邮件已成功保存!");
            }else{
                $.messager.alert("提示","网络错误,请联系管理员!");
            }
            resetEmail();
        }
    });
}
