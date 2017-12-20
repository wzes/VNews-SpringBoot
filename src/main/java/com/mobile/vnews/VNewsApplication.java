package com.mobile.vnews;

import com.mobile.vnews.util.SmsSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VNewsApplication {

	public static void main(String[] args) {
//		SmsSender smsSender=new SmsSender();
//		try{
//			smsSender.sendMessage();
//		}catch (Exception e){
//			e.getMessage();
//		}
		SpringApplication.run(VNewsApplication.class, args);
	}
}
