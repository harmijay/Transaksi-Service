package com.jayandra.transaksi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Configuration
public class TransaksiConfig {

    @Bean
    CommandLineRunner commandLineRunner(TransaksiRepository repository){
        return args -> {
            Transaksi tr1 = new Transaksi(
                    123,
                    3,
                    LocalDateTime.now(),
                    1,
                    "Log Transaksi"
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
