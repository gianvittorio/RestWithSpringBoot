package br.com.gianvittorio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.gianvittorio")
public class RestCalculadoraApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestCalculadoraApplication.class, args);
    }

}
