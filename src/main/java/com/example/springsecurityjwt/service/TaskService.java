package com.example.springsecurityjwt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class TaskService {

    @Scheduled(fixedDelay = 10000)
    public void testFixedDelay() {
        log.info("fixedDelay test, thread name:[{}], execute time:[{}]",
                Thread.currentThread().getName(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    /**
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     */
    @Scheduled(cron = "0 0/1 * * * ? ")
    public void testCron() {
        log.info("cron test, thread name:[{}], execute time:[{}]",
                Thread.currentThread().getName(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @Scheduled(initialDelay = 5000, fixedRate = 10000)
    public void testFixedRate() {
        log.info("fixedRate test, thread name:[{}], execute time:[{}]",
                Thread.currentThread().getName(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

}
