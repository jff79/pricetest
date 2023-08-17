package com.itx.pricetest.domain.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.time.OffsetDateTime;

@Value
public class Price {

    @NotNull
    @Min(0)
    private Integer brandId;

    @NotNull
    private OffsetDateTime startDate;

    @NotNull
    private OffsetDateTime endDate;

    @NotNull
    private Integer feeId;

    @NotNull
    private Long productId;

    @NotNull
    private Double price;

}