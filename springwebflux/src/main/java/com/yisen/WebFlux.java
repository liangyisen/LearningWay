package com.yisen;

import org.springframework.beans.factory.FactoryBean;


public class WebFlux implements FactoryBean {


    @Override
    public Object getObject() throws Exception {
        return new ApiHandler();
    }

    @Override
    public Class<?> getObjectType() {
        return ApiHandler.class;
    }
}
