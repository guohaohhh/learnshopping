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
 
 
 
 