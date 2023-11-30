package com.yisen.springstatmachine.service;


import com.yisen.springstatmachine.constans.OrderStatusChangeEvent;
import com.yisen.springstatmachine.pojo.Order;

/**
 * @Author : yisen
 * @Date : 2023/11/30 16:16
 * @Description :
 */
public interface OrderService {

	Order create(Order order);

	Order pay(Long id);

	Order deliver(Long id);

	Order receive(Long id);

	boolean sendEvent(OrderStatusChangeEvent changeEvent, Order order);

	Order getById(Long id);
}
