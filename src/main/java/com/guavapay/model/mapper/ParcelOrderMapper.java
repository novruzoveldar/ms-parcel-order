package com.guavapay.model.mapper;

import com.guavapay.model.dto.ParcelOrderDto;
import com.guavapay.model.entity.Parcel;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ParcelOrderMapper {

    @Mapping(target = "parcelState", source = "state")
    @Mapping(target = "placedDate", source = "parcelPlaced")
    @Mapping(target = "deliveredDate", source = "parcelDelivered")
    @Mapping(target = "itemPrice", source = "parcel.parcelItem.itemPrice", qualifiedByName = "mapPrice")
    @Mapping(target = "itemQuantity", source = "parcel.parcelItem.itemQuantity")
    @Mapping(target = "statusCode", source = "parcel.parcelItem.itemStatusCode")
    ParcelOrderDto toParcelOrderDto(Parcel parcel);

    @Named(value = "mapPrice")
    default BigDecimal mapPrice(AtomicReference<BigDecimal> itemPrice) {
        return itemPrice.get();
    }
}
