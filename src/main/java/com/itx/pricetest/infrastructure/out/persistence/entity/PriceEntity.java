package com.itx.pricetest.infrastructure.out.persistence.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import lombok.Value;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Table(name = "prices")
@Value
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BRAND_ID", nullable = false)
    private Integer brandId;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE", nullable = false)
    private OffsetDateTime startDate;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE", nullable = false)
    private OffsetDateTime endDate;

    @Column(name = "FEE_ID", nullable = false)
    private Integer feeId;

    @Column(name = "PRODUCT_ID", nullable = false)
    private Long productId;

    @Column(name = "PRIORITY", nullable = false)
    private Integer priority;

    @Column(name = "PRICE", nullable = false, scale = 2)
    private Double price;

    @Size(min = 3, max = 3)
    @Column(name = "CURR", nullable = false, length = 3)
    private String curr;

}