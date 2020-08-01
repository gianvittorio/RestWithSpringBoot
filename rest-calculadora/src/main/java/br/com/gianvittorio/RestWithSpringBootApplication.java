package br.com.gianvittorio;

import br.com.gianvittorio.config.FileStorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties({FileStorageConfig.class})
@ComponentScan("br.com.gianvittorio")
public class RestWithSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestWithSpringBootApplication.class, args);

//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
//        String result = bCryptPasswordEncoder.encode("admin123");
//        System.out.printf("My hash: " + result);
    }

}
