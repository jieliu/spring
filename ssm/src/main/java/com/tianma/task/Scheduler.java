package com.tianma.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zuowenxia on 2017/4/27.
 */
@Service
public class Scheduler {

    private static final SimpleDateFormat dataFromat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 30000)
    public void testFixed() {
        System.out.println("每30秒打印一次" + dataFromat.format(new Date()));
    }

    /**
     附：cron表达式配置说明
     字段 允许值 允许的特殊字符
     秒 0-59 , - * /
     分 0-59 , - * /
     小时 0-23 , - * /
     日期 1-31 , - * ? / L W C
     月份 1-12 或者 JAN-DEC , - * /
     星期 1-7 或者 SUN-SAT , - * ? / L C #
     年（可选） 留空, 1970-2099 , - * /
     表达式 意义
     "0 0 12 * * ?" 每天中午12点触发
     "0 15 10 ? * *" 每天上午10:15触发
     "0 15 10 * * ?" 每天上午10:15触发
     "0 15 10 * * ? *" 每天上午10:15触发
     "0 15 10 * * ? 2005" 2005年的每天上午10:15触发
     "0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发
     "0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发
     "0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发
     "0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发
     "0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发
     "0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发
     "0 15 10 15 * ?" 每月15日上午10:15触发
     "0 15 10 L * ?" 每月最后一日的上午10:15触发
     "0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发
     "0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发
     "0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发
     特殊字符 意义
     * 表示所有值；
     ? 表示未说明的值，即不关心它为何值；
     - 表示一个指定的范围；
     , 表示附加一个可能值；
     / 符号前表示开始时间，符号后表示每次递增的值；
     */
    @Scheduled(cron = "0 30 * * * *")
    public void testCron() {
        System.out.println("每小时30分钟的时候打印一次"+ dataFromat.format(new Date()));
    }

    @Scheduled(fixedDelay = 60000)
    public void testDelay() {
        System.out.println("上次任务执行完成60s后打印一次"+dataFromat.format(new Date()));
    }
}