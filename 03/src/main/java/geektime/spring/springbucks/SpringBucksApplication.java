package geektime.spring.springbucks;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import geektime.spring.springbucks.exception.RollbackException;
import geektime.spring.springbucks.mapper.OrderMapper;
import geektime.spring.springbucks.pojo.Order;
import geektime.spring.springbucks.pojo.OrderExample;
import geektime.spring.springbucks.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.*;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
@RequiredArgsConstructor
@EnableCaching(proxyTargetClass = true)
public class SpringBucksApplication implements ApplicationRunner {

	private final OrderService orderService;
	private final OrderMapper orderMapper;

	@Value("${orderlimit}")
	private String orderlimit;


	public static void main(String[] args) {
		SpringApplication.run(SpringBucksApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		//从配置中心获取配置的值
		log.info("orderlimit:{}",orderlimit);
	}

}

