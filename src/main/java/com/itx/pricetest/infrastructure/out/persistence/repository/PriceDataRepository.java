package com.itx.pricetest.infrastructure.out.persistence.repository;

import java.time.OffsetDateTime;

import com.itx.pricetest.infrastructure.out.persistence.entity.PriceEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PriceDataRepository extends ReactiveSortingRepository<PriceEntity, Long> {

    Mono<PriceEntity> findTopByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDescPriceDesc(OffsetDateTime startDate, OffsetDateTime endDate, Long productId, Integer brandId);

}