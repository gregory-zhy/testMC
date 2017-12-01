package cn.no7player.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduleTask {


    private int count=0;

//    @Scheduled(cron="*/6 * * * * ?")
    private void process(){
        System.out.println("this is scheduler task runing  "+(count++));
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //每3秒执行一次
//    @Scheduled(fixedRate = 3000)
//    public void timerRate() {
//        System.out.println("fixedRate : "+dateFormat.format(new Date()));
//    }

    //第一次延迟1秒执行，当执行完后3秒再执行
//    @Scheduled(initialDelay = 1000, fixedDelay = 3000)
//    public void timerInit() {
//        System.out.println("init : "+dateFormat.format(new Date()));
//    }

    //每天23点27分50秒时执行
//    @Scheduled(cron = "0 27 * * * ?")
    public void timerCron() {
        System.out.println("current time : "+ dateFormat.format(new Date()));
    }



//        0 0 10,14,16 * * ? 每天上午10点，下午2点，4点
//        0 0/30 9-17 * * ? 朝九晚五工作时间内每半小时
//        0 0 12 ? * WED 表示每个星期三中午12点
//        “0 0 12 * * ?” 每天中午12点触发
//        “0 15 10 ? * *” 每天上午10:15触发
//        “0 15 10 * * ?” 每天上午10:15触发
//        “0 15 10 * * ? *” 每天上午10:15触发
//        “0 15 10 * * ? 2005” 2005年的每天上午10:15触发
//        “0 * 14 * * ?” 在每天下午2点到下午2:59期间的每1分钟触发
//        “0 0/5 14 * * ?” 在每天下午2点到下午2:55期间的每5分钟触发
//        “0 0/5 14,18 * * ?” 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发
//        “0 0-5 14 * * ?” 在每天下午2点到下午2:05期间的每1分钟触发
//        “0 10,44 14 ? 3 WED” 每年三月的星期三的下午2:10和2:44触发
//        “0 15 10 ? * MON-FRI” 周一至周五的上午10:15触发
//        “0 15 10 15 * ?” 每月15日上午10:15触发
//        “0 15 10 L * ?” 每月最后一日的上午10:15触发
//        “0 15 10 ? * 6L” 每月的最后一个星期五上午10:15触发
//        “0 15 10 ? * 6L 2002-2005” 2002年至2005年的每月的最后一个星期五上午10:15触发
//        “0 15 10 ? * 6#3” 每月的第三个星期五上午10:15触发
}
