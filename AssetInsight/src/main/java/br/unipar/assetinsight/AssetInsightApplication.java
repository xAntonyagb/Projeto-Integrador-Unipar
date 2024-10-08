package br.unipar.assetinsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AssetInsightApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssetInsightApplication.class, args);
        System.out.println("BackEnd Online.");
    }

}
