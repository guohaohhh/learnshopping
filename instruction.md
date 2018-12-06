 ```
 # 配置用户名（提交时会引用）
 ## git config -- global user.name"你的用户名"
 # 配置邮箱
 ## git config --global user.email "你的邮箱"
 # 编码配置
 ## 避免git gui中的中文乱码
 ## git config --global gui.encoding utf-8
 ## 避免 git status显示的中文文件名乱码
 ## git config --global core.quotepath off
 # 其他
 ## git config --global core.ignorecase false
 # git ssh key pair配置
 ## 1.在git bash命令行窗口中输入：
 ## ssh-keygen -t rsa -C "你的邮箱"
 ## 2.然后一路回车，不要输入任何密码之类，生成ssh key pair
 ## 3.在用户目录下生成.ssh文件夹，找到公钥和私钥
 ### id_rsa id_rsa.pub
 ## 4.将公钥的内容复制
 ## 5.进入github网站，将公钥添加进去
 ```
 
 ```
 # -----------git指令-----------
 ### git init 创建本地仓库
 ### git commit -m “描述” 提交到本地仓库
 ### git status  查看
 ### git add 添加到暂存区
 ### git log 查看提交 committed
 ### git rest --hard committed 版本回退
 ### git branch 查看分支
 ### git checkout -b dev 创建并切到dev分支
 ### git checkout 分支名 切换分支
 ### git pull 拉取
 ### git push -u origin master 提交
 ### git merge branchname 合并分支
 ### git push origin HEAD -u 将本地分支送到远程分支
 ### git fetch  将远程在本地看
 ### git clone 地址名   克隆
 ### git --version 查看版本
 ### git add .  提交所有
 ### git remote add origin “地址”  关联远程仓库地址
 ### git remote remove origin   解除关联
 ### git pull origin master 把远程仓库内容拉取出来
 # -------创建文件 .gitignore-----------
 # 此为注释 – 将被 Git 忽略
 ## *.a # 忽略所有 .a 结尾的文件
 ## !lib.a # 但 lib.a 除外
 ## /TODO # 仅仅忽略项目根目录下的 TODO 文件，不包括 subdir/TODO
 ## build/ # 忽略 build/ 目录下的所有文件
 ## doc/*.txt # 会忽略 doc/notes.txt 但不包括 doc/server/arch.txt
 # 需求分析
 ## 一、用户模块
 ### 登录
 ### 注册
 ### 忘记密码
 ### 获取用户信息
 ### 修改密码
 ### 登出
 ## 二、商品模块
 ### 后台
 ### 添加商品
 ### 修改商品
 ### 删除商品
 ### 商品上下架
 ### 查看商品
 ### 前台（门户）
 ### 搜索商品 
 ### 查看商品详情
 ## 三、类别模块
 ### 添加类别
 ### 修改类别
 ### 删除类别
 ###  查看类别
 ### 查看子类
 ### 查看后代类别  
 ## 四、购物车模块
 ### 添加到购物车
 ### 修改购物车中某个商品的数量
 ### 删除购物车商品
 ### 全选/取消全选
 ### 单选/取消单选
 ### 查看购物车中商品数量
 ## 五、地址模块
 ### 添加地址
 ### 修改地址
 ### 删除地址
 ### 查看地址 
 ## 六、订单模块
 ### 前台
 ### 下订单
 ### 订单列表
 ### 取消订单
 ### 订单详情
 ## 后台 
 ### 订单列表
 ### 订单详情
 ### 发货
 ## 七、支付模块
 ### 支付宝支付
 ### 支付
 ### 支付回调
 ### 查看支付状态
 ## 八、线上部署
 ### 阿里云部署
 # 远程分支的合并
 ### git checkout dev 创建一个分支
 ### git pull origin dev 从远程中提取出来
 ### git checkout master 选择分支
 ### git merge dev 分支合并
 ### git push origin master  提交到远程仓库
 ```
 
 ```
 # ---------数据库设计-----------
 # ------创建数据库-----
 create database ilearnshopping;
 use ilearnshopping;
 ### 用户表 
 create table neuedu_user(
 `id`  int(11) not null auto_increment comment '用户id',
 `username`  varchar(50) not null comment '用户名',
 `password`  varchar(50) not null comment '密码',
 `email`     varchar(50) not null comment '邮箱',
 `phone`     varchar(11) not null comment '联系方式',
 `question`  varchar(100) not null comment '密保问题',
 `answer`    varchar(100) not null comment '答案',
 `role`      int(4) not null comment '用户角色',
 `create_time` datetime comment '创建时间',
 `update_time` datetime comment '修改时间',
  PRIMARY KEY(`id`),
  UNIQUE KEY `user_name_index`(`username`) USING BTREE
   )ENGINE=InnoDB DEFAULT CHARSET=UTF8;
 ### 类别表
 create table nuedu_category(
  `id` int(11) not null auto_increment comment '类别id',
  `parent_id`  int(11) not null default 0 comment '父类id',
  `name`  varchar(50) not null comment '类别名称',
  `status` int(4) default 1 comment '类别状态 1：正常 0：废弃',
  `create_time` datetime comment '创建时间',
  `update_time` datetime comment '修改时间',
  PRIMARY KEY(`id`)
  )ENGINE=InnoDB DEFAULT CHARSET=UTF8;
                  id       parent_id
  电子产品   1      1          0
  家电       2      2          1
  手机       2      3          1
  电脑       2      4          1
  相机       2      5          1
  华为手机   3      6          3
  小米手机   3      7          3
  p系列      4      8          6
  mate系列   4      9          6
  . . . .
 ### 商品表
 create table neuedu_product(
 `id` int(11) not null auto_increment comment '商品id',
 `category_id` int(11) not null comment '商品所属的类别id，值引用类别表的id',
 `name` varchar(100) not null comment '商品名称',
 `detail` text comment '商品详情',
 `subtitle` varchar(200) comment '商品副标题',
 `main_image`   varchar(100)  comment '商品主图', 
 `sub_images`   varchar(200)  comment '商品子图',
 `price`   decimal(20,2) not null  comment '商品价格,总共20位，小数2位，整数18位',
 `stock`   int(11)       comment '商品库存',
 `status` int(6)     default 1   comment '商品状态 1:在售 2:下架 3:删除',
 `create_time` datetime    comment '创建时间',
 `update_time`  datetime   comment '修改时间',
  PRIMARY KEY(`id`)
   )ENGINE=InnoDB DEFAULT CHARSET=UTF8
 
 ### 购物车表
  create table neuedu_cart(
  `id`  int(11) not null   auto_increment  comment '购物车id',  
  `user_id`  int(11) not null comment '用户id',
  `product_id`  int(11) not null comment '商品id',
  `quantity` int(11) not null  comment '购买数量',
  `checked`  int(4) default 1 comment '1:选中 0:未选中', 
  `create_time`   datetime    comment '创建时间',
  `update_time`  datetime   comment '修改时间',
   PRIMARY KEY(`id`),
   key `user_id_index`(`user_id`) USING BTREE
     )ENGINE=InnoDB DEFAULT CHARSET=UTF8
 ### 订单表
 create table neuedu_order(
  `id`  int(11)    not null  auto_increment comment '订单id,主键',
  `order_no`   bigint(20) not null  comment '订单编号',
  `user_id`  int(11)  not null  comment '用户id',
  `payment`  decimal(20,2) not null comment '付款总金额，单位元，保留两位小数',
  `payment_type` int(4) not null default 1 comment '支付方式 1:线上支付 ',
  `status`  int(10) not null  comment '订单状态 0-已取消  10-未付款 20-已付款 30-已发货 40-已完成  50-已关闭',   
  `shipping_id`  int(11) not null comment '收货地址id',
  `postage`  int(10) not null default 0 comment '运费', 
  `payment_time` datetime  default null  comment '已付款时间',
  `send_time`    datetime  default null  comment '已发货时间',
  `close_time`    datetime  default null  comment '已关闭时间',
  `end_time`     datetime  default null  comment '已结束时间',
  `create_time`   datetime  default null  comment '已创建时间',
  `update_time`   datetime  default null  comment '更新时间',
   PRIMARY KEY(`id`),
   UNIQUE KEY `order_no_index`(`order_no`) USING BTREE
    )ENGINE=InnoDB DEFAULT CHARSET=UTF8
 ### 订单明细表
 create table neuedu_order_item(
    `id`           int(11)    not null  auto_increment comment '订单明细id,主键',
    `order_no`     bigint(20) not null  comment '订单编号',
    `user_id`      int(11)  not null  comment '用户id',
    `product_id`   int(11)  not null comment '商品id',
    `product_name` varchar(100)  not null comment '商品名称',
    `product_image`  varchar(100)  comment '商品主图', 
    `current_unit_price` decimal(20,2) not null comment '下单时商品的价格，元为单位，保留两位小数',
    `quantity`     int(10)  not null comment '商品的购买数量',
    `total_price`  decimal(20,2) not null comment '商品的总价格，元为单位，保留两位小数',
    `create_time`    datetime  default null  comment '已创建时间',
    `update_time`    datetime  default null  comment '更新时间',
     PRIMARY KEY(`id`),
     KEY `order_no_index`(`order_no`) USING BTREE,
     KEY `order_no_user_id_index`(`order_no`,`user_id`) USING BTREE
  )ENGINE=InnoDB DEFAULT CHARSET=UTF8
 ### 支付表
 create table neuedu_payinfo(
 `id` int(11)    not null  auto_increment comment '主键',
 `order_no`  bigint(20) not null  comment '订单编号',
 `user_id`  int(11)  not null  comment '用户id',
 `pay_platform` int(4)  not null default 1  comment '1:支付宝 2:微信', 
 `platform_status`  varchar(50) comment '支付状态', 
 `platform_number`  varchar(100) comment '流水号',
 `create_time`  datetime  default null  comment '已创建时间',
 `update_time`  datetime  default null  comment '更新时间',
  PRIMARY KEY(`id`)
   )ENGINE=InnoDB DEFAULT CHARSET=UTF8
 ### 地址表
 create table neuedu_shipping(
`id`  int(11) not null  auto_increment comment '地址id',
`user_id`  int(11)  not  null comment '用户id' ,
`receiver_name`  varchar(20) default  null  COMMENT '收货姓名' ,
`receiver_phone`  varchar(20) default  null  COMMENT '收货固定电话' ,
`receiver_mobile`  varchar(20) default  null  COMMENT '收货移动电话' ,
`receiver_province`  varchar(20) default  null  COMMENT '省份' ,
`receiver_city` varchar(20) default  null  COMMENT '城市' ,
`receiver_district`  varchar(20) default  null  COMMENT '区/县' ,
`receiver_address`  varchar(200) default  null  COMMENT '详细地址' ,
`receiver_zip`  varchar(6) default  null  COMMENT '邮编' ,
`create_time`  datetime  not null  comment '创建时间',
`update_time`  datetime  not null  comment '最后一次更新时间',
PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
 ```
 
 ```
# 项目架构--四层架构
 ## 视图层
 ## 控制层controller
 ## 业务逻辑层service
 ## 接口和实现类
 ## Dao层
 # Mybatis-generator插件
 # 搭建ssm框架
 ##  在main目录下创建 java/resoures
 ### 在java/resoures下创建 com .neueud
 #### 在neuedu创建 控制层（controller），接口（dao），实体类（pojo）
 #### 在neuedu创建  mapper映射包 
 #### 常见一个db.prpperties 配置文件
 #### pom.xml 导包  resoures下创建generatorConfig.xml/ logback.xml/maybatis-config.xml/spring.xml/springmvc.xml
 ## generatorConfig.xml    <!--配置mysql的驱动包jar-->   <!-- 实体类-->   <!--配置sql文件--> <!--生成Dao接口-->  <!--配置数据表（表示几个写几个）-->
 ## spring.xml <!--开启注解-->  <!--配置控制前可以返回json格式的数据-->  <!--视图解析器--> <!-- 文件上传视图解析器 --> <!-- 设置上传文件的最大尺寸为5MB -->
 ## 点开maven.projects进行自动生成
 
 ##  冗余字段：某一字段属于一个表，但它又同时出现在另一个或多个表，且完全等同于它在其本来所属表的意义表示
 ```
 
 ```
   pom.xml 导包  resoures下创建generatorConfig.xml/ logback.xml/maybatis-config.xml/spring.xml/springmvc.xml
    generatorConfig.xml    <!--配置mysql的驱动包jar-->   <!-- 实体类-->   <!--配置sql文件--> <!--生成Dao接口-->  <!--配置数据表（表示几个写几个）-->
   spring.xml <!--开启注解-->  <!--配置控制前可以返回json格式的数据-->  <!--视图解析器--> <!-- 文件上传视图解析器 --> <!-- 设置上传文件的最大尺寸为5MB -->
   点开maven.projects进行自动生成
  ```