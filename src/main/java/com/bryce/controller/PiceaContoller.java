package com.bryce.controller;

import com.bryce.service.PiceaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.Future;

/**
 * @ClassName PiceaContoller
 * @Description
 * @Author Bryce
 * @Date 2020-09-25 10:05
 */
@RequestMapping("/async")
public class PiceaContoller {

    @Autowired
    private PiceaService piceaService;

    @RequestMapping("/asyncTask")
    public void asyncTask(){
        System.out.println("在控制层调用的线程名："+ Thread.currentThread().getName());
        piceaService.asyncTask();
    }

    @RequestMapping("/asyncTaskFuture")
    public String asyncTaskFuture() throws Exception {
        System.out.println("在控制层调用的线程名："+ Thread.currentThread().getName());
        String ret = null;
        //异步先执行任务1
        Future<String> future = piceaService.asyncTaskFuture();
        //异步执行任务2
        Future<String> future2 = piceaService.asyncTaskFuture2();
        String ret1 = null;
        String ret2 = null;
        //获取任务1执行结果
        while (true) {
            if (future.isDone()){
                ret1 = future.get();
                break;
            }
        }
        //获取任务2执行结果
        while (true) {
            if (future2.isDone()) {
                ret2 = future2.get();
                break;
            }
        }
        //任务1结果+任务2结果
        ret = ret1 + "+" + ret2;
        //最终返回任何合集
        return  ret;
    }
}
