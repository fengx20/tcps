package com.gxuwz.fx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.gxuwz.fx"})
@EnableScheduling
@EnableCaching
public class Application extends SpringBootServletInitializer {
	
	/**
	  * 使用外部Tomcat
	  */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
      return application.sources(this.getClass());
  }
	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	
	

}
