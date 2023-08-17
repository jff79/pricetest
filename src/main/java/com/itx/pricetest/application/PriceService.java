package com.itx.pricetest.application;

import com.itx.pricetest.domain.model.Price;
import com.itx.pricetest.domain.model.PriceFilter;
import reactor.core.publisher.Mono;

public interface PriceService {

    Mono<Price> findPrice(PriceFilter priceFilter);

}