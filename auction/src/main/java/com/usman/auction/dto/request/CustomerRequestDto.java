package com.usman.auction.dto.request;

import lombok.Data;

@Data
public class CustomerRequestDto {
    private String customerName;
    private Long phoneNumber;

    @Override
    public String toString() {
        return "CustomerRequestDto{" +
                "customerName='" + customerName + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}