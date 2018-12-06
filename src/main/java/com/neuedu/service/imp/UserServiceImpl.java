package com.neuedu.service.imp;

import com.neuedu.common.Const;
import com.neuedu.common.ConstCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.UserInfoMapper;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import com.neuedu.utils.MD5Utils;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.util.Password;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserInfoMapper userInfoMapper;
    /**
     *注册
     **/
    @Override
    public ServerResponse register(UserInfo userInfo) {
        //step1：参数非空校验
        if (userInfo==null){
            return ServerResponse.createServerResponseByError("参数为空");
        }
        //step2：效验用户名
        //int result=userInfoMapper.checkUsername(userInfo.getUsername());
//        if (result>0){//用户有了
//            return  ServerResponse.createServerResponseByError("用户名已存在");
//        }
        ServerResponse serverResponse=chaeck_valid(userInfo.getUsername(),Const.USERNAME);
        if (!serverResponse.isSuccess()){
            return ServerResponse.createServerResponseByError("用户名已存在");
        }


        //step3：效验邮箱
//        int resultemail=userInfoMapper.checkEmail(userInfo.getEmail());
//        if (resultemail>0){//邮箱有了
//            return  ServerResponse.createServerResponseByError("邮箱已注册");
//        }
        ServerResponse emailserverResponse=chaeck_valid(userInfo.getEmail(),Const.EMAIL);
        if (!emailserverResponse.isSuccess()){
            return ServerResponse.createServerResponseByError("邮箱已注册");
        }



        //step4:注册
        userInfo.setRole(ConstCode.RoleEnum.ROLE_CUSTOMER.getCode());//给他个等级
        userInfo.setPassword(MD5Utils.getMD5Code(userInfo.getPassword()));//密码加密
        int count=userInfoMapper.insert(userInfo);
        //step5：返回结果
        if (count>0){
            return ServerResponse.createServerResponseBySucess("注册成功");
        }

            return ServerResponse.createServerResponseByError("注册失败");
    }


    /**
     * 登录
     * */
//因为在apache提供的commons-lang包中定义了方法  StringUtils.isBlank()  和 StringUtils.isEmpty()
    //StringUtils.isBlank()能判断空格为空
    @Override
    public ServerResponse login(String username, String password) {
        //step1：参数非空效验     如果是ture，这个值就是空的
        if (StringUtils.isBlank(username)){
            return ServerResponse.createServerResponseByError("用户名不能为空");
        }
        if (StringUtils.isBlank(password)){
            return ServerResponse.createServerResponseByError("密码不能为空");
        }

        //step2：检查username是否存在   查询count（*） 大于0就有 <=0 就没有
//      int result=userInfoMapper.checkUsername(username);
//        if (result<=0){//用户名没有
//            return  ServerResponse.createServerResponseByError("用户名不存在");
//        }
        ServerResponse serverResponse=chaeck_valid(username,Const.USERNAME);
        if (serverResponse.isSuccess()){
            return ServerResponse.createServerResponseByError("用户名不存在");
        }

        //step3：根据用户名和密码查询                                             密码也加密进行对比
      UserInfo userInfo= userInfoMapper.selectUserByUsernameAndPassword(username,MD5Utils.getMD5Code(password));
        if (userInfo==null){//密码错误
            return ServerResponse.createServerResponseByError("密码错误");
        }
        //step4：处理结果并返回
        userInfo.setPassword("");//因为要查询出来，不能显示密码
        return ServerResponse.createServerResponseBySucess(null,userInfo);
    }

    /**
     * 用户名和邮箱是否有效
     * */
    @Override                       //      姓名或者邮箱        参数
    public ServerResponse chaeck_valid(String str, String type) {
        //step1：参数非空效验
        if (StringUtils.isBlank(str) || StringUtils.isBlank(type)){
            return ServerResponse.createServerResponseByError("参数不能为空");
        }
        //step2：判断有用户名或这邮箱是否存在
        if (type.equals(Const.USERNAME)){
            int username_result=userInfoMapper.checkUsername(str);
            if (username_result>0){
                return ServerResponse.createServerResponseByError("用户名已存在");
            }
            return ServerResponse.createServerResponseBySucess("成功");
        }else if (type.equals(Const.EMAIL)){
            int email_result=userInfoMapper.checkEmail(str);
            if (email_result>0){
                return ServerResponse.createServerResponseByError("邮箱已存在");
            }
            return ServerResponse.createServerResponseBySucess("成功");
        }
        //step3：返回结果
        return ServerResponse.createServerResponseByError("参数有误");
    }
}
