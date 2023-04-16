package com.yisen.javabase.guava.evenbus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author : yisen
 * @Date : 2023/4/16 14:17
 * @Description : 事件类（Event）。这个事件类可以是任何普通的Java类，只需实现Serializable接口即可
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyEvent implements Serializable {

	private String message;

}