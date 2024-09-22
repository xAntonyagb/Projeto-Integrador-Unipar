package br.unipar.assetinsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AssetInsightApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssetInsightApplication.class, args);
        System.out.println("BackEnd Online.");
    }

}
