package com.itx.pricetest.infrastructure.out.persistence.mapper;

import com.itx.pricetest.domain.model.Price;
import com.itx.pricetest.infrastructure.out.persistence.entity.PriceEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PriceMapper {

    Price from(PriceEntity priceEntity);

}