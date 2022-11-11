package com.usman.auction.service;

import com.usman.auction.dto.request.VendorRequestDto;
import com.usman.auction.dto.response.VendorResponseDto;

public interface VendorService {
    VendorResponseDto addVendor(VendorRequestDto requestDto);
}