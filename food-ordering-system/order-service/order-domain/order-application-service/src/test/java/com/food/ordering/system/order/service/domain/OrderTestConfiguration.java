package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.service.domain.OrderDomainService;
import com.food.ordering.system.order.service.domain.service.domain.OrderDomainServiceImpl;
import com.food.ordering.system.order.service.domain.service.domain.ports.output.message.publisher.payment.OrderCancelledPymntReqMsgPublisher;
import com.food.ordering.system.order.service.domain.service.domain.ports.output.message.publisher.payment.OrderCreatedPymntReqMsgPublisher;
import com.food.ordering.system.order.service.domain.service.domain.ports.output.message.publisher.restaurantapproval.OrderPaidRestaurantReqMsgPublisher;
import com.food.ordering.system.order.service.domain.service.domain.ports.output.repository.CustomerRepository;
import com.food.ordering.system.order.service.domain.service.domain.ports.output.repository.OrderRepository;
import com.food.ordering.system.order.service.domain.service.domain.ports.output.repository.RestaurantRepository;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.food.ordering.system")
public class OrderTestConfiguration {

    @Bean
    public OrderCreatedPymntReqMsgPublisher orderCreatedPymntReqMsgPublisher() {
        return Mockito.mock(OrderCreatedPymntReqMsgPublisher.class);
    }

    @Bean
    public OrderCancelledPymntReqMsgPublisher orderCancelledPymntReqMsgPublisher() {
        return Mockito.mock(OrderCancelledPymntReqMsgPublisher.class);
    }

    @Bean
    public OrderPaidRestaurantReqMsgPublisher orderPaidRestaurantReqMsgPublisher() {
        return Mockito.mock(OrderPaidRestaurantReqMsgPublisher.class);
    }

    @Bean
    public OrderRepository getOrderRepository() {
        return Mockito.mock(OrderRepository.class);
    }

    @Bean
    public CustomerRepository customerRepository(){
        return Mockito.mock(CustomerRepository.class);
    }

    @Bean
    public RestaurantRepository restaurantRepository() {
        return Mockito.mock(RestaurantRepository.class);
    }

    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }

}
