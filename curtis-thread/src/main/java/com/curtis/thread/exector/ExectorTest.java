package com.curtis.thread.exector;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.*;

public class ExectorTest {

    private Logger LOGGER = LoggerFactory.getLogger(ExectorTest.class);

    @Test
    public void test() {

        // int corePoolSize,
        // int maximumPoolSize,
        // long keepAliveTime,
        // TimeUnit unit,
        // BlockingQueue<Runnable> workQueue
        BlockingQueue blockingDeque = new ArrayBlockingQueue(100);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 30, 600, TimeUnit.SECONDS, blockingDeque);
        LOGGER.info("core:{},active:{},queue:{},complete:{}", threadPoolExecutor.getCorePoolSize(), threadPoolExecutor.getActiveCount(),
                threadPoolExecutor.getQueue().remainingCapacity(), threadPoolExecutor.getCompletedTaskCount());
        for (int i = 0; i < 110; i++) {
//            threadPoolExecutor.submit(() -> doTask());
            threadPoolExecutor.execute(() -> doTask());
            LOGGER.info("core:{},active:{},queue:{},complete:{}", threadPoolExecutor.getCorePoolSize(), threadPoolExecutor.getActiveCount(),
                    threadPoolExecutor.getQueue().remainingCapacity(), threadPoolExecutor.getCompletedTaskCount());
        }
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("core:{},active:{},queue:{},complete:{}", threadPoolExecutor.getCorePoolSize(), threadPoolExecutor.getActiveCount(),
                    threadPoolExecutor.getQueue().remainingCapacity(), threadPoolExecutor.getCompletedTaskCount());
            if(threadPoolExecutor.getQueue().remainingCapacity() == 100){
                System.out.println(threadPoolExecutor.getPoolSize());
            }
//            threadPoolExecutor.getCorePoolSize();
//            threadPoolExecutor.getActiveCount();
//            threadPoolExecutor.getQueue().remainingCapacity();
        }
    }

    public void doTask() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
