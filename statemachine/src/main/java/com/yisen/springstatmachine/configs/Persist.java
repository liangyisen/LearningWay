package com.yisen.springstatmachine.configs;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : yisen
 * @Date : 2023/11/30 16:02
 * @Description :
 */
@Configuration
@Slf4j
public class Persist<E, S> {
	/**
	 * 持久化到内存map中
	 *
	 * @return StateMachinePersister
	 */
	@Bean(name = "stateMachineMemPersister")
	public static StateMachinePersister getPersister() {
		return new DefaultStateMachinePersister(new StateMachinePersist() {
			@Override
			public void write(StateMachineContext context, Object contextObj) {
				log.info("持久化状态机,context:{},contextObj:{}", JSON.toJSONString(context), JSON.toJSONString(contextObj));
				map.put(contextObj, context);
			}

			@Override
			public StateMachineContext read(Object contextObj) {
				log.info("获取状态机,contextObj:{}", JSON.toJSONString(contextObj));
				StateMachineContext stateMachineContext = (StateMachineContext) map.get(contextObj);
				log.info("获取状态机结果,stateMachineContext:{}", JSON.toJSONString(stateMachineContext));
				return stateMachineContext;
			}

			private Map map = new HashMap();
		});
	}


}
