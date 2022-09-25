package com.food.ordering.system.order.service.domain.service.domain.ports.input.message.listener.restaurantapproval;

import com.food.ordering.system.order.service.domain.service.domain.dto.message.RestaurantApprovalResponse;

public interface RestaurantApprvlRespMsgListener {

    void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);

    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);

}
