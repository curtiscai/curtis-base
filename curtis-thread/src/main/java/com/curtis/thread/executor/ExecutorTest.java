package com.curtis.thread.executor;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author curtis.cai
 * @desc 测试线程池的用法
 * @date 2021-06-21
 * @email curtis.cai@outlook.com
 * @reference
 */
public class ExecutorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorTest.class);

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

    @Test
    public void testExecutor2() {
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

        final Semaphore semaphore = new Semaphore(60);
        for (int i = 0; i < 1000; i++) {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadPoolExecutor.submit(() -> {
                doTaskWithResult();
                semaphore.release();
            });
        }

        while (threadPoolExecutor.getCompletedTaskCount()<1000){
            Thread.yield();
        }

    }

    public Integer doTaskWithResult() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Test
    public void testExecutor3() {
        BlockingQueue workQueue = new ArrayBlockingQueue(20);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 50, 60, TimeUnit.SECONDS, workQueue,
                new RejectedExecutionHandler() {
                    /**
                     * 自定义拒绝策略, 当工作队列满时, 生产者调用put阻塞
                     */
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        if (!executor.isShutdown()) {
                            try {
                                executor.getQueue().put(r);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                LOGGER.error("Create blocking thread pool error !", e);
                            }
                        }
                    }
                });

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
            threadPoolExecutor.execute(this::doTaskWithResult);
        }

        while (threadPoolExecutor.getCompletedTaskCount()<1000){
            Thread.yield();
        }

    }
}
