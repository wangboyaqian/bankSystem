package com.qfedu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//想让你们看一下  请求前和渲染后中间间隔的时间差
public class TimeInterceptor implements HandlerInterceptor {

    //可以使用ThreadLocal进行线程间变量的安全使用
    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    //long beginTime;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("prehandle");
       long beginTime = System.currentTimeMillis();
       //threadLocal 存放变量的值
        threadLocal.set(beginTime);
        Thread.sleep(5000);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
        long endTime = System.currentTimeMillis();
        //把beginTIme获取出来
        Long beginTime = threadLocal.get();

        long v = endTime - beginTime;
        System.out.println(v);

    }
}
