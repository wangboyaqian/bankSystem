package com.qfedu.controller;

import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import com.qfedu.service.impl.UserServiceImpl;
import com.qfedu.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//login.do     login.action
//@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
    //处理service
    @Autowired
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //让loginServlet支持 依赖注入
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String bankCode = request.getParameter("bankCode");
        String password = request.getParameter("password");
        System.out.println(bankCode);

        try{
            User user = userService.login(bankCode, password);
            //将user存到sesssion中
            request.getSession().setAttribute("loginUser", user);
            //{code:1, info:}
            //response.getWriter().write();
            JsonUtils.writeJsonInfo(1, null, response);
        }catch (Exception e){
            e.printStackTrace();
            JsonUtils.writeJsonInfo(0, e.getMessage(), response);
        }
    }
}
