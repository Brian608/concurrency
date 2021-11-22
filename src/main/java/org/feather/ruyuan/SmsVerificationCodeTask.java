package org.feather.ruyuan;

import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

/**
 * @program: concurrency
 * @description:
 * @author: 杜雪松(feather)
 * @since: 2021-11-22 16:14
 **/
public class SmsVerificationCodeTask  implements Runnable{
    private static  final Logger LOGGER=LoggerFactory.getLogger(SmsVerificationCodeTask.class);

    private Long phoneNumber;

    public  SmsVerificationCodeTask(long phoneNumber){
        //省略判断手机号的长度为11
       this.phoneNumber=phoneNumber;
    }
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
// 生成验证码
        int verificationCode= ThreadLocalRandom.current().nextInt(999999);
        DecimalFormat decimalFormat=new DecimalFormat("000000");
        String txtVerificationCode=decimalFormat.format(verificationCode);
        //发送短信
        sendMessage(phoneNumber,txtVerificationCode);

    }
    private  void sendMessage(long phoneNumber,String  txtVerificationCode){
        LOGGER.info("发送短信开始:phoneNumber->{},,txtVerificationCode->{}",phoneNumber,txtVerificationCode);
        try {
            //模拟网络调用
            Thread.sleep(5000);
        }catch (Exception e){
            LOGGER.info("发送短信结束:phoneNumber->{},,txtVerificationCode->{}",phoneNumber,txtVerificationCode);
        }
    }
}
