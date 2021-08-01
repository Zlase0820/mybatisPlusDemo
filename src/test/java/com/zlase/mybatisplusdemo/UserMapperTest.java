package com.zlase.mybatisplusdemo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlase.mybatisplusdemo.mapper.UserMapper;
import com.zlase.mybatisplusdemo.module.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
@Slf4j
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
    }


    /**
     * 尝试更新数据
     * 修改ID唯一确定之后内部的数据
     */
    @Test
    public void testUpdate() {
        User user = userMapper.selectById(1L);
        user.setEmail("126@qq.com");
        user.setAge("12");
        user.setName("Zzz");
        user.setId(12L);

        int result = userMapper.updateById(user);
        System.out.println(result);
    }

    /**
     * 查询介绍：批量查询
     */
    @Test
    public void testSelectByBatchId(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1,2,3));
        users.forEach(System.out::println);
    }

    /**
     * 自定义查询：根据特定的条件进行查询
     */
    @Test
    public void testSelectByBatchId2(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("name","Zlase");
        map.put("age",14);

        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    /**
     * 自定义查询：分页查询
     * 需要先引入分页查询配置
     */
    @Test
    public void testSelectPage(){
        // current: 当前页    size：每页的大小
        Page<User> page = new Page<>(2,3);
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
    }

    /**
     * 基本的删除操作：单个删除，批量删除，查询删除
     */
    public void testDelete(){
        userMapper.deleteById(1L);

        userMapper.deleteBatchIds(Arrays.asList(10,20,30));

        HashMap<String,Object> map = new HashMap<>();
        map.put("name","zzzz");
        userMapper.deleteByMap(map);
    }

    /**
     * 进行逻辑删除测试
     */
    @Test
    public void testLogicDelete(){
        int result = userMapper.deleteById(1L);
    }
}


