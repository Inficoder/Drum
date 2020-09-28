package com.bryce.service.impl;

import com.bryce.service.PiceaService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @ClassName PiceaServiceImpl
 * @Description
 * @Author Bryce
 * @Date 2020-09-25 10:06
 */
@Service
public class PiceaServiceImpl implements PiceaService {


    @Override
    @Async
    public Future<String> asyncTaskFuture() {
        System.out.println("f1");
        return null;
    }

    @Override
    @Async
    public Future<String> asyncTaskFuture2() {
        System.out.println("f2");
        return null;
    }

    @Override
    @Async
    public void asyncTask() {
        System.out.println("async task");
    }
}
