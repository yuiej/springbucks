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
@AllArgsConstructor
@EnableCaching(proxyTargetClass = true)
public class SpringBucksApplication implements ApplicationRunner {

	private final OrderService orderService;
	private final OrderMapper orderMapper;

	public static void main(String[] args) {
		SpringApplication.run(SpringBucksApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		generateArtifacts();
		queryTest(1L);
		log.info("redis缓存测试");
		queryTest(1L);
		log.info("开始更新");
		updateTest();
		log.info("redis缓存测试");
		queryTest(1L);
		log.info("新建测试");
		createTest();

		log.info("分页测试");
		pageTest();

		log.info("根据id批量查询测试");
		queryByIdsTest();
		log.info("删除测试");
		deleteTest();

//		Thread.sleep(100000);
	}

	private void queryTest(Long id){
		Order byId = orderService.getById(id);
		log.info("根据id={}查询订单,结果为:{}",id,byId);
	}

	private void createTest() throws RollbackException {
		Order order = Order.builder()
				.customer("中国")
				.createTime(new Date())
				.updateTime(new Date())
				.state(1)
				.build();
		log.info("全部订单个数:{}",orderMapper.selectByExample(new OrderExample()).size());
		Order param = Order.builder().customer("中国").build();
		log.info("query order param:{}",param);
		try {
			log.info("i新建订单,结果为:{}",orderService.create(order));
			log.info("根据名称={}查询订单,结果为:{}","中国",orderService.getByParam(param));
		}catch (Exception e){
			log.info("全部订单个数:{}",orderMapper.selectByExample(new OrderExample()).size());
			log.info("根据名称={}查询订单,结果为:{}","中国",orderService.getByParam(param));
		}


	}

	private void updateTest() throws RollbackException {
		Order order = Order.builder()
				.customer("张三123")
				.id(1L)
				.build();
		log.info("更新订单,结果为:{}",orderService.updateById(order));
		log.info("全部订单:{}",orderMapper.selectByExample(new OrderExample()));
	}


	private void pageTest(){
		log.info("分页查询结果:{}",orderService.listOrderByPage(2,3));
	}

	private void queryByIdsTest(){
		List<Long> ids = Arrays.asList(1L, 5L);
		log.info("根据主键批量查询结果:{}",orderService.selectByIds(ids));
	}

	private void deleteTest(){
		log.info("全部订单个数:{}",orderMapper.selectByExample(new OrderExample()).size());
		log.info("根据主键删除结果:{}",orderService.deleteById(3L));
		log.info("全部订单个数:{}",orderMapper.selectByExample(new OrderExample()).size());
	}


	private void generateArtifacts() throws Exception {
		List<String> warnings = new ArrayList<>();
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(
				this.getClass().getResourceAsStream("/generatorConfig.xml"));
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}


}

