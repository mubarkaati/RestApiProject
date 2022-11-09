package com.usman.restaurantBooking.service;

import com.usman.restaurantBooking.entities.Order;
import com.usman.restaurantBooking.entities.OrderMenu;
import com.usman.restaurantBooking.entities.TableBooking;
import com.usman.restaurantBooking.model.dto.request.OrderRequestDto;
import com.usman.restaurantBooking.model.dto.response.OrderResponseDto;
import com.usman.restaurantBooking.repository.MenuRepository;
import com.usman.restaurantBooking.repository.OrderMenuRepository;
import com.usman.restaurantBooking.repository.OrderRepository;
import com.usman.restaurantBooking.repository.TableBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    TableBookingRepository tableBookingRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    OrderMenuRepository orderMenuRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderResponseDto addOrder(OrderRequestDto requestDto) {
        int tableId = requestDto.getTableId();
        TableBooking tableBooking = tableBookingRepository.findById(tableId).orElse(null);
        if ((tableBooking != null) && (tableBooking.isFree() == false)
                && (tableBooking.isBillGenerated() == false)
                && (requestDto.getMenuListAndQuantity().size() > 0)) {

            Order order;

            if (tableBooking.getOrders() != null) {
                 order = tableBooking.getOrders();
            }
            else {
                order = new Order();
            }
            order.setTableBooking(tableBookingRepository.findById(requestDto.getTableId()).orElse(null));
            Order savedOrder = orderRepository.save(order);

            List<OrderMenu> orderMenuList = requestDto.getMenuListAndQuantity()
                    .entrySet().stream()
                    .map(menuAndQuantity -> new OrderMenu(
                            menuAndQuantity.getValue(),
                            tableBooking,
                            savedOrder,
                            menuRepository.findById(Integer.parseInt(menuAndQuantity.getKey())).orElse(null)
                    ))
                    .map(orderMenu -> orderMenuRepository.save(orderMenu))
                    .collect(Collectors.toList());

            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setOrderId(savedOrder.getOrderId());
            orderResponseDto.setTableId(tableBooking.getTableId());
            orderResponseDto.setMenuItemName(
                    orderMenuList.stream()
                            .map(orderMenu -> orderMenu.getMenus().getItemName())
                            .collect(Collectors.toList())
            );

            tableBooking.setOrders(order);
            tableBookingRepository.save(tableBooking);

            return orderResponseDto;
        } else {
            return null;
        }
    }
}