package com.qfedu.controller;


import com.qfedu.common.JsonResult;
import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import com.qfedu.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/query.do")
    @ResponseBody


    public JsonResult getLoginInfo (HttpSession session) {
        User u = (User)session.getAttribute(StrUtils.LOGIN_USER);
        //需要再次从数据库查一遍
        User user = userService.selectByCode(u.getBankCode());

        JsonResult jsonResult = new JsonResult(1, user);
        return jsonResult;
    }
}
