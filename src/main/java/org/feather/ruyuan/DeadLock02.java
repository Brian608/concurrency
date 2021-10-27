package org.feather.ruyuan;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: concurrency
 * @description: 破坏占用且等待条件
 *
 * 我们要破坏占用且等待，就是一次性申请占有所有的资源，我们拿文章开头的【账户A】、【账户B】来举例，就是一次性申请账户A，账户B的锁，当线程01拿到账户A、B全部的锁后，再执行具体的操作。
 * @author: 杜雪松(feather)
 * @since: 2021-10-27 10:19
 **/
public class DeadLock02 {
    public static void main(String[] args) {
        Account a =new Account();
        Account b=new Account();
        a.transfer(b,100);
        b.transfer(a,200);
    }
    static  class Allocator{
        private List<Object> als=new ArrayList<>();
        //一次申请所有资源
        synchronized  boolean apply(Object from,Object to){
            if (als.contains(from)||als.contains(to)){
                return false;
            }else {
                als.add(from);
                als.add(to);
            }
            return  true;
        }
        synchronized void clean(Object from,Object to){
            als.remove(from);
            als.remove(to);
        }
    }
    static  class Account{
        private Allocator actr = DeadLock02.getInstance();
        private  int balance;
        void  transfer(Account target,int amt){

            while(!actr.apply(this, target));
            try{
                synchronized(this){
                    System.out.println(this.toString()+" lock obj1");
                    synchronized(target){
                        System.out.println(this.toString()+" lock obj2");
                        if (this.balance > amt){
                            this.balance -= amt;
                            target.balance += amt;
                        }
                    }
                }
            } finally {
                //执行完后，再释放持有的资源
                actr.clean(this, target);
            }
        }
    }

    private void Allocator(){};

    private static class SingleTonHoler{
        private static Allocator INSTANCE = new Allocator();
    }

    public static Allocator getInstance(){
        return SingleTonHoler.INSTANCE;
    }

}