package com.yisen.demo01;

/**
 * @Author : yisen
 * @Date : 2023/4/13 10:26
 * @Description :
 */

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;

public class ByteBuddyDemo {
	public static void main(String[] args) throws Exception {
		// 创建一个ByteBuddy实例
		ByteBuddy byteBuddy = new ByteBuddy();

		// 定义一个新类
		Class<?> dynamicType = byteBuddy
				.subclass(Object.class)
				.name("com.yisen.MyClass")
				.defineMethod("hello", String.class, publicMethod())
				.intercept(FixedValue.value("Hello World!"))
				.make()
				.load(ByteBuddyDemo.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
				.getLoaded();

		// 实例化新类并调用hello()方法
		Object instance = dynamicType.newInstance();
		System.out.println(instance.getClass().getName() + ": " + instance.getClass().getMethod("hello").invoke(instance));
	}

	private static net.bytebuddy.description.modifier.Visibility publicMethod() {
		return net.bytebuddy.description.modifier.Visibility.PUBLIC;
	}
}
