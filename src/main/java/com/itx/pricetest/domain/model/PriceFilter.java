package com.itx.pricetest.domain.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.time.OffsetDateTime;

@Value
public class PriceFilter {

    @NotNull
    private OffsetDateTime applyDate;

    @NotNull
    private Long productId;

    @NotNull
    @Min(0)
    private Integer brandId;

}