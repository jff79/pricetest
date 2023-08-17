package com.itx.pricetest.application;

import com.itx.pricetest.domain.PriceRepository;
import com.itx.pricetest.domain.model.Price;
import com.itx.pricetest.domain.model.PriceFilter;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public Mono<Price> findPrice(PriceFilter priceFilter) {
        return this.priceRepository.findPrice(priceFilter);
    }

}