package com.usman.restaurantBooking.controller;

import com.usman.restaurantBooking.model.dto.request.BillPayRequestDto;
import com.usman.restaurantBooking.model.dto.request.OrderBillRequestDto;
import com.usman.restaurantBooking.model.dto.request.OrderRequestDto;
import com.usman.restaurantBooking.model.dto.request.TableBookingRequestDto;
import com.usman.restaurantBooking.model.dto.response.BillPayResponseDto;
import com.usman.restaurantBooking.model.dto.response.OrderBillResponseDto;
import com.usman.restaurantBooking.model.dto.response.OrderResponseDto;
import com.usman.restaurantBooking.model.dto.response.TableBookingResponseDto;
import com.usman.restaurantBooking.service.OrderBillService;
import com.usman.restaurantBooking.service.OrderService;
import com.usman.restaurantBooking.service.TableBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    TableBookingService tableBookingService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderBillService orderBillService;

    @PostMapping("/bookTable")
    public ResponseEntity addBooking(@RequestBody TableBookingRequestDto bookingRequestDto) {
        try {
            TableBookingResponseDto bookingResponseDto = tableBookingService.addTableBooking(bookingRequestDto);
            if (bookingResponseDto != null)
                return new ResponseEntity(Optional.of(bookingResponseDto), HttpStatus.OK);
            else
                return new ResponseEntity("No table available for given number of persons", HttpStatus.NOT_ACCEPTABLE);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/giveOrder")
    public ResponseEntity giveOrder(@RequestBody OrderRequestDto requestDto) {
        try {
            OrderResponseDto responseDto = orderService.addOrder(requestDto);
            if (responseDto != null)
                return new ResponseEntity(Optional.of(responseDto), HttpStatus.OK);
            else
                return new ResponseEntity("cannot create order due to following reasons: invalid table bookingId, table is not free for this bookingId, booking is expired or order must have at-least 1 item", HttpStatus.NOT_ACCEPTABLE);
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/generateBill")
    public ResponseEntity generateBill(@RequestBody OrderBillRequestDto billRequestDto) {
        try {
            OrderBillResponseDto billResponseDto = orderBillService.generateBill(billRequestDto);
            if (billResponseDto != null)
                return new ResponseEntity(Optional.of(billResponseDto), HttpStatus.OK);
            else
                return new ResponseEntity("cannot generate bill due to following reasons: bill is already generated, invalid table id or table does not have any order", HttpStatus.NO_CONTENT);
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/payBill")
    public ResponseEntity payBill(@RequestBody BillPayRequestDto billPayRequestDto) {
        try {
            BillPayResponseDto billPayResponseDto = orderBillService.payBill(billPayRequestDto);
            if (billPayResponseDto != null)
                return new ResponseEntity(Optional.of(billPayResponseDto), HttpStatus.OK);
            else
                return new ResponseEntity("cannot pay bill due to following reasons: orderId is invalid, amount is not same as in generated bill, bill is already paid", HttpStatus.NO_CONTENT);
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}