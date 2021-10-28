package org.feather.ruyuan;

/**
 * @program: concurrency
 * @description:
 * @author: 杜雪松(feather)
 * @since: 2021-10-27 14:45
 * 关于线程阻塞无法感知标记位，如何停止线程执行的解决方案
 *
 * 线程阻塞本质上是一段代码执行时间过长，那它自然也没有办法感知到标记位的改变，从而优雅的终止线程。那么当一个线程发生阻塞时我们又该怎么去终止它呢？如果线程发生阻塞想要停止，就不太符合两阶段终止的思想了，属于一种暴力解决方式，这里提供一段代码作为一种思路。
 **/
public class ThreadExecutor {

    //执行线程
    private Thread executeThread;
    /**
     * 运行状态
     */
    private volatile  boolean isRunning=false;

    /**
     * 发生阻塞的线程任务
     * @param task
     */
    public  void execute(Runnable task){
        executeThread=new Thread(()->{
           Thread childrenThread=new Thread(task);
           //子线程设置为守护线程
            childrenThread.setDaemon(true);
            childrenThread.start();
            try {
                // 强行执行子线程，使其进入休眠状态
                childrenThread.join();
                isRunning=true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executeThread.start();
    }

    /**
     *
     * @param mills 强制结束任务的时长阈值
     */

    public  void shutDown(long mills){
        long currentTime=System.currentTimeMillis();
        while (isRunning){
            if (System.currentTimeMillis()-currentTime>=mills){
                System.out.println("任务超时，需要结束他");
                executeThread.interrupt();
                break;
            }
        }
        isRunning=false;
    }

    public static void main(String[] args) {
        ThreadExecutor executor=new ThreadExecutor();
        long start=System.currentTimeMillis();
        executor.execute(()->{
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.shutDown(1000);
        long end=System.currentTimeMillis();
        System.out.println(end-start);
    }


}
