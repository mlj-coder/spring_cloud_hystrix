package com.mlj.product.controller;

import com.mlj.product.entity.Product;
import com.mlj.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Value("${server.port}")
    private String prot;
    @Value("${spring.cloud.client.ip-address}")
    private String ip;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Product findById(@PathVariable Long id){
        Product byId = productService.findById(id);
        try {
            Thread.sleep(2000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
        byId.setProductName("请求的地址："+ip+",请求的端口："+prot);
        return byId;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestBody Product product){
        productService.save(product);
        return "保存成功";
    }

}
