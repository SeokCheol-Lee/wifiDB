package com.example.wifidb;

import com.example.wifidb.DB.DataService;
import com.example.wifidb.model.LocHty;
import com.example.wifidb.model.WifiInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Float x = Float.valueOf(request.getParameter("LAT"));
        Float y = Float.valueOf(request.getParameter("LNT"));
        List<WifiInfo> wifiInfoList = new ArrayList<>();
        DataService dataService = new DataService();
        LocHty locHty = new LocHty();
        locHty.setX(x);
        locHty.setY(y);
        dataService.historyInsert(locHty);
        wifiInfoList = dataService.dbSearch(x,y);

        out.println("<html><head>\n" +
                "    <title>와이파이 정보 구하기</title>\n" +
                "    <style type=\"text/css\">\n" +
                "        .tg  {border-collapse:collapse;border-spacing:0;}\n" +
                "        .tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;\n" +
                "            overflow:hidden;padding:10px 5px;word-break:normal;}\n" +
                "        .tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;\n" +
                "            font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}\n" +
                "        .tg .tg-dqvt{background-color:#009901;border-color:inherit;color:#ffffff;font-weight:bold;text-align:center;vertical-align:top}\n" +
                "        .tg .tg-c3ow{border-color:inherit;text-align:center;vertical-align:top}\n" +
                "    </style>\n" +
                "\n" +
                "</head><body>");
        out.println("<h1>와이파이 정보 구하기</h1>\n" +
                "<a href=\"index.jsp\">홈</a> | <a href=\"HistoryServlet\">위치 히스토리 목록</a>  | <a href=\"GetInfoServlet\">Open API 와이파이 정보 가져오기</a>  <br>\n" +
                "\n" +
                "<form action=\"HomeServlet\">\n" +
                "    LAT</label><input type=\"text\" id=\"LAT\" name=\"LAT\" value=\""+ x + "\"> ,\n" +
                "    LNT</label><input type=\"text\" id=\"LNT\" name=\"LNT\" value=\""+ y +"\">\n" +
                "    <button type=\"button\" id=\"my_location\">내 위치 가져오기</button>\n" +
                "    <button onclick=\"loadData()\" type=\"submit\" id=\"show_wifi_info\">근처 WIPI정보 보기</button>\n" +
                "</form>");
        out.println("<table class=\"tg\" id=\"table\">\n" +
                "    <thead>\n" +
                "    <tr>\n" +
                "        <th class=\"tg-dqvt\">거리(Km)</th>\n" +
                "        <th class=\"tg-dqvt\">관리번호</th>\n" +
                "        <th class=\"tg-dqvt\">자치구</th>\n" +
                "        <th class=\"tg-dqvt\">와이파이명</th>\n" +
                "        <th class=\"tg-dqvt\">도로명주소</th>\n" +
                "        <th class=\"tg-dqvt\">상세주소</th>\n" +
                "        <th class=\"tg-dqvt\">설치위치(층)</th>\n" +
                "        <th class=\"tg-dqvt\">설치유형</th>\n" +
                "        <th class=\"tg-dqvt\">설치기관</th>\n" +
                "        <th class=\"tg-dqvt\">서비스구분</th>\n" +
                "        <th class=\"tg-dqvt\">망종류</th>\n" +
                "        <th class=\"tg-dqvt\">설치년도</th>\n" +
                "        <th class=\"tg-dqvt\">실내외구분</th>\n" +
                "        <th class=\"tg-dqvt\">WIFI접속환경</th>\n" +
                "        <th class=\"tg-dqvt\">X좌표</th>\n" +
                "        <th class=\"tg-dqvt\">Y좌표</th>\n" +
                "        <th class=\"tg-dqvt\">작업일자</th>\n" +
                "    </tr>\n" +
                "    </thead>\n");
        out.println("<tbody>");
        for(WifiInfo wifiInfo : wifiInfoList){
            out.println("<tr>");
            out.println("<td>" + wifiInfo.getDistance() + "</td>");
            out.println("<td>" + wifiInfo.getWifino() + "</td>");
            out.println("<td>" + wifiInfo.getGu() + "</td>");
            out.println("<td>" + wifiInfo.getWifinm() + "</td>");
            out.println("<td>" + wifiInfo.getAdres1() + "</td>");
            out.println("<td>" + wifiInfo.getAdres2() + "</td>");
            out.println("<td>" + wifiInfo.getFloor() + "</td>");
            out.println("<td>" + wifiInfo.getInstl_ty() + "</td>");
            out.println("<td>" + wifiInfo.getInstl_mby() + "</td>");
            out.println("<td>" + wifiInfo.getSvc_se() + "</td>");
            out.println("<td>" + wifiInfo.getWifi_cmcwr() + "</td>");
            out.println("<td>" + wifiInfo.getInstl_year() + "</td>");
            out.println("<td>" + wifiInfo.getInout_door() + "</td>");
            out.println("<td>" + wifiInfo.getWifi_remars() + "</td>");
            out.println("<td>" + wifiInfo.getX() + "</td>");
            out.println("<td>" + wifiInfo.getY() + "</td>");
            out.println("<td>" + wifiInfo.getWork_date() + "</td>");
            out.println("</tr>");
        }
        out.println("</tbody>");
        out.println("</table>");
        out.println("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
