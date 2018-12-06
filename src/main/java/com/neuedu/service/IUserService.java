package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;

public interface IUserService {
    /**
     * 注册
     * */
    public ServerResponse register(UserInfo userInfo);

    /***
     * 登录
     */
    public ServerResponse login(String username,String password);

    //判断用户名和邮箱是否有效
    public ServerResponse chaeck_valid(String str,String type);

}
