package geektime.spring.springbucks.client;


import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Var;
import geektime.spring.springbucks.pojo.Order;
import org.springframework.stereotype.Component;

public interface MyClient {

    @Get("http://localhost:8080/order/json/{id}")
    Order getOrderById(@Var("id") Long id);

}
