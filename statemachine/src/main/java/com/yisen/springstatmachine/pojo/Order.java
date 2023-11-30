package com.yisen.springstatmachine.pojo;

/**
 * @Author : yisen
 * @Date : 2023/11/30 16:08
 * @Description :
 */
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class Order {
	private Long id;
	private String orderCode;
	private Integer status;
	private String name;
	private BigDecimal price;
	private Integer deleteFlag;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String createUserCode;
	private String updateUserCode;
	private Integer version;
	private String remark;

}
