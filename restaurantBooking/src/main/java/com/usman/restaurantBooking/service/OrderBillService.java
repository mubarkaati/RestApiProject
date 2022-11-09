package com.usman.restaurantBooking.service;

import com.usman.restaurantBooking.model.dto.request.BillPayRequestDto;
import com.usman.restaurantBooking.model.dto.request.OrderBillRequestDto;
import com.usman.restaurantBooking.model.dto.response.BillPayResponseDto;
import com.usman.restaurantBooking.model.dto.response.OrderBillResponseDto;

public interface OrderBillService {
    public OrderBillResponseDto generateBill(OrderBillRequestDto billRequestDto);

    BillPayResponseDto payBill(BillPayRequestDto billPayRequestDto);
}
