package ruan.gateway.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import ruan.gateway.common.TypeConvert;

@Slf4j
public class CodeGenerate {

    static String JAVA_PATH = "\\src\\main\\java";
    static String RESOURCES_PATH = "\\src\\main\\resources";
    static String CONTROLLER = "\\controller";
    static String SERVICE = "\\service";
    static String MAPPER = "\\mapper";
    static String SERVICE_IMPL = "\\impl";
    static String ENTITY = "\\entity";
    static String AO = "\\pojo\\ao";
    static String VO = "\\pojo\\vo";

    public static void main(String[] args){
        String property = System.getProperty("user.dir")+"\\gateway";
        String projectPath = "\\ruan\\gateway";
        String[] tableList = new String[]{"users"};
        generate(property,projectPath,tableList);
    }

    public static void generate(String property, String projectPath, String[] tableNames) {
        AutoGenerator autoGenerator = new AutoGenerator();
        //全局配置
        GlobalConfig config = new GlobalConfig();
        config.setAuthor("ruan");
        config.setBaseResultMap(true);
        config.setDateType(DateType.SQL_PACK);
        config.setControllerName("%sController");
        config.setServiceName("%sService");
        config.setServiceImplName("%sServiceImpl");
        config.setMapperName("%sDao");
        config.setActiveRecord(true);
        config.setBaseColumnList(true);
        config.setEnableCache(false);
        config.setFileOverride(true);
        config.setXmlName("%sMapper");
        config.setOutputDir(property.concat(JAVA_PATH));
        config.setIdType(IdType.INPUT);
        config.setOpen(false);
        autoGenerator.setGlobalConfig(config);
        //数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.alibaba.druid.proxy.DruidDriver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("217024");
        dataSourceConfig.setUrl("jdbc:mysql://192.168.17.14:3306/server?useUnicode=true&characterEncoding=utf8&useSSL=false&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai");
        dataSourceConfig.setTypeConvert(new TypeConvert());
        autoGenerator.setDataSource(dataSourceConfig);
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setEnableSqlFilter(true);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setEntitySerialVersionUID(true);
        strategyConfig.setEntityTableFieldAnnotationEnable(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setLogicDeleteFieldName("is_deleted");
        strategyConfig.setInclude(tableNames);
//        strategyConfig.setTablePrefix("t_sy_");
        autoGenerator.setStrategy(strategyConfig);
        PackageConfig packageConfig = new PackageConfig();
        Map<String, String> pathInfo = new HashMap<>();
        String javaPath = property.concat(JAVA_PATH).concat(projectPath);
        pathInfo.put(ConstVal.XML_PATH, property.concat(RESOURCES_PATH).concat(MAPPER));
        pathInfo.put(ConstVal.CONTROLLER_PATH, javaPath.concat(CONTROLLER));
        pathInfo.put(ConstVal.SERVICE_PATH, javaPath.concat(SERVICE));
        pathInfo.put(ConstVal.MAPPER_PATH, javaPath.concat(MAPPER));
        pathInfo.put(ConstVal.SERVICE_IMPL_PATH, javaPath.concat(SERVICE).concat("\\" + SERVICE_IMPL));
        pathInfo.put(ConstVal.ENTITY_PATH, javaPath.concat(ENTITY));
        packageConfig.setParent("ruan.gateway");
        packageConfig.setPathInfo(pathInfo);
        autoGenerator.setPackageInfo(packageConfig);
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setMapper("/templates/mapper.java");
        autoGenerator.setTemplate(templateConfig);
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                log.info("mybatius-plusl自定义代码输出！");
            }
        };
        List<FileOutConfig> fileOutConfigList = new ArrayList<>();
        fileOutConfigList.add(new FileOutConfig("/templates/Ao.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String name = tableInfo.getEntityName();
                name = name.concat("Ao.java");
                return javaPath.concat(AO).concat("\\").concat(name);
            }
        });
        fileOutConfigList.add(new FileOutConfig("/templates/Vo.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return javaPath.concat(VO).concat("\\").concat(tableInfo.getEntityName()).concat("Vo.java");
            }
        });
        injectionConfig.setFileOutConfigList(fileOutConfigList);
        autoGenerator.setCfg(injectionConfig);
        autoGenerator.execute();
    }
}
