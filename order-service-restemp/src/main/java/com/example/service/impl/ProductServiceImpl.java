package com.example.service.impl;

import com.example.pojo.Product;
import com.example.service.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * 商品管理
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private RestTemplate restTemplate;

    // 声明需要服务容错的方法
    // 信号量隔离
    @HystrixCommand(fallbackMethod = "selectProductListFallback")
    /**
     * 查询商品列表
     *
     * @return
     */
    @Override
    public List<Product> selectProductList() {
        // ResponseEntity: 封装了返回数据
        return restTemplate.exchange(
                "http://product-service/product/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                }).getBody();
    }


    // 托底数据 fallbackMethod调用的
    private List<Product> selectProductListFallback() {
        System.out.println("-----selectProductListFallback-----");
        return Arrays.asList(
                new Product(1, "托底数据-res-华为手机", 1, 5800D),
                new Product(2, "托底数据-res-联想笔记本", 1, 6888D),
                new Product(3, "托底数据-res-小米平板", 5, 2020D)
        );
    }



    /**
     * 根据多个主键查询商品
     *
     * @param ids
     * @return
     */
    @Override
    public List<Product> selectProductListByIds(List<Integer> ids) {
        StringBuffer sb = new StringBuffer();
        ids.forEach(id -> sb.append("id=" + id + "&"));
        return restTemplate.getForObject("http://product-service/product/listByIds?" + sb.toString(), List.class);
    }

    /**
     * 根据主键查询商品
     *
     * @param id
     * @return
     */
    @Override
    public Product selectProductById(Integer id) {
        return restTemplate.getForObject("http://product-service/product/" + id, Product.class);
    }

}
