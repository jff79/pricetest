package com.itx.pricetest.infrastructure.in.controller.mapper;

import com.itx.pricetest.domain.model.Price;
import com.itx.pricetest.infrastructure.in.api.model.PriceResponse;
import org.mapstruct.Mapper;

@Mapper
public interface PriceResponseMapper {

    PriceResponse from(Price price);

}