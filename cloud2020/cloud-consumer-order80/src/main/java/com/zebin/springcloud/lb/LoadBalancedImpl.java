package com.zebin.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
//手写轮询
@Component
public class LoadBalancedImpl implements LoadBalanced{
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    @Override
    public ServiceInstance instance(List<ServiceInstance> instances) {
        int index = getAndIncrment() % instances.size();
        return instances.get(index);
    }
    //对原子值增加1并返回
    private final int getAndIncrment(){
        int cur = 0, next = 0;
        do{
            cur = atomicInteger.get();
            next = (cur + 1) > Integer.MAX_VALUE ? 0 : cur + 1;
        }while(!this.atomicInteger.compareAndSet(next, cur));
        return next;
    }
}
