package com.food.ordering.system.order.service.domain.service.domain.ports.output.message.publisher.payment;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.order.service.domain.service.domain.event.OrderCancelledEvent;

public interface OrderCancelledPymntReqMsgPublisher extends DomainEventPublisher<OrderCancelledEvent> {

}
