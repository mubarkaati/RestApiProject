package com.usman.restaurantBooking.service;

import com.usman.restaurantBooking.entities.OrderBill;
import com.usman.restaurantBooking.entities.TableBooking;
import com.usman.restaurantBooking.entities.Tables;
import com.usman.restaurantBooking.model.dto.request.BillPayRequestDto;
import com.usman.restaurantBooking.model.dto.request.OrderBillRequestDto;
import com.usman.restaurantBooking.model.dto.response.BillPayResponseDto;
import com.usman.restaurantBooking.model.dto.response.OrderBillResponseDto;
import com.usman.restaurantBooking.repository.OrderBillRepository;
import com.usman.restaurantBooking.repository.TableBookingRepository;
import com.usman.restaurantBooking.repository.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderBillServiceImpl implements OrderBillService {

    @Autowired
    OrderBillRepository orderBillRepository;

    @Autowired
    TableBookingRepository tableBookingRepository;

    @Autowired
    TablesRepository tablesRepository;

    @Override
    public OrderBillResponseDto generateBill(OrderBillRequestDto billRequestDto) {
        TableBooking tableBooking = tableBookingRepository.findById(billRequestDto.getTableId()).orElse(null);
        if ((tableBooking.isBillGenerated() == false) && tableBooking != null && (tableBooking.getOrders() != null)) {
            List<Float> itemsTotal = tableBooking.getOrders()
                    .getOrderMenus().stream()
                    .map(orderMenu -> orderMenu.getItemQuantity() * orderMenu.getMenus().getItemPrice())
                    .collect(Collectors.toList());
            float totalPrice = 0;
            for (float itemPrice : itemsTotal) {
                totalPrice = totalPrice + itemPrice;
            }
            OrderBill orderBill = new OrderBill();
            orderBill.setTableBookingBill(tableBooking);
            orderBill.setTotalAmount(totalPrice);
            OrderBill generatedBill = orderBillRepository.save(orderBill);

            OrderBillResponseDto billResponseDto = new OrderBillResponseDto();
            billResponseDto.setBillId(generatedBill.getBillId());
            billResponseDto.setTableId(tableBooking.getTableId());
            billResponseDto.setPersonName(tableBooking.getPersonName());
            billResponseDto.setTotalAmount(totalPrice);

            tableBooking.setBillGenerated(true);
            tableBooking.setOrderBill(orderBill);
            tableBookingRepository.save(tableBooking);

            return billResponseDto;
        }
        else {
            return null;
        }
    }

    @Override
    public BillPayResponseDto payBill(BillPayRequestDto billPayRequestDto) {
        OrderBill orderBill = orderBillRepository.findById(billPayRequestDto.getBillId()).orElse(null);
        if ((orderBill != null) && (orderBill.getTotalAmount() == billPayRequestDto.getPaidAmount()) && (orderBill.isAmountPaid() == false)) {
            orderBill.setAmountPaid(true);

            TableBooking tableBooking = orderBill.getTableBookingBill();
            tableBooking.setFree(true);
            tableBookingRepository.save(tableBooking);

            BillPayResponseDto billPayResponseDto = new BillPayResponseDto();
            billPayResponseDto.setBillId(orderBill.getBillId());
            billPayResponseDto.setPaid(true);
            billPayResponseDto.setPersonName(tableBooking.getPersonName());
            billPayRequestDto.setPaidAmount(orderBill.getTotalAmount());

            Tables tables = tableBooking.getTable();
            tables.setAvailableTable(tables.getAvailableTable() + 1);
            tablesRepository.save(tables);

            return billPayResponseDto;
        }
        else {
            return null;
        }
    }
}