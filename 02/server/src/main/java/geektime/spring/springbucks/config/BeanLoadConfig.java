package geektime.spring.springbucks.config;

import geektime.spring.springbucks.service.OrderService;
import geektime.spring.springbucks.service.impl.OrderServiceImpl;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanLoadConfig {


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return new RestTemplate();
        return builder.build();
    }
}
