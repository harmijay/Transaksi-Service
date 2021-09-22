package com.jayandra.transaksi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class TransaksiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransaksiApplication.class, args);
	}
}
