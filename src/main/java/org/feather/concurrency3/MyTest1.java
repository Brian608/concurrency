package org.feather.concurrency3;

/**
 * @program: concurrency
 * @description:当我们使用synchronized关键字来修饰代码快时，子界面层面上是使用monitorenter与monitorexist指令来是实现锁的获取与释放
 * 当线程进入到monitorenter指令后，线程将会持有monitor对象，推出monitorenter指令后，线程将会释放monitor对象
 * @author: 杜雪松(feather)
 * @create: 2020-06-24 15:54
 **/
public class MyTest1 {
    private  Object object=new Object();
    public  void method(){
        synchronized (object){
            System.out.println("hello word");
            throw new RuntimeException();
        }
    }
    public void method2(){
        synchronized (object){
            System.out.println("welcome");
        }
    }
}
