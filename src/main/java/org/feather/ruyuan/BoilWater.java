package org.feather.ruyuan;

/**
 * @program: concurrency
 * @description:Promise模式
 * @author: 杜雪松(feather)
 * @since: 2021-10-28 09:57
 **/

/**
 * 先设定两个类， 一个是BoilWater表示烧水，在代码中new BoilWater()表示开始烧水，其中status等于true表示烧水完成。另一个是TeaAndCup表示准备茶叶茶杯，
 * 同样， 在代码中new TeaAndCup表示开始准备茶叶茶杯，其中status等于true表示准备茶叶茶杯完成。
 */
public class BoilWater {
    boolean status=false;

    public boolean isStatus(){
        return  status;
    }
    public  void setStatus(boolean status){
        this.status=status;
    }
    static  class TeaAndCup{
        boolean status=false;

        public  boolean isStatus(){
            return  status;
        }
        public  void setStatus(boolean status){
            this.status=status;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime=System.currentTimeMillis();
        Thread t=new Thread(()->{
            System.out.println("任务一开始，需要15分钟");
            try {
             BoilWater boilWater=new BoilWater() ;
             Thread.sleep(15000);
             boilWater.setStatus(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务一结束，烧水结束，当前用时"+(System.currentTimeMillis()-startTime)+"ms");
        });
        t.start();
        t.join();
        System.out.println("任务二开始，准备茶叶和茶杯，需要用时三分钟,当前用时"+(System.currentTimeMillis()-startTime)+"ms");
        TeaAndCup teaAndCup=new TeaAndCup();
        Thread.sleep(3000);
        teaAndCup.setStatus(true);
        System.out.println("任务二结束，茶叶茶杯结束，总用时："+(System.currentTimeMillis()-startTime)+"ms");
        System.out.println("准备工作结束，开始泡茶，make tea！");
        System.out.println("总共用时"+(System.currentTimeMillis()-startTime)+"ms");
    }



}
