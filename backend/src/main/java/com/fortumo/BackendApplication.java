package com.fortumo;

import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import com.fortumo.configuration.SchedulerConfig;

@SpringBootApplication
@Import({ SchedulerConfig.class })
public class BackendApplication {

	public static void main(String[] args) throws SchedulerException {
		SpringApplication.run(BackendApplication.class, args);
	
	}
}
