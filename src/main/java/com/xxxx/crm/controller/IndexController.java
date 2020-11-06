package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("main")
    public String main(){
        return "main";
    }

    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }



   /*//如果想完成首页用户名展示，完成以下service调用
   @RequestMapping("main")
    public String main(HttpServletRequest request){
        String id = "";
        //从 请求 request 里获取 Cookie数组
        Cookie[] cookies = request.getCookies();
        //遍历数组
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            //根据 cookie里的值 获取 id，
            if (cookie.getName().equals("id")) {
                id = cookie.getValue();
            }
        }
        //自我完成后面的业务逻辑，根据id获取用户信息
        User u = userService.selectUserById(id);
        //放到请求域中,注意这里请求域的 key用 "user",因为前端代码已经写好了，用user
        request.setAttribute("user", u);
        return "main";
    }*/

}
