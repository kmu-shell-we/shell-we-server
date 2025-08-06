package com.github.kmu_shell_we;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShellWeApplication {

    public static void main(String[] args) {

        SpringApplication.run(ShellWeApplication.class, args);
    }
}
