package com.zzj.csdnranks.config;

import com.zzj.csdnranks.tasks.WatcherTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {

    @Bean
    public JobDetail watcherDetail(){
        return JobBuilder.newJob(WatcherTask.class).withIdentity("watcherTask").storeDurably().build();
    }

    @Bean
    public Trigger watcherTrigger(){
        //直接设定
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5)
                //.withIntervalInHours(6) // 6个小时记录一次
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(watcherDetail())
                .withIdentity("watcherTask")
                .withSchedule(scheduleBuilder)
                .build();
        //cron表达式
//        return TriggerBuilder.newTrigger().forJob(watcherDetail())
//                .withIdentity("testTask2")
//                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
//                .build();
    }
}
