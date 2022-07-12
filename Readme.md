## 第三次作业

1. 启动配置中心

   ```
   ./demo.sh start
   ```

2. 配置中心配置，并添加测试配置值

![image-20220712230531224](/Users/medivh/Library/Application Support/typora-user-images/image-20220712230531224.png)

3. 配置

```xml
<dependency>
  <groupId>com.ctrip.framework.apollo</groupId>
  <artifactId>apollo-client</artifactId>
  <version>2.0.1</version>
</dependency>
```

```yaml
#Apollo 配置
app:
  id: springbucks-test
apollo:
  meta: http://127.0.0.1:8080
  bootstrap:
    enabled: true
    eagerLoad:
      enabled: true
```

4. 启动并测试

   ```java
   package geektime.spring.springbucks.controller;
   
   import org.springframework.beans.factory.annotation.Value;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.RestController;
   
   @RestController
   public class TestController {
   
       @Value("${orderlimit}")
       private String orderlimit;
   
   
       //通过更改配置中心的值，看是否可以实时拿到配置
       @GetMapping("/getApollo/orderlimit")
       public String getOrderlimitConfig(){
           return orderlimit;
       }
   }
   ```

   5. 测试结果

      ```
      http://localhost:4004/getApollo/orderlimit
      
      HTTP/1.1 200 
      Content-Type: text/plain;charset=UTF-8
      Content-Length: 4
      Date: Tue, 12 Jul 2022 15:01:48 GMT
      
      2000
      ```

      