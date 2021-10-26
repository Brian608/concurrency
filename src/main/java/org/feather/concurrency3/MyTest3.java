package org.feather.concurrency3;

/**
 * @program: concurrency
 * @description:jvm中的同步是基于进入与推出监视对象（管程）（monitor）来实现的，每个对象实例都会有一个monitor对象，monitor对象会和对象一同创建并销毁
 * monitor对象是由C++来实现的。
 * 当多个线程同时访问一段同步代码时，这些线程会被放到一个EntityList集合中，处于阻塞状态的线程都会被放到该列表中，接下来，当线程获取到对象的monitor时
 * ，monitor是依赖于底层操作系统的mutex lock来实现互斥的，线程获取mutex成功，则会持有mutex，这时其他线程就无法获取到该mutex
 * 如果线程调用了wait方法，那么该线程就会释放掉所有持有的mutex，并且该线程会进入到waitSet集合（等待集合）中，等待下一次被其他线程调用notify/notifyAll唤醒
 * ，如果当前线程顺利执行完毕方法，那么他会释放掉所持有的mutex
 *
 * 总结一下：同步锁在这种实现方式中，因为monitor是依赖于底层的操作系统实现，这样就存在用户态与内核态之间的切换，所以会增加性能开销，
 * 通过对象互斥锁的概念来保证共享数据操作的完整性。每个对象对应于一个可称为【互斥锁】的标记，这个标记用于保证在任何时刻，只能有一个线程访问该对象。
 *
 * 那些处于EntryList与waitSet中的线程均处于阻塞状态，阻塞操作是由操作系统来完成的，在Linux下是通过pthread—mutex—lock函数来实现的。
 * 线程阻塞后便会进入到内核调度状态，这会导致系统在用户态与内核态之间来回切换，严重影响锁的性能。
 *
 * 解决上述问题的办法是自旋，其原理是：当发生对monitor的争用时，若owner能在很短的时间内释放掉锁，则那些正在争用的线程就可以稍微等待一下
 * （即所谓的自旋转），在owner线程释放之后，争用线程可能会立刻获取到锁，从而避免了系统阻塞，不过，当owner运行的时间超过了临街值后，争用线程自旋一段时间后依然无法获取到锁，
 * 这时对那些窒息感时间很短的代码快来说有极大的性能提升，显然，自旋在多处理器（多核心）上才有意义。
 *
 * @author: 杜雪松(feather)
 * @create: 2020-06-29 21:30
 **/
public class MyTest3 {
        public  static synchronized void method(){
                System.out.println("hello word");
        }
}
