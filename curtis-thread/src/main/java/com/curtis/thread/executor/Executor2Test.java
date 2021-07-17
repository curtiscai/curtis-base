package com.curtis.thread.executor;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author curtis.cai
 * @desc 测试线程池的用法
 * @date 2021-06-21
 * @email curtis.cai@outlook.com
 * @reference
 */
public class Executor2Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(Executor2Test.class);

    @Test
    public void testExecutor() {
        BlockingQueue workQueue = new ArrayBlockingQueue(20);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 100, 60, TimeUnit.SECONDS, workQueue);
        for (int i = 0; i < 100; i++) {
            LOGGER.info("CorePoolSize:{},ActiveCount:{},TaskCount:{},CompletedTaskCount:{},remainingCapacity:{}",
                    threadPoolExecutor.getCorePoolSize(), threadPoolExecutor.getActiveCount(), threadPoolExecutor.getTaskCount(),
                    threadPoolExecutor.getCompletedTaskCount(), threadPoolExecutor.getQueue().remainingCapacity());
            threadPoolExecutor.execute(() -> {
                doTask();
            });
        }

        while (true) {
            LOGGER.info("CorePoolSize:{},ActiveCount:{},TaskCount:{},CompletedTaskCount:{},remainingCapacity:{}",
                    threadPoolExecutor.getCorePoolSize(), threadPoolExecutor.getActiveCount(), threadPoolExecutor.getTaskCount(),
                    threadPoolExecutor.getCompletedTaskCount(), threadPoolExecutor.getQueue().remainingCapacity());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void doTask() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        BlockingQueue workQueue = new ArrayBlockingQueue(20);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 50, 60, TimeUnit.SECONDS, workQueue);

        new Thread(() -> {
            while (true) {
                LOGGER.info("CorePoolSize:{},ActiveCount:{},TaskCount:{},CompletedTaskCount:{},remainingCapacity:{}",
                        threadPoolExecutor.getCorePoolSize(), threadPoolExecutor.getActiveCount(), threadPoolExecutor.getTaskCount(),
                        threadPoolExecutor.getCompletedTaskCount(), threadPoolExecutor.getQueue().remainingCapacity());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        final Semaphore semaphore = new Semaphore(20);
        for (int i = 0; i < 1000; i++) {
            Runnable runnable = () -> {
                try {
                    semaphore.acquire();
                    doTaskWithResult();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            threadPoolExecutor.execute(runnable);
        }

        while (Thread.activeCount() > 0) {
            Thread.yield();
        }


    }


    public static Integer doTaskWithResult() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
