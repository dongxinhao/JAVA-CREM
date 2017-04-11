
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%
    String path = request.getContextPath();
    List<String> res=(List)session.getAttribute("resource");

%>



<link rel="stylesheet" type="text/css" href="<%=path%>/static/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/static/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/static/css/admin.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/static/css/buttons.css">
<script type="text/javascript" src="<%=path%>/static/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/jquery.cookie.js" ></script>
<script type="text/javascript">
    var userNameCenter="${sessionScope.username}"
    var path="<%=path%>";
    var ress=[];
    <%

       if(res!=null&&res.size()>0){
           for(String re:res){
    %>
        ress.push("<%=re%>")

    <%
          }
       }
    %>
    function hasRes(code) {
        if(ress.length>0){
            for(var i=0;i<ress.length;i++){
                if(code==ress[i]){
                    return true;
                    break;
                }
            }
        }
        return false;
    }

    function valid(arr) {
        if(arr!=null&&arr.length>0){
            for(var i=0;i<arr.length;i++){
                if(!hasRes(arr[i])){
                   $("#"+arr[i]).hide();
                }
            }
        }
    }
</script>
