package com.usman.auction.service;

import com.usman.auction.dto.request.AuctionAcceptRequestDto;
import com.usman.auction.dto.request.ProductRequestDto;
import com.usman.auction.dto.response.AuctionAcceptResponseDto;
import com.usman.auction.dto.response.ProductResponseDto;
import com.usman.auction.entities.Auction;
import com.usman.auction.entities.Product;
import com.usman.auction.exceptions.ExceptionHandel;
import com.usman.auction.repository.AuctionRepository;
import com.usman.auction.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AuctionRepository auctionRepository;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto requestDto) {
        boolean isProductNameAvailable = productRepository.isNameAvailable(requestDto.getProductName());
        if (!isProductNameAvailable) {
            Product product = this.modelMapper.map(requestDto, Product.class);

            Product savedProduct = productRepository.save(product);

            ProductResponseDto responseDto = this.modelMapper.map(savedProduct, ProductResponseDto.class);
            return responseDto;
        } else {
            ExceptionHandel.message = "Product name is not available";
            ExceptionHandel.httpStatus = HttpStatus.NOT_ACCEPTABLE;
            return null;
        }
    }

    @Override
    public AuctionAcceptResponseDto acceptBid(AuctionAcceptRequestDto requestDto) {
        Product product = productRepository.findById(requestDto.getProductId()).orElse(null);
        if ((product != null) && (product.getVendor().getVendorId() == requestDto.getVendorId())) {
            if (!product.isSold()) {
                Auction auction = auctionRepository.maxAuction(product);
                if (auction != null) {
                    product.setSold(true);
                    product.setSoldPrice(auction.getBidPrice());

                    productRepository.save(product);

                    AuctionAcceptResponseDto responseDto = new AuctionAcceptResponseDto();
                    responseDto.setProductName(product.getProductName());
                    responseDto.setCustomerName(auction.getCustomer().getCustomerName());
                    responseDto.setSoldPrice(product.getSoldPrice());

                    return responseDto;
                } else {
                    ExceptionHandel.message = "No Bid done on this product";
                    ExceptionHandel.httpStatus = HttpStatus.NOT_ACCEPTABLE;
                    return null;
                }
            } else {
                ExceptionHandel.message = "product is already sold";
                ExceptionHandel.httpStatus = HttpStatus.NOT_ACCEPTABLE;
                return null;
            }
        } else {
            ExceptionHandel.message = "Invalid productId or vendorId";
            ExceptionHandel.httpStatus = HttpStatus.NOT_ACCEPTABLE;
            return null;
        }
    }
}