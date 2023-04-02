<%@ page import="com.example.wifidb.DB.DataService" %>
<%@ page import="com.example.wifidb.model.LocHty" %>
<%@ page import="com.example.wifidb.model.WifiInfo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;}
        .tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
            overflow:hidden;padding:10px 5px;word-break:normal;}
        .tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
            font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
        .tg .tg-dqvt{background-color:#009901;border-color:inherit;color:#ffffff;font-weight:bold;text-align:center;vertical-align:top}
        .tg .tg-c3ow{border-color:inherit;text-align:center;vertical-align:top}
    </style>


</head>
<body>
<h1>와이파이 정보 구하기</h1><br>
<a href="index.jsp">홈</a> | <a href="HistoryServlet">위치 히스토리 목록</a>  |
<a href="GetInfoServlet">Open API 와이파이 정보 가져오기</a> |
<a href="bookmark.jsp">북마크 보기</a> | <a href="bmg.jsp">북마크 그룹 관리</a>

<form action="HomeServlet">
    LAT</label><input type="text" id="LAT" name="LAT" value="0.0"> ,
    LNT</label><input type="text" id="LNT" name="LNT" value="0.0">
    <button type="button" id="my_location">내 위치 가져오기</button>
    <button type="submit" id="show_wifi_info">근처 WIPI정보 보기</button>
</form>
<script>
    document.getElementById("my_location").onclick = function () {
        navigator.geolocation.getCurrentPosition(function(pos) {
            console.log(pos);
            let latitude = pos.coords.latitude; //위도 y값
            let longitude = pos.coords.longitude; // 경도 x값
            document.getElementById('LAT').setAttribute('value',latitude);
            document.getElementById('LNT').setAttribute('value',longitude);
        });
    }
</script>

<table class="tg" id="table">
    <thead>
    <tr>
        <th class="tg-dqvt">거리(Km)</th>
        <th class="tg-dqvt">관리번호</th>
        <th class="tg-dqvt">자치구</th>
        <th class="tg-dqvt">와이파이명</th>
        <th class="tg-dqvt">도로명주소</th>
        <th class="tg-dqvt">상세주소</th>
        <th class="tg-dqvt">설치위치(층)</th>
        <th class="tg-dqvt">설치유형</th>
        <th class="tg-dqvt">설치기관</th>
        <th class="tg-dqvt">서비스구분</th>
        <th class="tg-dqvt">망종류</th>
        <th class="tg-dqvt">설치년도</th>
        <th class="tg-dqvt">실내외구분</th>
        <th class="tg-dqvt">WIFI접속환경</th>
        <th class="tg-dqvt">X좌표</th>
        <th class="tg-dqvt">Y좌표</th>
        <th class="tg-dqvt">작업일자</th>
    </tr>
    </thead>
    <tbody>
    <tr id="basic_col">
        <td class="tg-c3ow" colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
    </tr>
    </tbody>
</table>
</body>
</html>