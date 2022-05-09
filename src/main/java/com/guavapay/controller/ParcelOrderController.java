package com.guavapay.controller;

import com.guavapay.model.dto.ParcelOrderDto;
import com.guavapay.model.request.ParcelOrderRequest;
import com.guavapay.service.ParcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("parcel")
public class ParcelOrderController {

    private final ParcelService parcelService;

    @PostMapping(value = "/order", consumes = {"application/json"}, produces = {"application/json"})
    public ParcelOrderDto createParcelOrder(@Valid @RequestBody ParcelOrderRequest parcelOrderRequest) {
        return parcelService.orderParcel(parcelOrderRequest);
    }
}
