package com.kodality.travellog.liquibase;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.inject.Singleton;
import javax.sql.DataSource;

@Factory
public class DataSourceFactory {
    @Singleton
    public DataSource dataSource(@Value("jdbc:postgresql://localhost:5432/postgres") String url,
                                 @Value("org.postgresql.Driver") String driverClassName,
                                 @Value("postgres") String username,
                                 @Value("thisispassword") String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }
}
