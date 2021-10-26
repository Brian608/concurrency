package org.feather.concurrency1;

/**
 * @program: concurrency
 * @description:
 * 编写一个程序，实现这样一个目标
 *                 1。存在一个对象，该对象有一个int类型的成员变量counter，该成员变量的初始值为0
 *                 2。创建两个线程，其中一个线程对该对象的成员变量counter增1，另外一个线程对该对象的成员变量减1
 *                 3。输出该对象成员变量counter每次变化后的直
 *                 4。最终输出的结果为101011010101010。。。。。
 *
 * @author: 杜雪松(feather)
 * @create: 2020-06-05 15:42
 **/
public class MyObject {
    private  int counter;
    public  synchronized  void increase()  {
        while (counter!=0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter++;
        System.out.println(counter);
        notify();
    }

    public synchronized  void decrease()  {
        while (counter==0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter--;
        System.out.println(counter);
        notify();
    }

}
