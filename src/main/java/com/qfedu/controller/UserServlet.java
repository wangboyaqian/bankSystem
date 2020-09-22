package com.qfedu.controller;

import com.qfedu.entity.User;
import com.qfedu.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebServlet("/user/query.do")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("loginUser");
        if (loginUser != null) {
            JsonUtils.writeJsonInfo(1, loginUser.getBankCode(), response);
        } else {
            JsonUtils.writeJsonInfo(0, null, response);
        }

    }
}
