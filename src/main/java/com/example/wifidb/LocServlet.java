package com.example.wifidb;


import com.example.wifidb.DB.DataService;
import com.example.wifidb.model.LocHty;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "LocServlet", value = "/loc-servlet")
public class LocServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("action");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        DataService dataService = new DataService();
        Float x = Float.valueOf(request.getParameter("LAT"));
        Float y = Float.valueOf(request.getParameter("LNT"));

        LocHty locHty = new LocHty();
        locHty.setX(x);
        locHty.setY(y);

        dataService.historyInsert(locHty);
    }
}
