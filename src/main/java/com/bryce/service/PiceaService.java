package com.bryce.service;

import java.util.concurrent.Future;

/**
 * @ClassName PiceaService
 * @Description
 * @Author Bryce
 * @Date 2020-09-25 10:06
 */
public interface PiceaService {
    Future<String> asyncTaskFuture();
    Future<String> asyncTaskFuture2();
    void asyncTask();
}
