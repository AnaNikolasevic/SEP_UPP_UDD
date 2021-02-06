package com.project.online_library;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineLibraryApplication.class, args);
    }

}
