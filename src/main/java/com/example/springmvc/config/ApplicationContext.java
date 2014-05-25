package com.example.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by tatsuya on 2014/05/25.
 */
@Configuration
@ComponentScan(basePackages = {"com.example.springmvc"})
@EnableWebMvc
@ImportResource("classpath:applicationContext.xml")
public class ApplicationContext {
}
