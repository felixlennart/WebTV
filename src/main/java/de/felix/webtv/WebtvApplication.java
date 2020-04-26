package de.felix.webtv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.awt.*;
import java.io.IOException;

@SpringBootApplication
public class WebtvApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebtvApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WebtvApplication.class, args);
        RaspberryPiConnector connector = new RaspberryPiConnector();
        try {
            connector.openOnly("http://localhost:8080/welcome");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
