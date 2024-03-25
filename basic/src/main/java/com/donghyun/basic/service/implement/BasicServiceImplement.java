package com.donghyun.basic.service.implement;

import com.donghyun.basic.service.BasicService;

public class BasicServiceImplement implements BasicService {
    @Override
    public String getHello() {
        return "Hello Springboot!!";
    }
}
