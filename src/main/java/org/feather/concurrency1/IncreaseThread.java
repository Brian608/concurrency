package org.feather.concurrency1;

/**
 * @program: concurrency
 * @description:
 * @author: 杜雪松(feather)
 * @create: 2020-06-05 15:48
 **/
public class IncreaseThread extends  Thread {
    private MyObject myObject;
    public IncreaseThread(MyObject myObject){
        this.myObject=myObject;
    }


    @Override
    public void run() {
        for (int i = 0; i <30 ; ++i) {
            try {
                Thread.sleep((long)(Math.random()*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        myObject.increase();

    }
}
