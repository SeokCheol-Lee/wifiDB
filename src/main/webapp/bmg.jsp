<%@ page import="com.example.wifidb.DB.DataService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.wifidb.model.BookMarkGroup" %><%--
  Created by IntelliJ IDEA.
  User: Vamlin
  Date: 2023-04-02
  Time: 오후 8:56
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

    td{text-align: center}

    th{background-color: #009901; color:#ffffff; text-align: center}

  </style>
</head>
<body>
<%
  DataService dataService = new DataService();

  List<BookMarkGroup> bmgList = dataService.SearchBmg();
%>

<h1>북마크 그룹</h1><br>
<a href="index.jsp">홈</a> | <a href="HistoryServlet">위치 히스토리 목록</a>  |
<a href="GetInfoServlet">Open API 와이파이 정보 가져오기</a> |
<a href="bookmark.jsp">북마크 보기</a> | <a href="bmg.jsp">북마크 그룹 관리</a>

<form action="bmgadd.jsp">
  <button type="submit">북마크 그룹 이름 추가</button>
</form>

<table>
<thead>
<tr>
  <th>ID</th>
  <th>북마크 이름</th>
  <th>순서</th>
  <th>등록일자</th>
  <th>수정일자</th>
  <th>비고</th>
</tr>
</thead>
<tbody>
  <%
    for(BookMarkGroup bmg : bmgList){
  %>
  <tr>
  <td> <%=bmg.getId()%></td>
  <td> <%=bmg.getBMGNmae()%></td>
  <td> <%=bmg.getOrd()%></td>
  <td><%=bmg.getRdate()%></td>
  <td> <%=bmg.getMdate()%></td>
  <td style="text-align: center"><a href="bmgmodi.jsp?bgmId=<%=bmg.getId()%>">수정</a> |
    <a href="delBmg.jsp?bgmId=<%=bmg.getId()%>">삭제</a></td>
  </tr>
  <%
    }
  %>
</tbody>
</table>

</body>
</html>
