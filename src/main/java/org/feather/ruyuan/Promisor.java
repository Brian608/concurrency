package org.feather.ruyuan;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @program: concurrency
 * @description:
 * @author: 杜雪松(feather)
 * @since: 2021-10-28 15:36
 **/
public class Promisor {
    public static Future<Object> create(long startTime) {
        //1：定义任务
        FutureTask<Object> futureTask = new FutureTask<>(() -> {
            System.out.println("开始烧水，当前用时"+(System.currentTimeMillis()-startTime)+"ms");
            BoilWater boilWater=new BoilWater();
            Thread.sleep(15000);
            boilWater.setStatus(true);
            return  boilWater;
        });
      return  futureTask;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long startTime=System.currentTimeMillis();
        //part1  通过promisor的create来获取promise
        Future<Object> promise=Promisor.create(startTime);
        //part2 主线程来执行准备茶叶茶杯的任务
        System.out.println("开始准备茶叶茶杯，需要三分钟，当前用时"+(System.currentTimeMillis()-startTime)+"ms");
        BoilWater.TeaAndCup teaAndCup=new BoilWater.TeaAndCup();
        Thread.sleep(3000);
        teaAndCup.setStatus(true);
        System.out.println("茶叶茶杯结束，当前用时"+(System.currentTimeMillis()-startTime)+"ms");

        //part3 通过promise来获取执行结果
        //第一次用promise，查看任务是否执行完毕
        if (!promise.isDone()){
            System.out.println("茶叶茶杯结束，等待烧水完成");
        }
        //第二次用promise，获取result，可能有阻塞
        BoilWater boilWater=(BoilWater) promise.get();
        System.out.println("获取到烧水完成信号,当前用时"+(System.currentTimeMillis()-startTime)+"ms");
        System.out.println("准备工作结束，开始泡茶，make tea");
        System.out.println("总共用时："+(System.currentTimeMillis()-startTime)+"ms");
    }

}
