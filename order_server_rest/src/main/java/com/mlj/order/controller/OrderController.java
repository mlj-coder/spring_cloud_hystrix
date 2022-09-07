package com.mlj.order.controller;

import com.mlj.product.entity.Product;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/order")
/**
 * 指定此接口公共的熔断方法
 *  在类上指定默认熔断方法后就不需要单独配置降级方法了
 */
@DefaultProperties(defaultFallback = "defaultFallback")
public class OrderController {


    //注入rest Template对象
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 使用注解配置熔断保护
     *      fallbackmethod:配置熔断之后的降级方法
     */
    @HystrixCommand
    @RequestMapping(value = "/buy/{id}",method = RequestMethod.GET)
    public Product findById(@PathVariable Long id){
        if(id != 1){
            throw new RuntimeException("服务器异常");
        }
        Product forObject = restTemplate.getForObject("http://service-product/product/"+id, Product.class);
        return forObject;
    }

    /**
     * 降级方法
     *  和需要收到保护的方法的返回值一致
     */
    public Product orderFallBack(Long id){
        Product forObject = new Product();
        forObject.setProductName("触发降级方法");
        return forObject;
    }

    /**
     * 默认统一降级方法
     *  没有参数
     */
    public  Product defaultFallback(){
        Product product = new Product();
        product.setProductName("触发统一降级方法");
        return product;
    }

}
