package com.project.hanaro.kiosk.products.service;

import com.project.hanaro.kiosk.products.domain.Product;
import com.project.hanaro.kiosk.products.dto.ProductGetResponse;
import com.project.hanaro.kiosk.products.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductGetResponse> findProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream()
            .map(ProductGetResponse::fromEntity).collect(Collectors.toList());
    }
}
