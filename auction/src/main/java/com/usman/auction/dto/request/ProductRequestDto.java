package com.usman.auction.dto.request;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String productName;
    private float basePrice;
    private int vendorId;
}