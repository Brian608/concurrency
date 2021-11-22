package org.feather.ruyuan;

import java.util.concurrent.*;

/**
 * @program: concurrency
 * @description:利用线程池实现互联网验证码保护服务
 * @author: 杜雪松(feather)
 * @since: 2021-11-22 16:06
 **/
public class AsyncSmsVerificationCodePusher {
    private  static  final ExecutorService SMS_SEND_THREAD_POOL=new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() + 1,
            50,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(10000),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread=new Thread(r,"sms_send_thread_pool");
                    thread.setDaemon(true);
                    return thread;
                }
            },
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public void sendSmsVerificationCode(SmsVerificationCodeTask task){
        SMS_SEND_THREAD_POOL.submit(task);
    }
}
