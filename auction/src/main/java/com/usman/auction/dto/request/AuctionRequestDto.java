package com.usman.auction.dto.request;

import lombok.Data;

@Data
public class AuctionRequestDto {
    private float bidPrice;
    private int productId;
    private int customerId;
}