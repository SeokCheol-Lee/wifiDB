<%@ page import="com.example.wifidb.model.BookMarkGroup" %>
<%@ page import="com.example.wifidb.DB.DataService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.wifidb.model.BookMark" %><%--
  Created by IntelliJ IDEA.
  User: Vamlin
  Date: 2023-04-02
  Time: 오후 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>뷱마크 목록</title>
    <style type="text/css">
        table{
            border: 2px solid; border-collapse: collapse; width: 100%;}

        th,td{border: 1px solid; padding: 15px;}

        th{background-color: #009901; color:#ffffff; text-align: center}

    </style>
</head>
<body>
<%
    DataService dataService = new DataService();
    List<BookMark> bookMarkList = dataService.SearchBookMark();
%>

<h1>북마크 목록</h1><br>
<a href="index.jsp">홈</a> | <a href="HistoryServlet">위치 히스토리 목록</a>  |
<a href="GetInfoServlet">Open API 와이파이 정보 가져오기</a> |
<a href="bookmark.jsp">북마크 보기</a> | <a href="bmg.jsp">북마크 그룹 관리</a>
<table>
<thead>
<tr>
    <th>ID</th>
    <th>북마크 이름</th>
    <th>와이파이명</th>
    <th>등록일자</th>
    <th>비고</th>
</tr>
</thead>
<tbody>
<tr>
    <%
        for(BookMark bmg : bookMarkList){
    %>
    <td> <%=bmg.getId()%></td>
    <td> <%=bmg.getBmgNm()%></td>
    <td> <%=bmg.getWifiNm()%></td>
    <td> <%=bmg.getRdate()%></td>
    <td style="text-align: center"><a href="delBM.jsp?bgmNm=<%=bmg.getBmgNm()%>&wifiNm=<%=bmg.getWifiNm()%>&rdate=<%=bmg.getRdate()%>
&bmgId=<%=bmg.getId()%>">삭제</a></td>
    <%
        }
    %>
</tr>
</tbody>
</table>
</body>
</html>
