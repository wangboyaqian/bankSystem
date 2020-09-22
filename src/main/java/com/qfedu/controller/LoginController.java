package com.qfedu.controller;

import com.qfedu.common.JsonResult;
import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import com.qfedu.utils.StrUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Size;
import java.util.Iterator;


@Controller
@Validated//如果要在方法的参数上面使用验证注解，一定要先在这个类上写@Validated这样的注解
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login.do")
    @ResponseBody
    public JsonResult login (@NotEmpty(message = "{name.empty}") String bankCode, @Size(min = 1, max = 10) String password, HttpSession session) {
        User user = userService.login(bankCode, password);
        session.setAttribute(StrUtils.LOGIN_USER, user);
        JsonResult jsonResult = new JsonResult(1, null);
        return  jsonResult;
    }



}
