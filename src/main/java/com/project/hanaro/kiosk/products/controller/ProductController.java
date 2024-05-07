package com.project.hanaro.kiosk.products.controller;

import com.project.hanaro.kiosk.products.dto.ProductGetResponse;
import com.project.hanaro.kiosk.products.dto.ProductUpsertRequest;
import com.project.hanaro.kiosk.products.dto.ProductUpsertResponse;
import com.project.hanaro.kiosk.products.service.ProductService;
import com.project.hanaro.kiosk.products.vo.ProductOption;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * (페이징) 상품 리스트 조회 by 상품 구분
     * @param productOption 상품 구분
     * @param pageable {int 페이지, int 사이즈}
     * @return
     */
    @GetMapping("/api/v1/products")
    public ResponseEntity<Page<ProductGetResponse>> findProductList(ProductOption productOption, Pageable pageable) {
        Page<ProductGetResponse> response = productService.findProducts(productOption, pageable);
        return ResponseEntity.ok(response);
    }

    /**
     * (페이징) 추천 상품 리스트 조회 - 가격이 낮은 순
     * @param pageable {int 페이지, int 사이즈}
     * @return
     */
    @GetMapping("/api/v1/products/suggest")
    public ResponseEntity<Page<ProductGetResponse>> findSuggestProductList(Pageable pageable) {
        Page<ProductGetResponse> response = productService.findSuggestProducts(pageable);
        return ResponseEntity.ok(response);
    }

    /**
     * 상품 저장
     * @param request
     * @return
     */
    @PostMapping("/api/v1/products")
    public ResponseEntity<ProductUpsertResponse> saveProduct(@RequestBody ProductUpsertRequest request) {
        ProductUpsertResponse response = productService.saveProduct(request);
        return ResponseEntity.ok(response);
    }

    /**
     * 상품 찾기 by Id
     * @param productId 상품 ID
     * @return
     */
    @PostMapping("/api/v1/products/{productId}")
    public ResponseEntity<ProductGetResponse> findProduct(@PathVariable Long productId) {
        ProductGetResponse response = productService.findProduct(productId);
        return ResponseEntity.ok(response);
    }

    /**
     * 상품 수정
     * @param productUpsertRequest
     * @return
     */
    @PatchMapping("/api/v1/products/{productId}")
    public ResponseEntity<ProductUpsertResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductUpsertRequest productUpsertRequest) {
        ProductUpsertResponse response = productService.updateProduct(productId, productUpsertRequest);
        return ResponseEntity.ok(response);
    }
}
