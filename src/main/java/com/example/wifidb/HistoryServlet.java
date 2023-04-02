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

@WebServlet(name = "HistoryServlet", value = "/HistoryServlet")
public class HistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        List<LocHty> locHtyList = new ArrayList<>();
        DataService dataService = new DataService();
        locHtyList = dataService.historySearch();

        out.println("<html><head>\n" +
                "    <title>와이파이 정보 구하기</title>\n" +
                "    <style type=\"text/css\">\n" +
                "        .tg  {border-collapse:collapse;border-spacing:0;}\n" +
                "        .tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;\n" +
                "            overflow:hidden;padding:11px 20px;word-break:normal;}\n" +
                "        .tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;\n" +
                "            font-weight:normal;overflow:hidden;padding:11px 20px;word-break:normal;}\n" +
                "        .tg .tg-dqvt{background-color:#009901;border-color:inherit;color:#ffffff;font-weight:bold;text-align:center;vertical-align:top}\n" +
                "        .tg .tg-0pky{border-color:inherit;text-align:left;vertical-align:top}\n" +
                "table {\n" +
                        "            width: 100%;\n" +
                        "        }"+
                "td {\n" +
                        "        text-align: center;\n" +
                        "      }" +
                "    </style>\n" +
                "\n" +
                "</head><body>");
        out.println("<h1>위치 히스토리 목록</h1>\n" +
                "<a href=\"index.jsp\">홈</a> | <a href=\"HistoryServlet\">위치 히스토리 목록</a>  |\n" +
                "<a href=\"GetInfoServlet\">Open API 와이파이 정보 가져오기</a> |\n" +
                "<a href=\"bookmark.jsp\">북마크 보기</a> | <a href=\"bmg.jsp\">북마크 그룹 관리</a>");
        out.println("<table class=\"tg\" id=\"table\">\n" +
                "    <thead>\n" +
                "    <tr>\n" +
                "        <th class=\"tg-dqvt\">ID</th>\n" +
                "        <th class=\"tg-dqvt\">X좌표</th>\n" +
                "        <th class=\"tg-dqvt\">Y좌표</th>\n" +
                "        <th class=\"tg-dqvt\">조회일자</th>\n" +
                "        <th class=\"tg-dqvt\">비고</th>\n" +
                "    </tr>\n" +
                "    </thead>\n");
        out.println("<tbody>");
        out.println("<tbody>");
        for(LocHty locHty : locHtyList){
            out.println("<tr>");
            out.println("<td>"+ locHty.getId()+"</td>");
            out.println("<td>"+ locHty.getX()+"</td>");
            out.println("<td>"+ locHty.getY()+"</td>");
            out.println("<td>"+ locHty.getDate()+"</td>");
            out.println("<td><button class=\"btn_delte\" type=\"button\">삭제</button></td>");
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
