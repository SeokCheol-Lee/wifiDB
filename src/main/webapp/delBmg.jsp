<%@ page import="com.example.wifidb.DB.DataService" %><%--
  Created by IntelliJ IDEA.
  User: Vamlin
  Date: 2023-04-02
  Time: 오후 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<%
  int bmgId = Integer.parseInt(request.getParameter("bgmId"));
  DataService dataService = new DataService();
  dataService.DelteBmg(bmgId);
%>
<script type="text/javascript">
  alert("북마크 그룹을 삭제하였습니다.");
  location.href="bmg.jsp";
</script>
</body>
</html>
