package com.itx.pricetest.infrastructure.in.controller;

import com.itx.pricetest.infrastructure.in.api.rest.PriceControllerApi;
import com.itx.pricetest.infrastructure.in.controller.mapper.PriceRequestMapper;
import com.itx.pricetest.infrastructure.in.controller.mapper.PriceResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.itx.pricetest.infrastructure.in.api.model.PriceRequest;
import com.itx.pricetest.infrastructure.in.api.model.PriceResponse;
import com.itx.pricetest.application.PriceService;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class PriceController implements PriceControllerApi {

    private final PriceService priceService;

    private final PriceRequestMapper priceRequestMapper;
    private final PriceResponseMapper priceResponseMapper;

    @Override
    public Mono<ResponseEntity<PriceResponse>> pricesSearch(PriceRequest priceRequest, ServerWebExchange exchange) {
        return this.priceService.findPrice(this.priceRequestMapper.to(priceRequest)).flatMap(price -> Mono.just(new ResponseEntity<>(this.priceResponseMapper.from(price), HttpStatus.OK)));
    }

}