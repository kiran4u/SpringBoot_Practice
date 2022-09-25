package com.food.ordering.system.order.service.domain.service.domain;

import com.food.ordering.system.order.service.domain.service.domain.dto.message.RestaurantApprovalResponse;
import com.food.ordering.system.order.service.domain.service.domain.ports.input.message.listener.restaurantapproval.RestaurantApprvlRespMsgListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;



@Slf4j
@Validated
@Service
public class RestaurantApprovalResponseMessageListenerImpl implements RestaurantApprvlRespMsgListener {


    @Override
    public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {

    }

    @Override
    public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {

    }
}
