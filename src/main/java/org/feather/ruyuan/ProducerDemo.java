package org.feather.ruyuan;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: concurrency
 * @description:生产者消费者
 * @author: 杜雪松(feather)
 * @since: 2021-11-19 16:42
 **/
public class ProducerDemo {
    public static void main(String[] args) {
        //生产者线程池
        ExecutorService producerThreads = Executors.newFixedThreadPool(3);
        //消费者线程池
        ExecutorService consumerThreads=Executors.newFixedThreadPool(2);
        //任务队列，长度为10
        ArrayBlockingQueue<Task> taskQueue=new ArrayBlockingQueue<>(10);

        //生产者提交任务
        producerThreads.submit(() -> {
            try {
                taskQueue.put(new Task("任务"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
// 消费者处理任务
        consumerThreads.submit(() -> {
            try {
                Task task = taskQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
    static class Task {
        // 任务名称
        private String taskName;
        public Task(String taskName) {
            this.taskName = taskName;
        }
    }

}
