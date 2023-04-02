package com.example.wifidb;

import com.example.wifidb.DB.DataService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DelBmServlet", value = "/DelBmServlet")
public class DelBmServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int bmgId = Integer.parseInt(request.getParameter("bgmId"));

        DataService dataService = new DataService();
        dataService.BkDelete(bmgId);

        out.println("<html>\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\" />\n" +
                "<script type=\"text/javascript\">\n" +
                "   alert('북마크 그룹을 삭제하였습니다.');\n" +
                " location.href=\"bookmark.jsp\"; " +
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
