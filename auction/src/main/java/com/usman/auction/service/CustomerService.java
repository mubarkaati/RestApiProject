package com.usman.auction.service;

import com.usman.auction.dto.request.CustomerRequestDto;
import com.usman.auction.dto.request.VendorRequestDto;
import com.usman.auction.dto.response.CustomerResponseDto;
import com.usman.auction.dto.response.VendorResponseDto;

public interface CustomerService {
    CustomerResponseDto registerCustomer(CustomerRequestDto requestDto);
}