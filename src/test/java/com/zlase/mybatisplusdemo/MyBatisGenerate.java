package com.zlase.mybatisplusdemo;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.h2.value.DataType;

import javax.swing.table.TableRowSorter;
import java.util.ArrayList;

public class MyBatisGenerate {

    public static void main(String[] args) {
        // 构建一个代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置，注意：别导入错误的包
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("zla");
        gc.setOpen(false);  // 是否打开资源管理器
        gc.setFileOverride(false); // 是否进行覆盖
        gc.setServiceName("%sService"); // 去除Service的I前缀
        gc.setIdType(IdType.ID_WORKER);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatis_plus?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2b8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.zlase.mybatisplusdemo");
        pc.setModuleName("blog");   // 最外层的名称
        pc.setEntity("entity");     // entity模块
        pc.setMapper("mapper");     // mapper模块
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("user");  // 需要生成信息的表名称
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);  // 下划线转驼峰
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);  // 自动生成lombok
        strategyConfig.setLogicDeleteFieldName("deleteSign");  // 设计字段

        // 设置自动填充配置
        TableFill gmt_create = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmt_modified = new TableFill("gmt_modified", FieldFill.UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmt_create);
        tableFills.add(gmt_modified);
        strategyConfig.setTableFillList(tableFills);

        // 乐观锁
        strategyConfig.setVersionFieldName("version");

        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategyConfig);

        // 执行该策略
        mpg.execute();
    }
}
