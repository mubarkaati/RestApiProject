package com.usman.auction.service;

import com.usman.auction.dto.request.AuctionRequestDto;
import com.usman.auction.dto.response.AuctionResponseDto;

public interface AuctionService {
    AuctionResponseDto registerBid(AuctionRequestDto requestDto);
}