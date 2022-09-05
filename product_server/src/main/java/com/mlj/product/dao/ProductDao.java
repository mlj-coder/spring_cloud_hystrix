package com.mlj.product.dao;

import com.mlj.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * spring-data-jpa框架
 */
public interface ProductDao extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}
