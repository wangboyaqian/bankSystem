package com.qfedu.interceptor;

import com.qfedu.entity.User;
import com.qfedu.utils.StrUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();//  /user/query.do
        //uri  统一资源标识符
        //url  统一资源定位符

        System.out.println(requestURI);
        User user = (User)request.getSession().getAttribute(StrUtils.LOGIN_USER);
        if (user == null) {//证明没有登录需要跳转到login.html
            //如果是ajaxq请求也得跳转到login.html
            //如果是jquery的ajax请求的话，浏览器会带有一个特殊的请求头
            String value = request.getHeader("X-Requested-With");
            if (value != null&& value.equals("XMLHttpRequest")) {//说明是ajax请求
                response.getWriter().write("{\"code\":0, \"info\":\"未登录\"}");
            } else {
                //没有登录，直接跳转到 login.html  非ajax请求的时候直接登录页面
                response.sendRedirect(request.getContextPath() + "/login.html");
            }
            return false;

        }
        return true;
    }
}
