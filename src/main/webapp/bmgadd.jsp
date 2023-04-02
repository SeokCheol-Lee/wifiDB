<%--
  Created by IntelliJ IDEA.
  User: Vamlin
  Date: 2023-04-02
  Time: 오후 9:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>북마크 그룹</title>
  <style type="text/css">
    table{
      border: 2px solid; border-collapse: collapse; width: 100%;}

    th,td{border: 1px solid; padding: 15px;}

    th{background-color: #009901; color:#ffffff; text-align: center}

  </style>
</head>
<body>
<h1>북마크 그룹 추가</h1><br>
<a href="index.jsp">홈</a> | <a href="HistoryServlet">위치 히스토리 목록</a>  |
<a href="GetInfoServlet">Open API 와이파이 정보 가져오기</a> |
<a href="bookmark.jsp">북마크 보기</a> | <a href="bmg.jsp">북마크 그룹 관리</a>

<form action="AddBmgServlet">
  <table>
    <tr>
      <th>북마크 이름</th>
      <td>
        <input type="text" name="bmgNm" size="20">
      </td>
    </tr>
    <tr>
      <th>순서</th>
      <td>
        <input type="text" name="ord" size="20">
      </td>
    </tr>
    <tr>
      <td colspan="2" style="text-align: center">
        <button type="submit" name="bmgadd">추가</button>
      </td>
    </tr>
  </table>
</form>

</body>
</html>
