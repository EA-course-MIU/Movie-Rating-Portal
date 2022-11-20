package com.example.service;

import com.example.entity.Product;
import com.example.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepo productRepo;
    @Override
    public Iterable<Product> getAll() {
        return productRepo.findAll();
    }
}
