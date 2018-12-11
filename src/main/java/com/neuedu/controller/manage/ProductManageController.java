package com.neuedu.controller.manage;

import com.neuedu.common.ConstCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Product;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IProductService;
import com.neuedu.service.imp.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/manage/product/")
public class ProductManageController {

    @Autowired
    IProductService productService;


    /**
     * 新镇OR更新产品
     * */
    @RequestMapping(value = "save.do")
    public ServerResponse saveOrUpdate(HttpSession session, Product product){
        //判断用户是否登录
        UserInfo userInfo=(UserInfo)session.getAttribute(ConstCode.CURRENTUSER);
        if (userInfo==null){
           return ServerResponse.createServerResponseByError(ConstCode.ReponseCodeEnum.NEED_LOGIN.getCode(),ConstCode.ReponseCodeEnum.NEED_LOGIN.getDesc());
        }
        if (userInfo.getRole()!=ConstCode.RoleEnum.ROLE_ADMIN.getCode()){
            return ServerResponse.createServerResponseByError(ConstCode.ReponseCodeEnum.NO_PRIVILEGE.getCode(),ConstCode.ReponseCodeEnum.NO_PRIVILEGE.getDesc());
        }

          return  productService.saveOrUpdate(product);
    }

/**
 * 产品上下架
 * */
@RequestMapping(value = "set_sale_status.do")        //  按照id进行修改        状态：是否下架
public ServerResponse set_sale_status(HttpSession session,Integer productID, Integer status){
    //判断用户是否登录
    UserInfo userInfo=(UserInfo)session.getAttribute(ConstCode.CURRENTUSER);
    if (userInfo==null){
        return ServerResponse.createServerResponseByError(ConstCode.ReponseCodeEnum.NEED_LOGIN.getCode(),ConstCode.ReponseCodeEnum.NEED_LOGIN.getDesc());
    }
    if (userInfo.getRole()!=ConstCode.RoleEnum.ROLE_ADMIN.getCode()){
        return ServerResponse.createServerResponseByError(ConstCode.ReponseCodeEnum.NO_PRIVILEGE.getCode(),ConstCode.ReponseCodeEnum.NO_PRIVILEGE.getDesc());
    }

    return  productService.set_sale_status(productID,status);
}

/**
 * 查看商品详细信息
 * */
@RequestMapping(value = "detail.do")        //  按照id进行修改        状态：是否下架
public ServerResponse detail(HttpSession session,Integer productID){
    //判断用户是否登录
    UserInfo userInfo=(UserInfo)session.getAttribute(ConstCode.CURRENTUSER);
    if (userInfo==null){
        return ServerResponse.createServerResponseByError(ConstCode.ReponseCodeEnum.NEED_LOGIN.getCode(),ConstCode.ReponseCodeEnum.NEED_LOGIN.getDesc());
    }
    if (userInfo.getRole()!=ConstCode.RoleEnum.ROLE_ADMIN.getCode()){
        return ServerResponse.createServerResponseByError(ConstCode.ReponseCodeEnum.NO_PRIVILEGE.getCode(),ConstCode.ReponseCodeEnum.NO_PRIVILEGE.getDesc());
    }

    return  productService.detail(productID);
}

/**
 * 查看商品列表
 * */
@RequestMapping(value = "list.do")
public ServerResponse list(HttpSession session
                            , @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize){
    //判断用户是否登录
    UserInfo userInfo=(UserInfo)session.getAttribute(ConstCode.CURRENTUSER);
    if (userInfo==null){
        return ServerResponse.createServerResponseByError(ConstCode.ReponseCodeEnum.NEED_LOGIN.getCode(),ConstCode.ReponseCodeEnum.NEED_LOGIN.getDesc());
    }
    if (userInfo.getRole()!=ConstCode.RoleEnum.ROLE_ADMIN.getCode()){
        return ServerResponse.createServerResponseByError(ConstCode.ReponseCodeEnum.NO_PRIVILEGE.getCode(),ConstCode.ReponseCodeEnum.NO_PRIVILEGE.getDesc());
    }

    return  productService.list(pageNum,pageSize);
}
/**
 * 商品的搜索
 * */
    @RequestMapping(value = "search.do")
    public ServerResponse search(HttpSession session
            , @RequestParam(value = "productId") Integer productId,
            @RequestParam(value = "productName") String productName
            ,@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize){

        System.out.println("我进来了没有"+productName);
            //判断用户是否登录
        UserInfo userInfo=(UserInfo)session.getAttribute(ConstCode.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.createServerResponseByError(ConstCode.ReponseCodeEnum.NEED_LOGIN.getCode(),ConstCode.ReponseCodeEnum.NEED_LOGIN.getDesc());
        }
        if (userInfo.getRole()!=ConstCode.RoleEnum.ROLE_ADMIN.getCode()){
            return ServerResponse.createServerResponseByError(ConstCode.ReponseCodeEnum.NO_PRIVILEGE.getCode(),ConstCode.ReponseCodeEnum.NO_PRIVILEGE.getDesc());
        }

        return  productService.search(productId,productName,pageNum,pageSize);
    }
    /**
     * 图片的上传
     * */

//    @RequestMapping(value = "upload.do")
//    public ServerResponse upload(HttpSession session
//            , @RequestParam(value = "productId") Integer productId,
//                                 @RequestParam(value = "productName") String productName
//            ,@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
//                                 @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize){
//
//        System.out.println("我进来了没有"+productName);
//        //判断用户是否登录
//        UserInfo userInfo=(UserInfo)session.getAttribute(ConstCode.CURRENTUSER);
//        if (userInfo==null){
//            return ServerResponse.createServerResponseByError(ConstCode.ReponseCodeEnum.NEED_LOGIN.getCode(),ConstCode.ReponseCodeEnum.NEED_LOGIN.getDesc());
//        }
//        if (userInfo.getRole()!=ConstCode.RoleEnum.ROLE_ADMIN.getCode()){
//            return ServerResponse.createServerResponseByError(ConstCode.ReponseCodeEnum.NO_PRIVILEGE.getCode(),ConstCode.ReponseCodeEnum.NO_PRIVILEGE.getDesc());
//        }
//
//        return  productService.upload(productId,productName,pageNum,pageSize);
//    }













































}
