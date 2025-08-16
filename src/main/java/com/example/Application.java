package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Function<String, String> uppercase() {
        return String::toUpperCase;
    }
//
//
//    @Bean
//    public CommandLineRunner unicornCommandLineRunner(ApplicationContext applicationContext) {
//        return args -> {
//            FunctionCatalog functionCatalog = applicationContext.getBean(FunctionCatalog.class);
//            Function<String, String> uppercase = functionCatalog.lookup("uppercase");
//            String up = uppercase.apply("abc");
//            LOGGER.info("uppercase : {}", up);
//
//        };
//    }
//
//    @Bean
//    public CommandLineRunner uppercaseCommandLineRunner(ApplicationContext applicationContext) {
//        return args -> {
//            FunctionCatalog functionCatalog = applicationContext.getBean(FunctionCatalog.class);
//
//            Function<Transaction, APIGatewayProxyResponseEvent> unicornStockBrokerHandler = functionCatalog.lookup("unicornStockBrokerHandler");
//
//            Transaction transaction = new Transaction();
//            transaction.stockId = "UNICORN";
//            transaction.quantity = 100;
//            APIGatewayProxyResponseEvent event = unicornStockBrokerHandler.apply(transaction);
//            LOGGER.info("Event received: {}", event);
//
//        };
//    }

}
