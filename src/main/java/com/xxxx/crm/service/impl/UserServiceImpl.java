package com.xxxx.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.crm.dao.UserDao;
import com.xxxx.crm.exception.MyException;
import com.xxxx.crm.exception.ParamsException;
import com.xxxx.crm.po.User;
import com.xxxx.crm.service.UserService;
import com.xxxx.crm.utils.Md5Util;
import com.xxxx.crm.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

//Ctrl + O 重写方法
@Service
public class UserServiceImpl implements UserService {
    //Ctrl Alt L
    @Autowired
    private UserDao userDao;

    /**
     * 目的是校验用户名和密码是否正确
     * <p>
     * 空字符串
     * 用户不存在
     * 用户密码不符合
     *
     * Base64 加密  123456 → aslkdfnmoaskfi
     *              aslkdfnmoaskfi → 123456
     *
     * MD5 加密  123456 → sadfsadpfsadffdsg
     * 123456 再次加密 sadfsadpfsadffdsg
     *
     *
     * @param userName
     * @param userPwd
     */
    @Override
    public User selectUserByUserNameAndPwd(String userName, String userPwd) throws ParamsException, MyException {
        if (userName == null || "".equals(userName.trim())) {
            throw new ParamsException(20001, "用户名不能为空");
        }
        if (userPwd == null || "".equals(userPwd.trim())) {
            throw new ParamsException(20001, "用户密码不能为空");
        }
        User user = userDao.selectUserByUserName(userName);
        if (user == null) {
            throw new MyException("用户名不存在");
        }
        String userPwd1 = user.getUserPwd();
        String encode = Md5Util.encode(userPwd);
        //如果从前台传过来的密码，进行MD5加密过后，与数据库一样
        if(!userPwd1.equals(encode)){
            throw new MyException("用户密码错误");
        }
        return user;
    }

    @Override
    public void updatePwd(String id, String oldPwd, String newPwd, String repeatPwd) throws ParamsException, MyException {
        //认证新密码，确认密码不能为空
        if (oldPwd == null || "".equals(oldPwd.trim())) {
            System.out.println("旧密码不能为空");
            throw new ParamsException(20002, "旧密码不能为空");
        }
        if(!newPwd.equals(repeatPwd)){
            System.out.println("确认密码与新密码不同");
            throw new MyException("确认密码与新密码不同");
        }
        //根据id 查询用户
        User u = userDao.selectUserInfoById(id);
        String encode = Md5Util.encode(oldPwd);

        String userPwd = u.getUserPwd();

        System.out.println(encode);
        System.out.println(userPwd);

        if(!encode.equals(userPwd)){
            System.out.println("旧密码与数据库密码不同");
            return;
            //throw new MyException("旧密码与数据库密码不同");
        }
        userDao.updateUserPwd(id,Md5Util.encode(newPwd));

        List list = new ArrayList<>();


    }

    @Override
    public Map<String, Object> selectList(UserVo vo) {
        //使用 pageHelper 帮我们处理了总记录数
        PageHelper.startPage(vo.getPage(), vo.getLimit());
        //
        List<User> list = userDao.selectList(vo);
        PageInfo<User> pageInfo = new PageInfo<>(list);


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", pageInfo.getTotal());
        map.put("data", list);
        return map;
    }

    @Override
    public void insertUser(User user) {
        String pwd = "123456";
        String encode = Md5Util.encode(pwd);

        user.setUserPwd(encode);

        user.setIsValid(1);
        user.setCreateDate(new Date());
        userDao.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        user.setUpdateDate(new Date());
        userDao.updateUser(user);
    }

    @Override
    public User selectUserInfoById(String id) {
        User user = userDao.selectUserInfoById(id);
        return user;
    }

    @Override
    public void deleteUser(List<String> ids) {
        userDao.deleteUser(ids);
    }
}
