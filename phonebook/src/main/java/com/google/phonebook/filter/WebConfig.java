package com.google.phonebook.filter;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    
    @Bean
    public FilterRegistrationBean<Filter> loginCheckFilter() {
      FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
      filterFilterRegistrationBean.setFilter(new LoginCheckFilter());
      filterFilterRegistrationBean.setOrder(1);
      filterFilterRegistrationBean.addUrlPatterns("/phonebook/login/*");
      return filterFilterRegistrationBean;
    }

}