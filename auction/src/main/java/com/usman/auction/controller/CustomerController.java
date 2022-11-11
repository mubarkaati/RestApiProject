package com.usman.auction.controller;

import com.usman.auction.dto.request.AuctionRequestDto;
import com.usman.auction.dto.request.CustomerRequestDto;
import com.usman.auction.dto.response.AuctionResponseDto;
import com.usman.auction.dto.response.CustomerResponseDto;
import com.usman.auction.exceptions.ExceptionHandel;
import com.usman.auction.service.AuctionService;
import com.usman.auction.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    AuctionService auctionService;

    @PostMapping("/register")
    public ResponseEntity registerCustomer(@RequestBody CustomerRequestDto requestDto) {
        try {
            CustomerResponseDto responseDto = customerService.registerCustomer(requestDto);
            if (responseDto != null)
                return new ResponseEntity(Optional.of(responseDto), HttpStatus.OK);
            else
                return new ResponseEntity(ExceptionHandel.message, ExceptionHandel.httpStatus);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/registerBid")
    public ResponseEntity registerBid(@RequestBody AuctionRequestDto requestDto) {
        try {
            AuctionResponseDto responseDto = auctionService.registerBid(requestDto);
            if (responseDto != null)
                return new ResponseEntity(Optional.of(responseDto), HttpStatus.OK);
            else
                return new ResponseEntity(ExceptionHandel.message, ExceptionHandel.httpStatus);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}