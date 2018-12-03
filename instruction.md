 #配置用户名（提交时会引用）
 ## git config -- global user.name"你的用户名"
 #配置邮箱
 ##git config --global user.email "你的邮箱"
 #编码配置
 ##避免git gui中的中文乱码
 ##git config --global gui.encoding utf-8
 ##避免 git status显示的中文文件名乱码
 ##git config --global core.quotepath off
 #其他
 ##git config --global core.ignorecase false
 #git ssh key pair配置
 ##1.在git bash命令行窗口中输入：
 ##ssh-keygen -t rsa -C "你的邮箱"
 ##2.然后一路回车，不要输入任何密码之类，生成ssh key pair
 ##3.在用户目录下生成.ssh文件夹，找到公钥和私钥
 ###id_rsa id_rsa.pub
 ##4.将公钥的内容复制
 ##5.进入github网站，将公钥添加进去
 #-----------git指令-----------
 ###git init 创建本地仓库
 ###git commit -m “描述” 提交到本地仓库
 ###git status  查看
 ###git add 添加到暂存区
 ###git log 查看提交 committed
 ###git rest --hard committed 版本回退
 ###git branch 查看分支
 ###git checkout -b dev 创建并切到dev分支
 ###git checkout 分支名 切换分支
 ###git pull 拉取
 ###git push -u origin master 提交
 ###git merge branchname 合并分支
 ###git push origin HEAD -u 将本地分支送到远程分支
 ###git fetch  将远程在本地看
 ###git clone 地址名   克隆
 ###git --version 查看版本
 ###git add .  提交所有
 ###git remote add origin “地址”  关联远程仓库地址
 ###git remote remove origin   解除关联
 ###git pull origin master 把远程仓库内容拉取出来
 #-------创建文件 .gitignore-----------
 # 此为注释 – 将被 Git 忽略
 ##*.a # 忽略所有 .a 结尾的文件
 ##!lib.a # 但 lib.a 除外
 ##/TODO # 仅仅忽略项目根目录下的 TODO 文件，不包括 subdir/TODO
 ##build/ # 忽略 build/ 目录下的所有文件
 ##doc/*.txt # 会忽略 doc/notes.txt 但不包括 doc/server/arch.txt
 ##哈哈哈

 
 
 
 