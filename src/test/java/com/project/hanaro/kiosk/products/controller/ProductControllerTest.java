package com.project.hanaro.kiosk.products.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.project.hanaro.kiosk.products.dto.ProductGetResponse;
import com.project.hanaro.kiosk.products.dto.ProductUpsertRequest;
import com.project.hanaro.kiosk.products.dto.ProductUpsertResponse;
import com.project.hanaro.kiosk.products.service.ProductService;
import com.project.hanaro.kiosk.products.vo.ProductOption;
import com.project.hanaro.kiosk.products.vo.ProductType;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(ProductController.class)
@MockBean(JpaMetamodelMappingContext.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @Test
    @DisplayName("Product 리스트 조회 테스트")
    void findProductList() throws Exception {
        //BDD
        //given
        ArrayList<ProductGetResponse> productList = new ArrayList<>();

        productList.add(new ProductGetResponse(1L, "plain burger","url",1000L, ProductOption.SINGLE, ProductType.BURGER));
        productList.add(new ProductGetResponse(2L, "pizza","url",2000L, ProductOption.SINGLE, ProductType.DESSERT));
        given(productService.findProducts()).willReturn(productList);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].productId").value(1L))
            .andExpect(jsonPath("$[0].name").value("plain burger"))
            .andExpect(jsonPath("$[1].productId").value(2L))
            .andExpect(jsonPath("$[1].name").value("pizza"))
            .andExpect(jsonPath("$.length()").value(2))
            .andDo(print());

        //then
        verify(productService).findProducts();
    }

    @Test
    @DisplayName("Product 데이터 생성")
    void saveProduct() throws Exception {
        ProductUpsertRequest productUpsertRequest = new ProductUpsertRequest("hamberger", "url",
            1000L, ProductOption.SINGLE, ProductType.BURGER);
        given(productService.saveProduct(productUpsertRequest))
            .willReturn(new ProductUpsertResponse(1L));

        Gson gson = new Gson();
        String content = gson.toJson(productUpsertRequest);

        mockMvc.perform(
            post("/products")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.productId").exists())
            .andDo(print());

    }
}