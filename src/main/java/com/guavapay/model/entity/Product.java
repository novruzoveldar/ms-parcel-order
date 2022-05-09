package com.guavapay.model.entity;

import com.guavapay.model.converter.AtomicDecimalConverter;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "seq_product", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Convert(converter = AtomicDecimalConverter.class)
    private AtomicReference<BigDecimal> price;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private BigDecimal weight;

    @Column(nullable = false)
    private String unitOfMeasurement;

    private String description;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parcel_item_id", nullable = false, updatable = false)
    private ParcelItem parcelItem;

}
