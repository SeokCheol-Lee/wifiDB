<%@ page import="com.example.wifidb.DB.DataService" %>
<%@ page import="com.example.wifidb.model.WifiInfo" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Vamlin
  Date: 2023-04-02
  Time: 오후 3:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style type="text/css">
        table{
            border: 2px solid; border-collapse: collapse; width: 100%;}

        th,td{border: 1px solid; padding: 15px;}

        th{background-color: #009901; color:#ffffff; text-align: center}

    </style>
</head>
<body>
<h1>와이파이 정보 구하기</h1><br>
<%
    DataService dataService = new DataService();
    List<String> bmgNmList = dataService.BmgNm();
    String wifino = request.getParameter("wifino");
    String distance = request.getParameter("distance");
    WifiInfo wifiInfo = dataService.getWifiDetail(wifino);
%>
<a href="index.jsp">홈</a> | <a href="HistoryServlet">위치 히스토리 목록</a>  |
<a href="GetInfoServlet">Open API 와이파이 정보 가져오기</a> |
<a href="bookmark.jsp">북마크 보기</a> | <a href="bmg.jsp">북마크 그룹 관리</a>

<form action="AddBMServlet">
    <select name="bmg" id="bmg" style="width: 150px; height: 30px;">
        <option value="" disabled selected>북마크 그룹 이름 선택</option>
        <%
            for(String bmg : bmgNmList) {
        %>
        <option value=<%=bmg%>><%=bmg%></option>
        <%
            }
        %>
    </select>
    <input type="hidden" name="wifinm" value="<%=wifiInfo.getWifinm()%>">
    <button type="submit">북마크 추가하기</button>
</form>
<table class="tg" id="table">
    <colgroup>
        <col style="width: 20%;"/>
        <col style="width: 80%;"/>
    </colgroup>
    <tbody>
    <tr>
        <th>거리(Km)</th>
        <td>
            <%=distance %>
        </td>
    </tr>
    <tr>
        <th>관리번호</th>
        <td>
            <%=wifiInfo.getWifino() %>
        </td>
    </tr>
    <tr>
        <th>자치구</th>
        <td>
            <%=wifiInfo.getGu() %>
        </td>
    </tr>
    <tr>
        <th>와이파이명</th>
        <td>
            <%=wifiInfo.getWifinm() %>
        </td>
    </tr>
    <tr>
        <th>도로명주소</th>
        <td>
            <%=wifiInfo.getAdres1() %>
        </td>
    </tr>
    <tr>
        <th>상세주소</th>
        <td>
            <%=wifiInfo.getAdres2() %>
        </td>
    </tr>
    <tr>
        <th>설치위치</th>
        <td>
            <%=wifiInfo.getFloor() %>
        </td>
    </tr>
    <tr>
        <th>설치유형</th>
        <td>
            <%=wifiInfo.getInstl_ty() %>
        </td>
    </tr>
    <tr>
        <th>설치기관</th>
        <td>
            <%=wifiInfo.getInstl_mby() %>
        </td>
    </tr>
    <tr>
        <th>서비스구분</th>
        <td>
            <%=wifiInfo.getSvc_se() %>
        </td>
    </tr>
    <tr>
        <th>망종류</th>
        <td>
            <%=wifiInfo.getWifi_cmcwr() %>
        </td>
    </tr><tr>
        <th>설치년도</th>
        <td>
            <%=wifiInfo.getInstl_year() %>
        </td>
    </tr>

    <tr>
        <th>실내외구분</th>
        <td>
            <%=wifiInfo.getInout_door() %>
        </td>
    </tr>
    <tr>
        <th>WIFI접속환경</th>
        <td>
            <%=wifiInfo.getWifi_remars() %>
        </td>
    </tr>
    <tr>
        <th>X좌표</th>
        <td>
            <%=wifiInfo.getX() %>
        </td>
    </tr>
    <tr>
        <th>Y좌표</th>
        <td>
            <%=wifiInfo.getY() %>
        </td>
    </tr>
    <tr>
        <th>작업일자</th>
        <td>
            <%=wifiInfo.getWork_date() %>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>
