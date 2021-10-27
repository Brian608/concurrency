package org.feather.ruyuan;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: concurrency
 * @description:
 * @author: 杜雪松(feather)
 * @since: 2021-10-26 10:47
 **/
public class Counter {
    private  int i=0;

    private ReentrantLock reentrantLock=new ReentrantLock();
        private  void add(){
            i++;
        }

    public void  lock1(){
        reentrantLock.lock();
        try {
        add();
        }finally {
            reentrantLock.unlock();
        }
    }

    public synchronized void lock2(){
            add();
    }

    public static void main(String[] args) {
        int a=4;
        int b=10;
        System.out.println("result:"+(int)Math.ceil(a/b));
    }

}
