package com.example.wifidb;

import com.example.wifidb.DB.DataService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddBMServlet", value = "/AddBMServlet")
public class AddBMServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String bmgNm = request.getParameter("bmg");
        String wifinm = request.getParameter("wifinm");

        DataService dataService = new DataService();
        dataService.BkInsert(wifinm, bmgNm);

        out.println("<html>\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\" />\n" +
                "<script type=\"text/javascript\">\n" +
                "   alert('북마크를 추가하였습니다.');\n" +
                " history.go(-1) " +
                "</script>\n" +
                "</head>\n" +
                " <body>\n" +
                " </body>\n" +
                "</html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
