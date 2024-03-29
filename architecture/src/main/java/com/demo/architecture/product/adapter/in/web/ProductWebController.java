package com.demo.architecture.product.adapter.in.web;

import com.demo.architecture.product.adapter.in.web.response.StartSalesProductWebRes;
import com.demo.architecture.product.application.command.StartSalesProductCommand;
import com.demo.architecture.product.application.port.in.SalesProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/web/products")
class ProductWebController {

    private final SalesProductUseCase salesProductUsecase;

    /**
     * 상품 판매 시작하기
     * @param productId
     * @return
     */
    @PostMapping("/sale/{product-id}")
    public ResponseEntity<StartSalesProductWebRes> startSalesProduct(@PathVariable(value = "product-id") Long productId) {
        salesProductUsecase.startSalesProduct(StartSalesProductCommand.builder()
                .productId(productId)
                .build());
        return ResponseEntity.ok(new StartSalesProductWebRes("[WEB] " + productId + "번 상품 판매가 시작되었습니다."));
    }
}
