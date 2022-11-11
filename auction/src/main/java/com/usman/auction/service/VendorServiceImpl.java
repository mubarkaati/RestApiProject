package com.usman.auction.service;

import com.usman.auction.dto.request.VendorRequestDto;
import com.usman.auction.dto.response.VendorResponseDto;
import com.usman.auction.entities.Vendor;
import com.usman.auction.exceptions.ExceptionHandel;
import com.usman.auction.repository.VendorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public VendorResponseDto addVendor(VendorRequestDto requestDto) {
        if (true) {
            boolean phoneNumberStatus = vendorRepository.isPhoneNumberAvailable(requestDto.getPhoneNumber());
            if (!phoneNumberStatus) {
                System.out.println(requestDto);
                Vendor vendor = this.modelMapper.map(requestDto, Vendor.class);
                Vendor savedVendor = vendorRepository.save(vendor);

                System.out.println(savedVendor);

                VendorResponseDto responseDto = this.modelMapper.map(savedVendor, VendorResponseDto.class);
                System.out.println(responseDto);
                return responseDto;
            } else {
                ExceptionHandel.message = "phone number is already registered";
                ExceptionHandel.httpStatus = HttpStatus.NOT_ACCEPTABLE;
                return null;
            }
        } else {
            ExceptionHandel.message = "length of phone number should be equal to 10";
            ExceptionHandel.httpStatus = HttpStatus.NOT_ACCEPTABLE;
            return null;
        }
    }
}