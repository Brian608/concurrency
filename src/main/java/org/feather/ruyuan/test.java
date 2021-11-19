package org.feather.ruyuan;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @program: concurrency
 * @description:
 * @author: 杜雪松(feather)
 * @since: 2021-11-01 18:15
 **/
public class test {
    public static   String checkSortWay(Integer sortWay){
        if (IntStream.of(1, 2, 3, 4, 5).anyMatch(sortWay::equals)) {
            return  null;
        }
        return "请输入合法的排序值";
    }
    public static int  reverseNum(){
        int n,g;
        int sum=0;
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入一个整数:");
         n=sc.nextInt();
        while(n!=0){
        g=n%10;
        sum=sum*10+g;
        n/=10;
        }

        return sum;
    }

    public static void main(String[] args) {
       // System.out.println( checkSortWay(6));
        System.out.println( reverseNum());
    }
}
