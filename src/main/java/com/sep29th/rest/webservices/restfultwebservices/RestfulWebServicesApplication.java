package com.sep29th.rest.webservices.restfultwebservices;

import com.sep29th.rest.webservices.restfultwebservices.aws.S3Buckets;
import com.sep29th.rest.webservices.restfultwebservices.aws.S3Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestfulWebServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulWebServicesApplication.class, args);
    }

//    @Bean
//    CommandLineRunner runner(S3Service s3Service, S3Buckets s3Buckets) {
//        return args -> {
//            s3Service.putObject(s3Buckets.getName(), "user/images/sep29th", "HelloWord".getBytes());
//            System.out.println(new String(s3Service.getObject(s3Buckets.getName(), "user/images/sep29th")));
//        };
//    }

}
