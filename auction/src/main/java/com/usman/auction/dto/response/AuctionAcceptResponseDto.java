package com.usman.auction.dto.response;

import lombok.Data;

@Data
public class AuctionAcceptResponseDto {
    private String productName;
    private String customerName;
    private float soldPrice;
}