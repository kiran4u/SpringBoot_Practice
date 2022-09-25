package com.food.ordering.system.order.service.domain.service.domain.ports.output.message.publisher.restaurantapproval;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.order.service.domain.service.domain.event.OrderPaidEvent;

public interface OrderPaidRestaurantReqMsgPublisher extends DomainEventPublisher<OrderPaidEvent> {

}
