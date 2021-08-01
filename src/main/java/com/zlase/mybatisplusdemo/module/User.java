package com.zlase.mybatisplusdemo.module;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 在连接MySQL数据库时候，要求类的名称和表的名称要一一对应
 * 表名称一般使用蛇形命名法 —— 类一般使用驼峰命名法
 * user —— User
 * user_test —— UserTest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    // 自增id  IdType.AUTO  但是要求数据库字段也配置成自增
    // 雪花算法（分布式用的较多）
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;
    private String age;
    private String email;

    // 创建时自动插入
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    // 更新文件时自动更新
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    // 乐观锁注解，需要写一个配置
    @Version
    private Integer version;

    // 逻辑删除标识符，后续再 .yaml 中进行配置即可
    @TableLogic
    private Integer deleteSign;
}
