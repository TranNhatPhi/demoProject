package com.r2sCompany.exampleProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        System.out.println("test demo application");
        SpringApplication.run(DemoApplication.class, args);

    }

}
