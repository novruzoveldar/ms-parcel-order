package com.guavapay.model.entity;

import com.guavapay.model.converter.AtomicDecimalConverter;
import com.guavapay.model.type.ItemStatusCode;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parcel_item")
public class ParcelItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parcel_item_seq")
    @SequenceGenerator(name = "parcel_item_seq", sequenceName = "seq_parcel_item", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "parcelItem", fetch = FetchType.LAZY, orphanRemoval = true)
    private Parcel parcel;

    @Enumerated(EnumType.STRING)
    private ItemStatusCode itemStatusCode;

    private Integer itemQuantity;

    @Column(nullable = false)
    @Convert(converter = AtomicDecimalConverter.class)
    private AtomicReference<BigDecimal> itemPrice;

    @OneToMany(mappedBy = "parcelItem", fetch = FetchType.LAZY)
    private List<Product> productList;

}
