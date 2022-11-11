package com.usman.auction.service;

import com.usman.auction.dto.request.CustomerRequestDto;
import com.usman.auction.dto.response.CustomerResponseDto;
import com.usman.auction.entities.Customer;
import com.usman.auction.exceptions.ExceptionHandel;
import com.usman.auction.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CustomerResponseDto registerCustomer(CustomerRequestDto requestDto) {
        if (true) {
            boolean phoneNumberStatus = customerRepository.isPhoneNumberAvailable(requestDto.getPhoneNumber());
            System.out.println(phoneNumberStatus);
            if (!phoneNumberStatus) {
                System.out.println(phoneNumberStatus);
                System.out.println(requestDto);
                Customer customer = this.modelMapper.map(requestDto, Customer.class);

                Customer savedCustomer = customerRepository.save(customer);

                return this.modelMapper.map(savedCustomer, CustomerResponseDto.class);
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