package geektime.spring.springbucks;

import geektime.spring.springbucks.client.MyClient;
import geektime.spring.springbucks.pojo.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
@AllArgsConstructor
public class SpringBucksApplication implements ApplicationRunner {

	@Autowired
	private RestTemplate restTemplate;

	@Resource
	private MyClient client;

	public static void main(String[] args) {
		new SpringApplicationBuilder()
				.sources(SpringBucksApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.web(WebApplicationType.NONE)
				.run(args);
	}



	@Override
	public void run(ApplicationArguments args) {
		requestByRestTemplate();
		requestByOkhttp3();
	}


	private void requestByRestTemplate(){
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8080/order/json/{id}")
				.build(1);
		ResponseEntity<Order> c = restTemplate.getForEntity(uri, Order.class);
		log.info("Response Status: {}, Response Headers: {}", c.getStatusCode(), c.getHeaders().toString());
		log.info("Order: {}", c.getBody());
	}


	private void requestByOkhttp3(){
		// forest 默认为Okhttp3 api
		Order orderById = client.getOrderById(2L);
		log.info("Order: {}", orderById);
	}

}

