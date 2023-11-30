package com.yisen.springstatmachine.controller;

import com.yisen.springstatmachine.pojo.Order;
import com.yisen.springstatmachine.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author : yisen
 * @Date : 2023/11/30 16:03
 * @Description :
 */
@RestController
@RequestMapping("/order")
public class OrderController {
	@Resource
	private OrderService orderService;

	/**
	 * 根据id查询订单
	 *
	 * @return order
	 */
	@GetMapping("/getById")
	public Order getById(@RequestParam("id") Long id) {
		//根据id查询订单
		return orderService.getById(id);
	}

	/**
	 * 创建订单
	 *
	 * @return success
	 */
	@PostMapping("/create")
	public String create(Order order) {
		//创建订单
		orderService.create(order);
		return "sucess";
	}

	/**
	 * 对订单进行支付
	 *
	 * @param id id
	 * @return success
	 */
	@PostMapping("/pay")
	public String pay(@RequestParam("id") Long id) {
		//对订单进行支付
		orderService.pay(id);
		return "success";
	}

	/**
	 * 对订单进行发货
	 *
	 * @param id id
	 * @return success
	 */
	@PostMapping("/deliver")
	public String deliver(@RequestParam("id") Long id) {
		//对订单进行确认收货
		orderService.deliver(id);
		return "success";
	}

	/**
	 * 对订单进行确认收货
	 *
	 * @param id id
	 * @return success
	 */
	@RequestMapping("/receive")
	public String receive(@RequestParam("id") Long id) {
		//对订单进行确认收货
		orderService.receive(id);
		return "success";
	}
}