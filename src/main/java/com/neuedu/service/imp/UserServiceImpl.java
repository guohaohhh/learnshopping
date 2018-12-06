package com.neuedu.service.imp;

import com.neuedu.common.ConstCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.UserInfoMapper;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
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
        int result=userInfoMapper.checkUsername(userInfo.getUsername());
        if (result>0){//用户有了
            return  ServerResponse.createServerResponseByError("用户名已存在");
        }
        //step3：效验邮箱
        int resultemail=userInfoMapper.checkEmail(userInfo.getEmail());
        if (resultemail>0){//邮箱有了
            return  ServerResponse.createServerResponseByError("邮箱已注册");
        }
        //step4:注册
        userInfo.setRole(ConstCode.RoleEnum.ROLE_CUSTOMER.getCode());
        int count=userInfoMapper.insert(userInfo);
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
      int result=userInfoMapper.checkUsername(username);
        if (result<=0){//用户名没有
            return  ServerResponse.createServerResponseByError("用户名不存在");
        }
        //step3：根据用户名和密码查询
      UserInfo userInfo= userInfoMapper.selectUserByUsernameAndPassword(username,password);
        if (userInfo==null){//密码错误
            return ServerResponse.createServerResponseByError("密码错误");
        }
        //step4：处理结果并返回
        userInfo.setPassword("");//因为要查询出来，不能显示密码
        return ServerResponse.createServerResponseBySucess(null,userInfo);
    }
}
