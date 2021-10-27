package org.feather.ruyuan;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: concurrency
 * @description:
 * 破坏不抢占条件，需要发生死锁的线程能够主动释放它占有的资源，但使用synchronized是做不到的。原因为synchronized申请不到资源时，线程直接进入了阻塞状态，而线程进入了阻塞状态也就没有办法释放它占有的资源了。
 *
 * 不过JDK中的java.util.concurrent提供了Lock解决这个问题。
 *
 * 显式使用Lock类中的定时tryLock功能来代替内置锁机制，可以检测死锁和从死锁中恢复过来。使用内置锁的线程获取不到锁会被阻塞，而显式锁可以指定一个超时时限（Timeout），在等待超过该时间后tryLock就会返回一个失败信息，也会释放其拥有的资源。
 * @author: 杜雪松(feather)
 * @since: 2021-10-27 10:40
 **/
public class DeadLock {
    private static ReentrantLock lock1=new ReentrantLock();
    private static ReentrantLock lock2=new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1=new Thread(new Lock1());
        Thread thread2=new Thread(new Lock1());
        thread1.start();
        thread2.start();
    }
    static  class Lock1 implements  Runnable{

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            try {
                System.out.println("Lock is running");
                while (true){
                    if (lock1.tryLock(1, TimeUnit.MICROSECONDS)){
                        System.out.println("lock1 is lock obj1");
                    }
                    if (lock2.tryLock(1,TimeUnit.MICROSECONDS)){
                        System.out.println("lock2 is lock obj2");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock1.unlock();
                lock2.unlock();
            }
        }

    }
}
