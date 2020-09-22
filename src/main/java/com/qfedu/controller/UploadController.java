package com.qfedu.controller;

import com.qfedu.common.JsonResult;
import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import com.qfedu.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {

    @Autowired
    private UserService userService;

    @RequestMapping("/upload.do")
    @ResponseBody
    /*
    * 上传文件对应方法的参数类型为@RequestParm  MultpartFile，并且@RequestParam必须带
    * */
    public JsonResult uploadFile (@RequestParam MultipartFile upfile, HttpSession session) {
        User u = (User)session.getAttribute(StrUtils.LOGIN_USER);
        if (u == null) {
            return new JsonResult(0, "未登录");
        }
        //0.把文件写到本地磁盘里面，刚好对应咱们的虚拟路径  c:/upload
        //1.上传文件目录
        String dir = "c:/upload";
        //2.获取上传文件的额名字
        String filename = upfile.getOriginalFilename();
        System.out.println(filename);
        //3.判断上传文件的目录是否存在
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        //4.要把上传的文件保存成一个对象
        File newFile = new File(dir, filename);

        try {
            //5.保存文件
            upfile.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //操作数据库
        userService.updateHeadImg(u.getId(), "/upload/"+ filename);
        return new JsonResult(1, "上传成功");
    }
}
