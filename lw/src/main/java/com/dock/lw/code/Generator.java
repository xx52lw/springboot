package com.dock.lw.code;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Scanner;

/**
 * 代码生成
 *
 */
// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class Generator {

    private static final String PROJECT_PATH = System.getProperty("user.dir");// 项目在硬盘上的基础路径

    private static final String JAVA_PATH = "/src/main/java"; // java文件路径

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();

        gc = gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true); //实体属性 Swagger2 注解

        System.out.println("" + PROJECT_PATH);
        String path = PROJECT_PATH + JAVA_PATH;
        String realPath = path;
        System.out.println(realPath);
        gc.setOutputDir(realPath);
//        gc.setOutputDir("D:");
        gc.setFileOverride(false);
        gc.setActiveRecord(false);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setOpen(false);
        gc.setAuthor("liwei");
        gc.setBaseColumnList(true);
//        gc.setBaseResultMap(true);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setServiceImplName("%sServiceImpl");
        gc.setServiceName("%sService");

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://10.0.37.11:3306/ncov?characterEncoding=utf8&serverTimezone=UTC");
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("ccdcccdc");

        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.dock.lw.code");

        pc.setEntity("model");
        pc.setMapper("mapper");
        pc.setXml("mapper.xml");
        pc.setServiceImpl("service.impl");
        pc.setService("service");
        pc.setController("web");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        mpg.setCfg(cfg);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.dock.lw.common.BaseModel");
        strategy.setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper");
        strategy.setSuperServiceClass("com.baomidou.mybatisplus.extension.service.IService");
        strategy.setSuperServiceImplClass("com.dock.lw.common.BaseServiceImpl");
//        strategy.setSuperControllerClass("");

        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);


        // 写于父类中的公共字段
        // 自定义实体，公共字段
        strategy.setSuperEntityColumns(
                new String[]{"id", "create_by", "create_at", "update_by", "update_at"});

//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setInclude(new String[]{"user_report"});
        strategy.setControllerMappingHyphenStyle(true);

        mpg.setStrategy(strategy);

        mpg.execute();
    }

}
