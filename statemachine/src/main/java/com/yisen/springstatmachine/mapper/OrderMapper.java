package com.yisen.springstatmachine.mapper;

import com.yisen.springstatmachine.pojo.Order;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : yisen
 * @Date : 2023/11/30 21:30
 * @Description :
 */
@Repository
public class OrderMapper {

	private List<Order> orders = new ArrayList<>();

	@PostConstruct
	private void init() {
		Order order1 = new Order();
		order1.setId(2L);
		order1.setOrderCode("A111");
		order1.setStatus(1);
		order1.setName("A");
		order1.setPrice(new BigDecimal("22.00"));
		order1.setDeleteFlag(0);
		order1.setCreateTime(Timestamp.valueOf("2022-10-15 16:14:11"));
		order1.setUpdateTime(Timestamp.valueOf("2022-10-02 21:29:14"));
		order1.setCreateUserCode("zhangsan");
		order1.setUpdateUserCode("zhangsan");
		order1.setVersion(0);
		order1.setRemark(null);

		Order order2 = new Order();
		order2.setId(3L);
		order2.setOrderCode("A111");
		order2.setStatus(1);
		order2.setName("订单A");
		order2.setPrice(new BigDecimal("22.00"));
		order2.setDeleteFlag(0);
		order2.setCreateTime(Timestamp.valueOf("2022-10-02 21:53:13"));
		order2.setUpdateTime(Timestamp.valueOf("2022-10-02 21:29:14"));
		order2.setCreateUserCode("zhangsan");
		order2.setUpdateUserCode("zhangsan");
		order2.setVersion(0);
		order2.setRemark(null);

		Order order3 = new Order();
		order3.setId(4L);
		order3.setOrderCode("A111");
		order3.setStatus(1);
		order3.setName("订单A");
		order3.setPrice(new BigDecimal("22.00"));
		order3.setDeleteFlag(0);
		order3.setCreateTime(Timestamp.valueOf("2022-10-02 21:53:13"));
		order3.setUpdateTime(Timestamp.valueOf("2022-10-02 21:29:14"));
		order3.setCreateUserCode("zhangsan");
		order3.setUpdateUserCode("zhangsan");
		order3.setVersion(0);
		order3.setRemark(null);

		Order order4 = new Order();
		order4.setId(5L);
		order4.setOrderCode("A111");
		order4.setStatus(1);
		order4.setName("订单A");
		order4.setPrice(new BigDecimal("22.00"));
		order4.setDeleteFlag(0);
		order4.setCreateTime(Timestamp.valueOf("2022-10-03 09:08:30"));
		order4.setUpdateTime(Timestamp.valueOf("2022-10-02 21:29:14"));
		order4.setCreateUserCode("zhangsan");
		order4.setUpdateUserCode("zhangsan");
		order4.setVersion(0);
		order4.setRemark(null);

		orders.add(order1);
		orders.add(order2);
		orders.add(order3);
		orders.add(order4);
	}


	public void updateById(Order order) {
		for (Order order1 : orders) {
			if (order1.getId().equals(order.getId())) {
				order1.setStatus(order.getStatus());
				order1.setUpdateTime(order.getUpdateTime());
			}
		}
	}


	public void insert(Order order) {
		orders.add(order);
	}

	public Order selectById(Long id) {
		for (Order order : orders) {
			if (order.getId().equals(id)) {
				return order;
			}
		}
		return null;
	}
}
