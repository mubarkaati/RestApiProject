package com.usman.auction.controller;

import com.usman.auction.dto.request.AuctionAcceptRequestDto;
import com.usman.auction.dto.request.ProductRequestDto;
import com.usman.auction.dto.request.VendorRequestDto;
import com.usman.auction.dto.response.AuctionAcceptResponseDto;
import com.usman.auction.dto.response.ProductResponseDto;
import com.usman.auction.dto.response.VendorResponseDto;
import com.usman.auction.exceptions.ExceptionHandel;
import com.usman.auction.service.ProductService;
import com.usman.auction.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor;

import java.util.Optional;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    VendorService vendorService;

    @Autowired
    ProductService productService;

    @PostMapping("/registerVendor")
    public ResponseEntity addVendor(@RequestBody VendorRequestDto requestDto) {
//        try {
            VendorResponseDto responseDto = vendorService.addVendor(requestDto);
            if (responseDto != null)
                return new ResponseEntity(Optional.of(responseDto), HttpStatus.OK);
            else
                return new ResponseEntity(ExceptionHandel.message, ExceptionHandel.httpStatus);
//        }
//        catch (Exception exception) {
//            exception.printStackTrace();
//            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @PostMapping("/registerProduct")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto requestDto) {
        try {
            ProductResponseDto responseDto = productService.addProduct(requestDto);
            if (responseDto != null)
                return new ResponseEntity(Optional.of(responseDto), HttpStatus.OK);
            else
                return new ResponseEntity(ExceptionHandel.message, ExceptionHandel.httpStatus);
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/acceptAuction")
    public ResponseEntity acceptAuction(@RequestBody AuctionAcceptRequestDto requestDto) {
        try {
            AuctionAcceptResponseDto responseDto = productService.acceptBid(requestDto);
            if (responseDto != null)
                return new ResponseEntity(Optional.of(requestDto), HttpStatus.OK);
            else
                return new ResponseEntity(ExceptionHandel.message, ExceptionHandel.httpStatus);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}