package com.usman.auction.service;

import com.usman.auction.dto.request.AuctionAcceptRequestDto;
import com.usman.auction.dto.request.ProductRequestDto;
import com.usman.auction.dto.response.AuctionAcceptResponseDto;
import com.usman.auction.dto.response.ProductResponseDto;

public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto requestDto);

    AuctionAcceptResponseDto acceptBid(AuctionAcceptRequestDto requestDto);
}