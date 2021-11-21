package com.example.service;

import com.example.pojo.Product;

import java.util.List;

/**
 * 商品管理
 */
public interface ProductService {

    /**
     * 查询商品列表
     *
     * @return
     */
    List<Product> selectProductList();

    /**
     * 根据多个主键查询商品
     *
     * @param ids
     * @return
     */
    List<Product> selectProductListByIds(List<Integer> ids);

    /**
     * 根据主键查询商品
     *
     * @param id
     * @return
     */
    Product selectProductById(Integer id);

}
