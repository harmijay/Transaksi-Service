package com.jayandra.transaksi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Configuration
public class TransaksiConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancer(){
        return WebClient.builder();
    }

    @Bean
    CommandLineRunner commandLineRunner(TransaksiRepository repository){
        return args -> {
            Transaksi tr1 = new Transaksi(
                    123,
                    3,
                    LocalDateTime.now(),
                    1,
                    "Log Transaksi 1"
            );

            Transaksi tr2 = new Transaksi(
                    123,
                    3,
                    LocalDateTime.of(2021, Month.SEPTEMBER, 28, 11, 57, 00),
                    1,
                    "Log Transaksi"
            );

            repository.saveAll(
                    List.of(tr1,tr2)
            );
        };
    }
}
