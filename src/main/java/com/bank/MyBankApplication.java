package com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static com.google.common.base.Predicates.not;
import static com.google.common.collect.Sets.newHashSet;

/**
 * Created by ugi on 3/16/2018.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.bank"})
public class MyBankApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MyBankApplication.class, args);
    }


    @Bean
    public Docket swaggerForCustomers() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("external")
                .apiInfo(
                        new ApiInfoBuilder()
                                .title("My Bank application")
                                .description("My bank application provides denominationof amounts")
                                .termsOfServiceUrl("http://gilakathula.dk")
                                .version("0.1")
                                .build()
                )
                .protocols(newHashSet("http"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bank"))
                .paths(not(PathSelectors.regex("/error*")))
                .build();
    }
}
