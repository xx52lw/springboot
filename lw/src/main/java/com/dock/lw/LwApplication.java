package com.dock.lw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootConfiguration
@ServletComponentScan(basePackages = "com.dock.lw.common.filter")
@MapperScan("com.dock.lw.code.mapper")
@SpringBootApplication
public class LwApplication {

	public static void main(String[] args) {
		SpringApplication.run(LwApplication.class, args);
	}

}
