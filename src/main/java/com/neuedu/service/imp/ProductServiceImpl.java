package com.neuedu.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.neuedu.common.ConstCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.CategoryMapper;
import com.neuedu.dao.ProductMapper;
import com.neuedu.pojo.Category;
import com.neuedu.pojo.Product;
import com.neuedu.service.ICategoryService;
import com.neuedu.service.IProductService;
import com.neuedu.utils.DateUtils;
import com.neuedu.utils.PropertiesUtils;
import com.neuedu.vo.ProductDetailVO;
import com.neuedu.vo.ProductListVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.SchemaOutputResolver;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ICategoryService iCategoryService;
/**
 * 商品添加
 * */
    @Override
    public ServerResponse saveOrUpdate(Product product) {
        //step1:参数的非空校验
        if (product==null){
            return  ServerResponse.createServerResponseByError("参数为空");
        }
        //step2：设置商品主图        子图sub_image --》1：jpg 2jpg 3jpg   主图取子图的第一张
        String subImages=product.getSubImages();  //得到子图
        if (subImages!=null && !subImages.equals("")){
             String[] subImagesArr=subImages.split(",");  //得到的是一个数组，用逗号隔开
             if (subImagesArr.length>0){
                 product.setMainImage(subImagesArr[0]);   //主图是附图的第一张
             }
        }
        //step3：对商品的添加或修改
        if (product.getId()==null){
            //添加
            int result=productMapper.insert(product);
            if (result>0){
                return  ServerResponse.createServerResponseBySucess("添加成功");
            }else {
                return  ServerResponse.createServerResponseByError("添加失败了");
            }
        }else {
            //更新
            int result=productMapper.updateByPrimaryKey(product);
            if (result>0){
                return  ServerResponse.createServerResponseBySucess("更新成功");
            }else {
                return  ServerResponse.createServerResponseByError("更新失败了");
            }
        }
        //step4：返回结果

    }
/**
 * 商品上下架
 * */
    @Override
    public ServerResponse set_sale_status(Integer productID, Integer status) {
        System.out.println(productID);
        //step1：参数的非空校验
        if (productID==null){
            return ServerResponse.createServerResponseByError("商品id不能为空");
        }
        if (status==null){
            return ServerResponse.createServerResponseByError("商品状态不能为空");
        }
        //step2：更新状态
        Product product=new Product();
        product.setId(productID);
        product.setStatus(status);
        int result=productMapper.updateByproductStatus(product);
        System.out.println(result);
        //step3：返回结果
        if (result>0){
            return ServerResponse.createServerResponseBySucess("成功");
        }else {
            return ServerResponse.createServerResponseByError("修改失败");
        }
    }
//获取商品详细信息
    @Override
    public ServerResponse detail(Integer productID) {
        //step1:参数的非空校验
        if (productID==null){
            return ServerResponse.createServerResponseByError("商品id不能为空");
        }
        //step2：查询
        Product product1=productMapper.selectByPrimaryKey(productID);
        if (product1==null){
            return ServerResponse.createServerResponseByError("商品不存在");
        }
        //step3：product ——-->prductlVO
        ProductDetailVO productDetailVO=assembleProductDetailVO(product1);
        //step4：返回结果

        return ServerResponse.createServerResponseBySucess("成功",productDetailVO);
    }

    private ProductDetailVO assembleProductDetailVO(Product product){
        ProductDetailVO productDetailVO=new ProductDetailVO();
        productDetailVO.setCategoryId(product.getCategoryId());
        productDetailVO.setCreateTime(DateUtils.dateToStr(product.getCreateTime()));
        productDetailVO.setDetail(product.getDetail());
        productDetailVO.setImageHost(PropertiesUtils.readByKey("imageHost"));
        productDetailVO.setName(product.getName());
        productDetailVO.setMainImage(product.getMainImage());
        productDetailVO.setId(product.getId());
        productDetailVO.setPrice(product.getPrice());
        productDetailVO.setStatus(product.getStatus());
        productDetailVO.setStock(product.getStock());
        productDetailVO.setSubImages(product.getSubImages());
        productDetailVO.setSubtitle(product.getSubtitle());
        productDetailVO.setUpdateTime(DateUtils.dateToStr(product.getUpdateTime()));
        Category  category=categoryMapper.selectByPrimaryKey(product.getCategoryId());
        if (category!=null){
            productDetailVO.setParentCategoryId(category.getParentId());
        }else {
            //默认根节点
            productDetailVO.setParentCategoryId(category.getParentId());
        }

        return  productDetailVO;
    }



//商品列表   分页
    @Override
    public ServerResponse list(Integer pageNum, Integer pageSize) {
        //这段代码一定要放在查询前面
        PageHelper.startPage(pageNum,pageSize);
       //step1：查询商品数据 select * from product limit （pageNum-1）*pageSize，pageSize
        List<Product> productList=productMapper.selectAll();
        List<ProductListVO> productListVOList=Lists.newArrayList();
        if (productList!=null&&productList.size()>0){
            for (Product product:productList) {
             ProductListVO productListVO= assembleProductListVO(product);
             productListVOList.add(productListVO);
            }
        }
        PageInfo pageInfo=new PageInfo(productListVOList);
        return ServerResponse.createServerResponseBySucess("返回成功",pageInfo);
    }

    private ProductListVO assembleProductListVO(Product product){
          ProductListVO productListVO=new ProductListVO();
          productListVO.setId(product.getId());
          productListVO.setCategoryId(product.getCategoryId());
          productListVO.setMainImage(product.getMainImage());
          productListVO.setName(product.getName());
          productListVO.setPrice(product.getPrice());
          productListVO.setSubtitle(product.getSubtitle());
          productListVO.setStatus(product.getStatus());
          return productListVO;
    }
    //商品的搜索
    @Override
    public ServerResponse search(Integer productId,
                                 String productName,
                                 Integer pageNum,
                                 Integer pageSize) {
        //select * from product where  productId ? and productName like %name%
        PageHelper.startPage(pageNum,pageSize);

        if (productName!=null&&!productName.equals("")){
            productName="%"+productName+"%";
            System.out.println(productName);
        }
       List<Product>  productList= productMapper.findProductByProductIdAndProductName(productId,productName);
        List<ProductListVO> productListVOList=Lists.newArrayList();
        if (productList!=null&&productList.size()>0){
            for (Product product:productList) {
                ProductListVO productListVO= assembleProductListVO(product);
                productListVOList.add(productListVO);
            }
        }
        PageInfo pageInfo=new PageInfo(productListVOList);
        return ServerResponse.createServerResponseBySucess("成功",pageInfo);
    }
/**
 * 图片上传
 * */
    @Override
    public ServerResponse upload(MultipartFile file, String path) {
        //step1：
        if(file==null){
            return ServerResponse.createServerResponseByError("出错");
        }
        //step2：获取图片的名字
        String  originalFilename =file.getOriginalFilename();
        //获取图片的扩展名            字符串截取               这个点会截取
        String exName=originalFilename.substring(originalFilename.lastIndexOf("."));
        //获取新的图片名字唯一的
        String newFileName=UUID.randomUUID().toString()+exName;
        File pathFile=new File(path);
        if (!pathFile.exists()){
            pathFile.setWritable(true);
            pathFile.mkdirs();
        }

        File file1=new File(path,newFileName);
        try {
            //把文件写到了file1
            file.transferTo(file1);
            //上传到图片服务器
            Map<String,String> map=Maps.newHashMap();
            map.put("uri",newFileName);
            map.put("url",PropertiesUtils.readByKey("imageHost")+"/"+newFileName);
            return ServerResponse.createServerResponseBySucess("成功",map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 前台商品详情
     * */
    @Override
    public ServerResponse detail_portal(Integer productId) {
        //step1：参数的非空校验
        if (productId==null){
            return ServerResponse.createServerResponseByError("商品id不能为空");
        }
        //step2:查询productId
        Product product1=productMapper.selectByPrimaryKey(productId);
        if (product1==null){
            return ServerResponse.createServerResponseByError("商品不存在");
        }
        //step3:效验商品状态
        if (product1.getStatus()!=ConstCode.ProductStatusEnum.PRODUCT_ONL_INE.getCode()){
            return ServerResponse.createServerResponseByError("商品已下架或删除");

        }
        //step4:获取productDetailVO
        ProductDetailVO productDetailVO=assembleProductDetailVO(product1);

        return ServerResponse.createServerResponseBySucess("成功",productDetailVO);
    }
/**
 * 前台商品排序
 * */
    @Override
    public ServerResponse list_protal(Integer categoryId, String keyword, Integer pageNum, Integer pageSize,String orderBy) {
        //step1:参数的非空校验
        if (categoryId==null&&(keyword==null||keyword.equals(""))){
            return ServerResponse.createServerResponseByError("参数错误");
        }
        //step2:categoryId
        Set<Category> categorySet=Sets.newHashSet();
        if (categoryId!=null){
            Category category=categoryMapper.selectByPrimaryKey(categoryId);
            System.out.println("按照id查找到的值"+category);
            if (category==null&&StringUtils.isBlank(keyword)){
                //说明没有商品数据
                PageHelper.startPage(pageNum,pageSize);
                List<ProductListVO> productListVOList=Lists.newArrayList();
                PageInfo pageInfo=new PageInfo(productListVOList);
                return ServerResponse.createServerResponseBySucess("成功",pageInfo);
            }
            ServerResponse serverResponse=iCategoryService.get_deep_category(categoryId);
            System.out.println("serverResponse的值"+serverResponse);
            if (serverResponse.isSuccess()){
                categorySet= (Set<Category>)serverResponse.getData();
            }

        }
        //step3:keyword
        if (keyword!=null&&!keyword.equals("")){
            keyword="%"+keyword+"%";
        }
        if (orderBy.equals("")){
            PageHelper.startPage(pageNum,pageSize);
        }else {
           String[] orderByAll=orderBy.split("_") ;
           if (orderByAll.length>1){
               PageHelper.startPage(pageNum,pageSize,orderByAll[0]+""+orderByAll[1]);
           }else {
               PageHelper.startPage(pageNum,pageSize);

           }
        }
        //step4:List<Product>--->List<ProductListVO>

//        Iterator<Integer> iterator=integerSet.iterator();
//        while(iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
        List<Product> productList=productMapper.searchProduct(categorySet,keyword);
        System.out.println("这个值不是空把"+productList);
        List<ProductListVO> productListVOList=Lists.newArrayList();
        if (productList!=null&&productList.size()>0){
            for (Product product:productList){
                ProductListVO  productListVO= assembleProductListVO(product);
                productListVOList.add(productListVO);
            }
        }
        //step5：分页
        PageInfo pageInfo=new PageInfo();
        pageInfo.setList(productListVOList);
        //返回
        return ServerResponse.createServerResponseBySucess("成功",pageInfo);
    }


}
