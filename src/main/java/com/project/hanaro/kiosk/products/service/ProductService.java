package com.project.hanaro.kiosk.products.service;

import com.project.hanaro.kiosk.members.dto.MemberLoginResponse;
import com.project.hanaro.kiosk.products.domain.Product;
import com.project.hanaro.kiosk.products.dto.ProductGetResponse;
import com.project.hanaro.kiosk.products.dto.ProductUpsertRequest;
import com.project.hanaro.kiosk.products.dto.ProductUpsertResponse;
import com.project.hanaro.kiosk.products.exception.ProductInvalidException;
import com.project.hanaro.kiosk.products.exception.ProductNotFoundException;
import com.project.hanaro.kiosk.products.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
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

    public ProductUpsertResponse saveProduct(ProductUpsertRequest request) {
        Optional<Product> product = productRepository.findByName(request.name());

        if(product.isPresent()){
            throw new ProductInvalidException();
        }

        Product savedProduct = productRepository.save(ProductUpsertRequest.toEntity(request));
        return new ProductUpsertResponse(savedProduct.getProductId());
    }

    public ProductGetResponse findProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException(productId));
        return ProductGetResponse.fromEntity(product);
    }
}