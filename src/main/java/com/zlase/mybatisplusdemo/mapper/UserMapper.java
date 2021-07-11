package com.zlase.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlase.mybatisplusdemo.module.User;
import org.springframework.stereotype.Repository;

// 在对应的Mapper上继承基本类BaseMapper即可

@Repository   // 持久层
public interface UserMapper extends BaseMapper<User> {
    // 基础的CRUD操作已经编写完成
    // 已经继承了BaseMapper因此所有的类都来自于BaseMapper的扩展方法
}
