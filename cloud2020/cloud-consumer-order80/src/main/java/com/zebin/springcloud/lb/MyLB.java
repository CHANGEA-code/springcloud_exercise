package com.zebin.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int cur = 0, next = 0;
        do {
            cur = atomicInteger.get();
            next = (cur + 1) >= Integer.MAX_VALUE ? 0 : (cur + 1);
        }while (!(this.atomicInteger.compareAndSet(cur, next)));
        System.out.println("***next=" + next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> instances) {
        int ind = getAndIncrement() % instances.size();
        return instances.get(ind);
    }
}
