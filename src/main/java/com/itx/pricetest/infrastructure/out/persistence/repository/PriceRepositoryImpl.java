package com.itx.pricetest.infrastructure.out.persistence.repository;

import com.itx.pricetest.domain.PriceRepository;
import com.itx.pricetest.domain.model.Price;
import com.itx.pricetest.domain.model.PriceFilter;
import com.itx.pricetest.infrastructure.out.persistence.mapper.PriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

    private final PriceDataRepository priceDataRepository;

    private final PriceMapper priceMapper;

    @Override
    public Mono<Price> findPrice(PriceFilter priceFilter) {
        return priceDataRepository
                .findTopByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDescPriceDesc(
                        priceFilter.getApplyDate(), priceFilter.getApplyDate(),
                        priceFilter.getProductId(), priceFilter.getBrandId()).flatMap(priceEntity -> Mono.just(this.priceMapper.from(priceEntity)));
    }

}