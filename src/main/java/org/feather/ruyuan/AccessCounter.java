package org.feather.ruyuan;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: concurrency
 * @description:接口访问统计次数 详细运行流程看img.png
 * @author: 杜雪松(feather)
 * @since: 2021-10-26 10:35
 **/
public class AccessCounter {

       AtomicInteger accessCount=new AtomicInteger(0);

    public   int access(){
        accessCount.incrementAndGet();
        return accessCount.get();
    }


}
