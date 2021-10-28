package org.feather.ruyuan;

import java.util.Queue;

/**
 * @program: concurrency
 * @description:
 * @author: 杜雪松(feather)
 * @since: 2021-10-27 11:11
 **/
public class GuardedQueue {
    private  final Queue<Integer> sourceList;

    public GuardedQueue(Queue<Integer> sourceList) {
        this.sourceList = sourceList;
    }
    public synchronized  Integer get(){
        while (sourceList.isEmpty()){
            try {
               // 队列为null，等待
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
        return sourceList.peek();
}
public  synchronized void  put(Integer e){
        sourceList.add(e);
        //通知  继续执行
        notifyAll();
}
}
