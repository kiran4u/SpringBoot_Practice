package com.food.ordering.system.order.service.domain.service.domain;

import com.food.ordering.system.order.service.domain.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.service.domain.ports.output.message.publisher.payment.OrderCreatedPymntReqMsgPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCreateCommandHandler {

    private final OrderCreateHelper orderCreateHelper;

    private final OrderDataMapper orderDataMapper;

    private final OrderCreatedPymntReqMsgPublisher orderCreatedPymntReqMsgPublisher;

    public OrderCreateCommandHandler(OrderCreateHelper orderCreateHelper, OrderDataMapper orderDataMapper, OrderCreatedPymntReqMsgPublisher orderCreatedPymntReqMsgPublisher) {
        this.orderCreateHelper = orderCreateHelper;
        this.orderDataMapper = orderDataMapper;
        this.orderCreatedPymntReqMsgPublisher = orderCreatedPymntReqMsgPublisher;
    }

    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        OrderCreatedEvent orderCreatedEvent = orderCreateHelper.persistOrder(createOrderCommand);
        log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        orderCreatedPymntReqMsgPublisher.publish(orderCreatedEvent);
        return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder());
    }

}
