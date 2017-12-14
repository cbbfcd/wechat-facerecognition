package com.example.demo;

import com.example.demo.schedule.WeChatGetTokenSchedule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScreenshotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScreenshotApplication.class, args);
		try {
			new WeChatGetTokenSchedule().getWechatTokenTask();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
