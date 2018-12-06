package com.neuedu.controller.portal;

import com.neuedu.common.ConstCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/portal/user/")
public class UserController {
    @Autowired
    IUserService userService;

    /**
     * 登录
     * */
    @RequestMapping(value = "login.do")
    public ServerResponse login(HttpSession session, String username, String password){
        ServerResponse serverResponse=userService.login(username,password);
        if (serverResponse.isSuccess()){//登录状态
            UserInfo userInfo=(UserInfo) serverResponse.getData();
            //登录成功了放进去session里
            session.setAttribute(ConstCode.CURRENTUSER,userInfo);
        }
        return serverResponse;
    }


    /**
     * 注册登录
     * */
    @RequestMapping(value = "register.do")             //对象绑定
    public ServerResponse register(HttpSession session,UserInfo userInfo){
        ServerResponse serverResponse=userService.register(userInfo);
        return serverResponse;
    }

}
