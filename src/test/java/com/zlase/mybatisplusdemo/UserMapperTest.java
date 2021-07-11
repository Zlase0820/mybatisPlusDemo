package com.zlase.mybatisplusdemo;

import com.zlase.mybatisplusdemo.mapper.UserMapper;
import com.zlase.mybatisplusdemo.module.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询全部数据信息（基础查询）
     * database：mybatis_plus
     * 表：user
     */
    @Test
    public void contextLoads() {
        // 参数是一个Wrapper，条件构造器
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    /**
     * 尝试插入数据
     */
    @Test
    public void testInsert() {
        User user = new User();
        user.setEmail("123456@qq.com");
        user.setAge("12");
        user.setName("Zlase");
        userMapper.insert(user);

        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }


    /**
     * 尝试更新数据
     * 修改ID唯一确定之后内部的数据
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setEmail("126@qq.com");
        user.setAge("12");
        user.setName("Zzz");
        user.setId(12L);

        int result = userMapper.updateById(user);
        System.out.println(result);
    }
}





















