package ruan.provider.config;

import lombok.SneakyThrows;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
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
public class MybatisConfig {

    @Resource
    private DataSource dataSource;


    @Bean
    @SneakyThrows
    public SqlSessionFactory sqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{
                new SqlStatementInterceptor()
        });
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources("classpath:mapper/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }
}
