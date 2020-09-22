package com.qfedu.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.common.JsonResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonUtils {
    public static void writeJsonInfo (int code, Object info, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult(code, info);
        //jackjson有一个类  ObjectMapper
        //writeValueAsString() 将对象转换成json字符串
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(jsonResult);
            PrintWriter writer = response.getWriter();
            writer.write(s);
            writer.flush();//刷新缓存
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
