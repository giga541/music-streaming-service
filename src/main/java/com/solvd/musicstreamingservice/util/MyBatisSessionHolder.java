package com.solvd.musicstreamingservice.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisSessionHolder {

    private static final SqlSessionFactory SESSION_FACTORY;

    static {
        try (InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml")) {
            SESSION_FACTORY = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize MyBatis session factory.", e);
        }
    }

    public static SqlSessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}