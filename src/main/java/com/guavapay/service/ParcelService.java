package com.guavapay.service;

import com.guavapay.model.dto.ParcelOrderDto;
import com.guavapay.model.request.ParcelOrderRequest;

public interface ParcelService {

    ParcelOrderDto orderParcel(ParcelOrderRequest parcelOrderRequest);
}
