package com.guavapay.service;

import com.guavapay.constants.Constants;
import com.guavapay.model.entity.Account;
import com.guavapay.model.entity.Parcel;
import com.guavapay.model.entity.ParcelItem;
import com.guavapay.model.entity.Product;
import com.guavapay.model.mapper.ProductMapper;
import com.guavapay.model.request.ParcelOrderRequest;
import com.guavapay.model.type.ItemStatusCode;
import com.guavapay.model.type.ParcelState;
import com.guavapay.repository.ParcelItemRepository;
import com.guavapay.repository.ParcelProductRepository;
import com.guavapay.repository.ParcelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParcelUtilService {

    private final ProductMapper productMapper;
    private final ParcelRepository parcelRepository;
    private final ParcelItemRepository parcelItemRepository;
    private final ParcelProductRepository parcelProductRepository;

    protected Parcel order(Account account, ParcelItem parcelItem) {
        Parcel parcel = Parcel.builder()
                .account(account)
                .parcelDelivered(null)
                .parcelPlaced(new Date())
                .state(ParcelState.PENDING)
                .updateDate(new Date())
                .parcelItem(parcelItem)
                .build();
        return parcelRepository.save(parcel);
    }

    protected ParcelItem fillParcelItem(ParcelOrderRequest parcelOrderRequest) {
        List<Product> productList = productMapper.toProductEntityList(parcelOrderRequest.getProductRequestList());

        BigDecimal weight = productList.stream()
                .map(Product::getWeight)
                .reduce(BigDecimal::add)
                .get();

        ParcelItem parcelItem = parcelItemRepository.save(ParcelItem.builder()
                .itemPrice(new AtomicReference<>(weight.multiply(Constants.AMOUNT_PER_KILOGRAM)))
                .itemQuantity(productList.size())
                .itemStatusCode(ItemStatusCode.DELIVERED)
                .productList(productList)
                .build());

        productList.forEach(product -> product.setParcelItem(parcelItem));
        parcelProductRepository.saveAll(productList);
        return parcelItem;
    }
}
