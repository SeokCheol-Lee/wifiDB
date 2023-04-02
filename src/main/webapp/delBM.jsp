<%--
  Created by IntelliJ IDEA.
  User: Vamlin
  Date: 2023-04-03
  Time: 오전 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>북마크 삭제</title>
  <style type="text/css">
    table{
      border: 2px solid; border-collapse: collapse; width: 100%;}

    th,td{border: 1px solid; padding: 15px;}

    th{background-color: #009901; color:#ffffff; text-align: center}

  </style>
</head>
<body>
<h1>북마크 삭제</h1><br>
<a href="index.jsp">홈</a> | <a href="HistoryServlet">위치 히스토리 목록</a>  |
<a href="GetInfoServlet">Open API 와이파이 정보 가져오기</a> |
<a href="bookmark.jsp">북마크 보기</a> | <a href="bmg.jsp">북마크 그룹 관리</a>

<%
  String bgmNm = request.getParameter("bgmNm");
  String wifiNm = request.getParameter("wifiNm");
  String rdate = request.getParameter("rdate");
  String bgmId = request.getParameter("bmgId");
%>

<form action="DelBmServlet">
  <table>
    <tr>
      <th>북마크 이름</th>
      <td>
        <input type="hidden" name="bgmId" value=<%=bgmId%>>
        <%=bgmNm%>
      </td>
    </tr>
    <tr>
      <th>와이파이명</th>
      <td>
        <%=wifiNm%>
      </td>
    </tr>
    <tr>
      <th>등록일자</th>
      <td>
        <%=rdate%>
      </td>
    </tr>
    <tr>
      <td colspan="2" style="text-align: center">
        <a href="bookmark.jsp">돌아가기</a> |
        <button type="submit" name="bmgadd">삭제</button>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
