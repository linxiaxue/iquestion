package com.web.pj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@MapperScan(basePackages = {"com.web.pj.mapper"})
@SpringBootApplication()
@EnableTransactionManagement
public class PjApplication {

	public static void main(String[] args) {
		SpringApplication.run(PjApplication.class, args);
	}

}
