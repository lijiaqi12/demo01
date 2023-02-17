package com.shida.cn.shida1.controller;


import com.alibaba.fastjson.JSONObject;
import com.shida.cn.shida1.entity.User;
import com.shida.cn.shida1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: 张鹏飞
 * @company： 软通动力信息技术股份有限公司
 * @Official： www.isoftstone.com
 */
@Controller
public class IndexController {

    @Autowired
    UserService userService;


    @RequestMapping("/userRegister")
    public String getIndex(User user){
       //user的信息已经都有了    把这些信息保存到数据库中
        userService.addUser(user);

        //页面应该跳转到 我们的登录页面
        return "login.jsp";
    }



    @RequestMapping("/ajaxUser")
    @ResponseBody
    public JSONObject getAjaxUser(@RequestParam String username){
        JSONObject jsonObject = new JSONObject();
        //用户名  现在拿到了
        User user = userService.findUser(username);
        //判断
        if(user!=null){
            jsonObject.put("userExit",true);
        }else{
            jsonObject.put("userExit",false);
        }
       //把结果返回给 html页面即可
        return jsonObject;
    }
}
