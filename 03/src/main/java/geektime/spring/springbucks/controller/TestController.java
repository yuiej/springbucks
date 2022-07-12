package geektime.spring.springbucks.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${orderlimit}")
    private String orderlimit;


    //通过更改配置中心的值，看是否可以实时拿到配置

    /**
     *
     * 2022-07-12 22:57:20.463  INFO 1048 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
     * 2022-07-12 22:57:20.961  INFO 1048 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 4004 (http) with context path ''
     * 2022-07-12 22:57:20.964  INFO 1048 --- [           main] g.s.springbucks.SpringBucksApplication   : Started SpringBucksApplication in 3.988 seconds (JVM running for 4.855)
     * 2022-07-12 22:57:20.966  INFO 1048 --- [           main] g.s.springbucks.SpringBucksApplication   : orderlimit:10001
     * 2022-07-12 22:58:09.274  INFO 1048 --- [nio-4004-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
     * 2022-07-12 22:58:09.275  INFO 1048 --- [nio-4004-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
     * 2022-07-12 22:58:09.295  INFO 1048 --- [nio-4004-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 20 ms
     * 2022-07-12 22:58:38.726  INFO 1048 --- [Apollo-Config-1] c.f.a.s.p.AutoUpdateConfigChangeListener : Auto update apollo changed value successfully, new value: 2000, key: orderlimit, beanName: springBucksApplication, field: geektime.spring.springbucks.SpringBucksApplication$$EnhancerBySpringCGLIB$$a15bc875.orderlimit
     * 2022-07-12 22:58:38.728  INFO 1048 --- [Apollo-Config-1] c.f.a.s.p.AutoUpdateConfigChangeListener : Auto update apollo changed value successfully, new value: 2000, key: orderlimit, beanName: testController, field: geektime.spring.springbucks.controller.TestController.orderlimit
     *
     *
     * @return
     */
    @GetMapping("/getApollo/orderlimit")
    public String getOrderlimitConfig(){
        return orderlimit;
    }
}
