package com.mlj.order.feign;

import com.mlj.product.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignClientCallBack implements ProductFeignClient{
    @Override
    public Product findById(long id) {
        Product product = new Product();
        product.setProductName("feign支持熔断降级的方法");
        return product;
    }
}
