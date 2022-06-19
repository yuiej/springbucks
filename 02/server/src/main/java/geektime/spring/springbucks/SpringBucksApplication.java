package geektime.spring.springbucks;

import geektime.spring.springbucks.exception.RollbackException;
import geektime.spring.springbucks.mapper.OrderMapper;
import geektime.spring.springbucks.pojo.Order;
import geektime.spring.springbucks.pojo.OrderExample;
import geektime.spring.springbucks.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ClassUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
@AllArgsConstructor
public class SpringBucksApplication implements ApplicationRunner {


	public static void main(String[] args) {
		SpringApplication.run(SpringBucksApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {

		Thread.sleep(100000);
	}



}

