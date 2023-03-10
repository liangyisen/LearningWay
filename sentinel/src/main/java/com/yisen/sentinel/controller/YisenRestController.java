package com.yisen.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : yisen
 * @Date : 2023/3/10 14:55
 * @Description :
 */
@RestController
class YisenRestController {

	@GetMapping("hello")
	public String hello() {
		return "hello";
	}
}
