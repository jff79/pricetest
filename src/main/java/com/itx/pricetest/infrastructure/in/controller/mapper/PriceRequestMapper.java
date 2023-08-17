package com.itx.pricetest.infrastructure.in.controller.mapper;

import com.itx.pricetest.domain.model.PriceFilter;
import com.itx.pricetest.infrastructure.in.api.model.PriceRequest;
import org.mapstruct.Mapper;

@Mapper
public interface PriceRequestMapper {

    PriceFilter to(PriceRequest priceRequest);

}