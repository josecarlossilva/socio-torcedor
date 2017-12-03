package br.com.sociotorcedor;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableFeignClients
@EnableHystrix
@EnableMongoRepositories(basePackages = "br.com.sociotorcedor.repository")
public class ApplicationStarter {

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(ApplicationStarter.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
