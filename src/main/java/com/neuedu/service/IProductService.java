package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface IProductService {
    //产品添加或更新
    ServerResponse saveOrUpdate( Product product);

     /**
      * 商品上下架
      * 商品id
      * 商品状态
      * */
    ServerResponse  set_sale_status(Integer productID,Integer status);
    /**
     * 后台查看商品详情
     * */
    ServerResponse detail(Integer productID);
    /**
     * 查看显示商品   后台商品列表  分页
     * */
     ServerResponse  list(Integer pageNum,Integer pageSize);
     /**
      * 后台搜索商品
      * */
     ServerResponse search(Integer productId,String productName,Integer pageNum,Integer pageSize);
     /**
      * 图片上传
      * */
     ServerResponse upload(MultipartFile file,String path);
     /**
      * 前台商品详情
      * */
     ServerResponse detail_portal(Integer productId);

     /**
      * 前台商品显示及排序
      * */
     ServerResponse list_protal(Integer categoryId,
                         String keyword,
                         Integer pageNum,
                        Integer pageSize,
                         String orderBy);
}
