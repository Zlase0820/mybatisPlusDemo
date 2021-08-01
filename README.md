# MyBatisPlus笔记



## 环境搭建

### 前置工作

[搭建MySQL环境](https://blog.csdn.net/zhusongziye/article/details/80869125)

[安装Navicat](https://www.jb51.net/article/199525.htm)

[MyBatisPlus官方文档](https://baomidou.com/guide/#%E7%89%B9%E6%80%A7)

[学习资料1](https://www.bilibili.com/video/BV1Ds411E76Y?from=search&seid=5268324907552745421)

[学习资料2](https://www.bilibili.com/video/BV17E411N7KN?p=3&spm_id_from=pageDriver)



### 数据库初始化

```
-- 创建数据库
CREATE DATABASE mybatis_plus;

-- 使用数据库
USE mybatis_plus;

-- 创建数据库表
DROP TABLE IF EXISTS USER;
CREATE TABLE USER
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	NAME VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
);

-- 插入测试数据
DELETE FROM USER;
INSERT INTO USER (id, NAME, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');

```

![初始数据库信息](pic/初始数据库信息.png)  


### 数据库变更配置
1. 修改数据库主键id为自增   
```
ALTER TABLE user
MODIFY COLUMN id BIGINT(30) NOT NULL AUTO_INCREMENT COMMENT '主键id' FIRST;
```  

2. 创建两个新字段：创建时间、修改时间   
```  
ALTER TABLE user
ADD COLUMN `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间';
```  

```
ALTER TABLE user
ADD COLUMN `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间';
```  

3. 创建新字段：逻辑删除标识符  delete  
```
ALTER TABLE user
ADD COLUMN `delete` INT(10) NOT NULL DEFAULT '0' COMMENT '逻辑删除';
```

### CRUD操作
1. 查询  
2. 增加  
3. 删除  

### 性能分析插件  
分析每条sql执行的时间  
会对sql的执行命令的时间进行限制，当sql执行的时间超过规定的时间，就会进行拦截并抛出异常  


### 条件构造器  
基于各种条件，来完成数据库中的信息查询   

### 代码自动填充配置  
使用方式：  
引入一个依赖包，然后在一个类中创建对应的信息，将所有的信息都填充到该类中，通过执行这个类，在工程中生成对应的代码。  

该类可以通过写入sql命令，完成数据库信息的增删改查。  
也可以通过特定的指令，基于数据库的类型，生成对应的mapper，module等结构和代码。   






























