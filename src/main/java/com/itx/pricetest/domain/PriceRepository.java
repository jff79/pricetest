package com.itx.pricetest.domain;

import com.itx.pricetest.domain.model.Price;
import com.itx.pricetest.domain.model.PriceFilter;
import reactor.core.publisher.Mono;

public interface PriceRepository {

    Mono<Price> findPrice(PriceFilter priceFilter);

}