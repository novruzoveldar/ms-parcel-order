package com.guavapay.service;

import com.guavapay.error.AccountNotFoundException;
import com.guavapay.model.dto.ParcelOrderDto;
import com.guavapay.model.entity.Account;
import com.guavapay.model.entity.Parcel;
import com.guavapay.model.entity.ParcelItem;
import com.guavapay.model.mapper.ParcelOrderMapper;
import com.guavapay.model.request.ParcelOrderRequest;
import com.guavapay.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParcelServiceImpl implements ParcelService {

    private final AccountRepository accountRepository;
    private final ParcelUtilService parcelUtilService;
    private final ParcelOrderMapper parcelOrderMapper;

    @Override
    @Transactional
    public ParcelOrderDto orderParcel(ParcelOrderRequest parcelOrderRequest) {
       Account account = accountRepository.findById(parcelOrderRequest.getAccountId()).orElse(null);
       if(Objects.nonNull(account)) {
           log.info("Found current user. userId {}", account.getId());
           ParcelItem parcelItem = parcelUtilService.fillParcelItem(parcelOrderRequest);
           Parcel parcel = parcelUtilService.order(account, parcelItem);
           return parcelOrderMapper.toParcelOrderDto(parcel);
       }
       throw new AccountNotFoundException("Account not found!");
    }
}
