package geektime.spring.springbucks.controller;

import geektime.spring.springbucks.pojo.Order;
import geektime.spring.springbucks.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.awt.*;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @GetMapping("/json/{id}")
    public Order getOrderJsonById(@PathVariable Long id){
        Order byId = orderService.getById(id);
        log.info("order={}",byId);
        return byId;
    }

    @GetMapping(value = "/xml/{id}",produces = MediaType.APPLICATION_XML_VALUE)
    public Order getOrderXmlById(@PathVariable Long id){
        Order byId = orderService.getById(id);
        log.info("order={}",byId);
        return byId;
    }
}
