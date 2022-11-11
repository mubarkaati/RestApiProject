package com.usman.auction.dto.request;

import lombok.Data;

@Data
public class AuctionAcceptRequestDto {
    private int productId;
    private int vendorId;
}