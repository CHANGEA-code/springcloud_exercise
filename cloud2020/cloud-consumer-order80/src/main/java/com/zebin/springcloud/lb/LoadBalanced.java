package com.zebin.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalanced {
    //获取实例
    public ServiceInstance instance(List<ServiceInstance> instances);
}
