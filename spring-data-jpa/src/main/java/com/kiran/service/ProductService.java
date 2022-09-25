package com.kiran.service;

import com.kiran.entity.Product;
import com.kiran.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProductById(int id) {
        return repository.findById(id).get();
    }

    public Product getProductByName(String name) {
        return repository.findByName(name);
    }

    public List<Product> getProductsByType(String productType) {
        return repository.findByProductType(productType);
    }

    public List<Product> getProductWithPriceAndType(double price, String productType){
        return repository.findByPriceAndProductType(price, productType);
    }

    public List<Product> getProductsByPrice(double price){
        return repository.getProductByPrice(price);
    }

    public Product updateProduct(int id, Product productRequest){
        //1. get the product from DB by id
        //2. update with  new value getting from request
         Product existingProduct = repository.findById(id).get(); //From DB existing product entry
         existingProduct.setName(productRequest.getName());
         existingProduct.setPrice(productRequest.getPrice());
         existingProduct.setProductType(productRequest.getProductType());
         existingProduct.setDescription(productRequest.getDescription());

         return repository.save(existingProduct);
    }

    public long deleteProduct(int id) {
        repository.deleteById(id);
        return repository.count();
    }

}
