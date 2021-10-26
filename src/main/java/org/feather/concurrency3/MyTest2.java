package org.feather.concurrency3;

/**
 * @program: concurrency
 * @description:对于synchronized关键字修饰方法来说，并没有出现monitorenter与monitorexit指令，而是出现了一个ACC_SYNCHRONIZED标志
 * jvm使用了ACC_SYNCHRONIZED访问来标志来区分一个方法是否为同步方法，当方法被调用时，调用指令会检查是否拥有ACC_SYNCHRONIZED这个标志，如果有，那么执行线程
 * 将会先持有方法所在对象的monitor对象，然后再去执行方法体，在该方法执行期间，其他线程均无法在获取到这个monitor，当线程执行该方法后，他会释放掉这个monitor对象。
 * @author: 杜雪松(feather)
 * @create: 2020-06-29 21:18
 **/
public class MyTest2 {
    public  synchronized  void method(){
        System.out.println("hello word");
    }
}
