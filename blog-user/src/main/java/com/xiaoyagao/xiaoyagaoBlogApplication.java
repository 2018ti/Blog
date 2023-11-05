package com.xiaoyagao;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiaoyagao.mapper")
public class xiaoyagaoBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(xiaoyagaoBlogApplication.class,args);
    }
}
