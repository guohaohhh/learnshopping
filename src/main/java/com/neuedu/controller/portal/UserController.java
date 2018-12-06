package com.neuedu.controller.portal;

import com.neuedu.common.Const;
import com.neuedu.common.ConstCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import javafx.scene.chart.ValueAxis;
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
     * 注册
     * */
    @RequestMapping(value = "register.do")             //对象绑定
    public ServerResponse register(UserInfo userInfo){
        ServerResponse serverResponse=userService.register(userInfo);
        return serverResponse;
    }


    /**
     * 用户名和邮箱是否有效
     * */
    @RequestMapping(value = "chaeck_valid.do")
    public ServerResponse chaeck_valid(String str,String type){
      return userService.chaeck_valid(str, type);
    }
    /**
     * 获取用户的信息
     * */
    @RequestMapping(value = "get_user_info.do")
    public ServerResponse get_user_info(HttpSession session){
          Object o=session.getAttribute(ConstCode.CURRENTUSER);
          if (o!=null && o instanceof UserInfo){
              UserInfo userInfo=(UserInfo) o;
              UserInfo user=new UserInfo();
              user.setId(userInfo.getId());
              user.setUsername(userInfo.getUsername());
              user.setEmail(userInfo.getEmail());
              user.setUpdateTime(userInfo.getUpdateTime());
              user.setCreateTime(userInfo.getCreateTime());
              return ServerResponse.createServerResponseBySucess(null,user);

          }
        return ServerResponse.createServerResponseByError("用户未登录");
    }
    /**
     * 获取详细信息
     * */
    @RequestMapping(value = "get_inforamtion.do")
    public ServerResponse get_inforamtion(HttpSession session){
        Object o=session.getAttribute(ConstCode.CURRENTUSER);
        if (o!=null && o instanceof UserInfo){
            UserInfo userInfo=(UserInfo) o;

            return ServerResponse.createServerResponseBySucess(null,userInfo);

        }
        return ServerResponse.createServerResponseByError("用户未登录");
    }
}
