package org.feather.concurrency2;

/**
 * @program: concurrency
 * @description: 如果一个对象有若干个synchronized方法，那么在某一时刻，这些synchronized方法只有唯一一个synchronized方法被某个访问，
 * 而其他的线程即便想要访问另外一个synchronized也要去等待，因为当一个对象里有若干个synchronized方法的时候，那么这些synchronized方法，当某个线程想要去访问的时候，
 * 他要尝试着去获取当前对象的锁，而当前对象只有一把锁
 * @author: 杜雪松(feather)
 * @create: 2020-06-23 20:41
 **/
public class MyThreadTest2 {
    public static void main(String[] args) {
    MyClass myClass=new MyClass();
    Thread t1=new Thread1(myClass);
    Thread t2=new Thread2(myClass);
    t1.start();
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
class  MyClass{
    public synchronized  void  hello(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello");
    }
    public synchronized  void  word(){
        System.out.println("word");
    }

}
class Thread1 extends Thread{
    private MyClass myClass;
    public Thread1 (MyClass myClass){
        this.myClass=myClass;
    }

    @Override
    public void run() {
        myClass.hello();
    }
}

class Thread2 extends Thread{

    private MyClass myClass;
    public Thread2 (MyClass myClass){
        this.myClass=myClass;
    }

    @Override
    public void run() {
        myClass.word();
    }
}
