package com.guavapay.model.mapper;

import com.guavapay.constants.Constants;
import com.guavapay.model.dto.Measurement;
import com.guavapay.model.entity.Product;
import com.guavapay.model.request.ParcelOrderRequest;
import com.guavapay.model.type.MeasurementUnit;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
imports = Constants.class)
public interface ProductMapper {

    @Mapping(target = "name", source = "productName")
    @Mapping(target = "price", source = "productPrice", qualifiedByName = "mapPrice")
    @Mapping(target = "color", source = "productColor")
    @Mapping(target = "weight", source = "measurement.value")
    @Mapping(target = "unitOfMeasurement", source = "measurement", qualifiedByName = "mapUnit")
    @Mapping(target = "description", source = "productDescription")
    Product toProductEntity(ParcelOrderRequest.ProductRequest productRequest);

    List<Product> toProductEntityList(List<ParcelOrderRequest.ProductRequest> productRequestList);

    @Named(value = "mapPrice")
    default AtomicReference<BigDecimal> mapPrice(String productPrice) {
        return new AtomicReference<>(new BigDecimal(productPrice).divide(BigDecimal.valueOf(Constants.PRICE_SCALE_DIVIDER)));
    }

    @Named(value = "mapUnit")
    default String mapUnit(Measurement measurement) {
        return MeasurementUnit.of(measurement.getUnit());
    }
}
