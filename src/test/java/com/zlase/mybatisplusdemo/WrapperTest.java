package com.zlase.mybatisplusdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlase.mybatisplusdemo.mapper.UserMapper;
import com.zlase.mybatisplusdemo.module.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 条件构造器：
     * 查询name不为空的用户，并且邮箱不为空的用户，年龄大于等于12
     */
    @Test
    public void contextLoads() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .isNotNull("name")
                .isNotNull("email")
                .ge("age", 14);
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }

    /**
     * 单条进行查询，selectOne如果有多个重名，就会出错
     */
    @Test
    public void queryOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Sandy");
        User user = userMapper.selectOne(queryWrapper);
    }

    /**
     * 查询一个范围内的值：查询年龄在20-30岁之前的人的数量
     */
    @Test
    public void queryBetween() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("age", 20, 30);
        Integer number = userMapper.selectCount(queryWrapper);
        log.info("query between 20 and 30 is : " + number);
    }

    /**
     * 模糊查询：name中不带有e，右侧开头以t为开头的
     * 可以通过正则进行判断和查询
     */
    @Test
    public void queryFuzzy() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .notLike("name", "e")
                .likeRight("email", "t");

        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
    }

    /**
     * 多查询：多个SQL命令放到一起进行查询，可以嵌套多个sql命令，可以做连表
     */
    @Test
    public void queryChain() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id", "select id from user where id < 3");

        List<Object> objects = userMapper.selectObjs(queryWrapper);
        objects.forEach(System.out::println);
    }
}
