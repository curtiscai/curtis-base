package com.curtis.thread.exector;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Exector2Test {

    private Logger LOGGER = LoggerFactory.getLogger(Exector2Test.class);

    @Test
    public void test() {

        // int corePoolSize,
        // int maximumPoolSize,
        // long keepAliveTime,
        // TimeUnit unit,
        // BlockingQueue<Runnable> workQueue
        BlockingDeque blockingDeque = new LinkedBlockingDeque(20);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 100, 60, TimeUnit.MINUTES, blockingDeque);

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                threadPoolExecutor.submit(this::doTask);
            }
        });

        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("core:{},active:{},queue:{},complete:{}", threadPoolExecutor.getCorePoolSize(), threadPoolExecutor.getActiveCount(),
                    threadPoolExecutor.getQueue().remainingCapacity(), threadPoolExecutor.getCompletedTaskCount());
        }
    }

    public void doTask() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
