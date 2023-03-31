package com.example.wifidb;

import com.example.wifidb.DB.DataService;
import com.example.wifidb.model.WifiInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetInfoServlet", value = "/GetInfoServlet")
public class GetInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        DataService dataService = new DataService();
        ApiExplorer apiExplorer = new ApiExplorer();

        out.println("<html><body>");
        out.println("<style>\n" +
                "    .center{\n" +
                "      text-align: center;\n" +
                "      }\n" +
                "    </style>");
        out.println("<h1 style=\"text-align: center\">" + apiExplorer.getWifiInfomation() + "개의 WIFI 정보를 정상적으로 저장하였습니다." + "</h1><br>");
        out.println("<div class=\"center\">\n" +
                "    <a href=\"index.jsp\">홈 으로 가기</a>\n" +
                "    </div>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

    }
}
