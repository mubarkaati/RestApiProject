package com.usman.auction.service;

import com.usman.auction.dto.request.AuctionRequestDto;
import com.usman.auction.dto.response.AuctionResponseDto;
import com.usman.auction.entities.Auction;
import com.usman.auction.exceptions.ExceptionHandel;
import com.usman.auction.repository.AuctionRepository;
import com.usman.auction.repository.CustomerRepository;
import com.usman.auction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public AuctionResponseDto registerBid(AuctionRequestDto requestDto) {
        if (customerRepository.findById(requestDto.getCustomerId()).isPresent()) {
            if (productRepository.findById(requestDto.getProductId()).isPresent()) {
                if (!productRepository.findById(requestDto.getProductId()).orElse(null).isSold()) {
                    if ((auctionRepository.maxAuction(productRepository.findById(requestDto.getProductId()).orElse(null))) != null) {
                        if (!((requestDto.getBidPrice() > productRepository.findById(requestDto.getProductId()).orElse(null).getBasePrice()) && (requestDto.getBidPrice() > (auctionRepository.maxAuction(productRepository.findById(requestDto.getProductId()).orElse(null)).getBidPrice())))) {
                            ExceptionHandel.message = "Bid price is less then current bid price";
                            ExceptionHandel.httpStatus = HttpStatus.NOT_ACCEPTABLE;
                            return null;
                        }
                    }
                    if (requestDto.getBidPrice() > productRepository.findById(requestDto.getProductId()).orElse(null).getBasePrice()) {
                        Auction auction = auctionRepository.findByCustomer(customerRepository.findById(requestDto.getCustomerId()).orElse(null));
                        if (auction == null) {
                            auction = new Auction();
                        }
                        auction.setBidPrice(requestDto.getBidPrice());
                        auction.setCustomer(customerRepository.findById(requestDto.getCustomerId()).orElse(null));
                        auction.setProduct(productRepository.findById(requestDto.getProductId()).orElse(null));

                        Auction savedAuction = auctionRepository.save(auction);

                        AuctionResponseDto responseDto = new AuctionResponseDto();
                        responseDto.setAuctionId(savedAuction.getAuctionId());
                        responseDto.setBidPrice(savedAuction.getBidPrice());
                        responseDto.setCustomerName(savedAuction.getCustomer().getCustomerName());
                        responseDto.setProductName(savedAuction.getProduct().getProductName());

                        return responseDto;
                    } else {
                        ExceptionHandel.message = "Bid price is less then base price";
                        ExceptionHandel.httpStatus = HttpStatus.NOT_ACCEPTABLE;
                        return null;
                    }
                } else {
                    ExceptionHandel.message = "Product is already sold";
                    ExceptionHandel.httpStatus = HttpStatus.NOT_ACCEPTABLE;
                    return null;
                }
            } else {
                ExceptionHandel.message = "Invalid ProductId";
                ExceptionHandel.httpStatus = HttpStatus.NOT_ACCEPTABLE;
                return null;
            }
        } else {
            ExceptionHandel.message = "Invalid CustomerId";
            ExceptionHandel.httpStatus = HttpStatus.NOT_ACCEPTABLE;
            return null;
        }
    }
}