package com.yoyo.ventas;

import java.io.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yoyo.ventas.controller.ProductController;

@SpringBootApplication
public class YoyoApplication {

	public static void main(String[] args) {
		new File(ProductController.uploadDirectory).mkdir();
		SpringApplication.run(YoyoApplication.class, args);
	}

}
