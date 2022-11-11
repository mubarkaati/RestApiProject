package com.usman.auction.dto.response;

import lombok.Data;

@Data
public class AuctionResponseDto {
    private int auctionId;
    private String productName;
    private String customerName;
    private float bidPrice;
}