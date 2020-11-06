package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.exception.MyException;
import com.xxxx.crm.exception.ParamsException;
import com.xxxx.crm.po.User;
import com.xxxx.crm.service.UserService;
import com.xxxx.crm.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping("toPasswordPage")
    public String toPasswordPage() {
        return "user/password";
    }

    @RequestMapping("index")
    public String index() {
        return "user/user";
    }


    @RequestMapping("addOrUpdateUserPage")
    public String addOrUpdateUserPage(HttpServletRequest request,String id) {
        //如果有id说明是更新
        if (id != null && !"".equals(id)) {
            //根据用户id获取用户信息，放到请求域，让前台从请求域中获取信息，并展示
            User u = userService.selectUserInfoById(id);
            request.setAttribute("user",u);
        }
        //如果没有id说明是新增
        return "user/add_update";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectList(UserVo vo) {
        Map<String, Object> map = userService.selectList(vo);
        return map;
    }


    //Alt + Enter
    @RequestMapping("login")
    @ResponseBody
    public ResultInfo login(String userName, String userPwd) throws MyException, ParamsException {
        /*try {
            u = userService.selectUserByUserNameAndPwd(userName,userPwd);
        } catch (ParamsException e) {
            e.printStackTrace();
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setCode(400);
            resultInfo.setMsg(e.getMsg());
            return resultInfo;
        } catch (MyException e) {
            e.printStackTrace();
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setCode(400);
            resultInfo.setMsg(e.getMsg());
            return resultInfo;
        }*/

        User u = userService.selectUserByUserNameAndPwd(userName, userPwd);

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(200);
        resultInfo.setMsg("");
        resultInfo.setResult(u);
        return resultInfo;
    }


    @ResponseBody
    @RequestMapping("updatePwd")
    public ResultInfo updatePWD(HttpServletRequest request, String oldPwd, String newPwd, String repeatPwd) {
        String id = "";
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equals("id")) {
                id = cookie.getValue();
            }
        }
        try {
            userService.updatePwd(id, oldPwd, newPwd, repeatPwd);
        } catch (ParamsException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping("add")
    @ResponseBody
    public ResultInfo insertUser(User user) {
        userService.insertUser(user);
        return success();
    }


    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateUser(User user) {
        userService.updateUser(user);
        return success();
    }

    /**
     * 尝试用不同方式去接收前台传过来的参数
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteUser(@RequestParam List<String> ids) {
        userService.deleteUser(ids);
        return success();
    }
}
