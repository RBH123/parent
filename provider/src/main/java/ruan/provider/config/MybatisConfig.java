package ruan.provider.config;

import com.google.common.collect.Maps;
import java.util.Map;
import lombok.SneakyThrows;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import ruan.provider.common.DynamicDataSourceContextHolder;
import ruan.provider.common.SqlStatementInterceptor;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Author: rocky
 * Date: 2020/11/28 11:51
 * Project: parent
 * Package: ruan.provider.config
 */
@Configuration
@MapperScan(basePackages = {"ruan.provider.mapper"})
public class MybatisConfig {

    @Resource
    private JdbcConnectionPool dataSource;

    @Bean("master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master(){
        return DataSourceBuilder.create().build();
    }

    @Bean("slave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slave(){
        return DataSourceBuilder.create().build();
    }

    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource(){
        MultiDataSource multiDataSource = new MultiDataSource();
        Map<Object,Object> dataSourceMap = Maps.newHashMap();
        dataSourceMap.put("master",master());
        dataSourceMap.put("slave",slave());
        multiDataSource.setDataSource(dataSourceMap);
        multiDataSource.setDefaultDataSource(master());
        return multiDataSource;
    }


    @Bean
    @SneakyThrows
    public SqlSessionFactory sqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{
                new SqlStatementInterceptor()
        });

        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources("classpath:mapper/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
