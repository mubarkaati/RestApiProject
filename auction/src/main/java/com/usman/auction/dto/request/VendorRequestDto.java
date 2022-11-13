package com.usman.auction.dto.request;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class VendorRequestDto {
    private String vendorName;
    private Long phoneNumber;
}