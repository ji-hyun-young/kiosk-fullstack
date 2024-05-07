package com.project.hanaro.kiosk.products.service;

import com.project.hanaro.kiosk.members.dto.MemberLoginResponse;
import com.project.hanaro.kiosk.products.domain.Product;
import com.project.hanaro.kiosk.products.dto.ProductGetResponse;
import com.project.hanaro.kiosk.products.dto.ProductUpsertRequest;
import com.project.hanaro.kiosk.products.dto.ProductUpsertResponse;
import com.project.hanaro.kiosk.products.exception.ProductInvalidException;
import com.project.hanaro.kiosk.products.exception.ProductNotFoundException;
import com.project.hanaro.kiosk.products.repository.ProductRepository;
import com.project.hanaro.kiosk.products.vo.ProductOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<ProductGetResponse> findProducts(ProductOption productOption, Pageable pageable){
        Page<Product> products = productRepository.getPageByProductOption(productOption, pageable);
        List<ProductGetResponse> productList = products.stream()
            .map(ProductGetResponse::fromEntity).collect(Collectors.toList());

        return new PageImpl<>(productList, pageable, products.getTotalElements());
    }

    @Transactional
    public ProductUpsertResponse saveProduct(ProductUpsertRequest request) {
        Optional<Product> product = productRepository.findByName(request.name().get());

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

    public Page<ProductGetResponse> findSuggestProducts(Pageable pageable) {
        Page<Product> products = productRepository.getSuggestProductsPage(pageable);
        List<ProductGetResponse> productList = products.stream()
            .map(ProductGetResponse::fromEntity).collect(Collectors.toList());

        return new PageImpl<>(productList, pageable, products.getTotalElements());
    }

    @Transactional
    public ProductUpsertResponse updateProduct(Long productId, ProductUpsertRequest productUpsertRequest) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ProductNotFoundException(productId));
        Product savedProduct = productUpsertRequest.updateEntity(product);
        return ProductUpsertResponse.fromEntity(savedProduct);
    }
}