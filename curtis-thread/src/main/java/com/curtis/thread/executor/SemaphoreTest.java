package com.curtis.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-06-22
 * @email curtis.cai@outlook.com
 * @reference
 */
public class SemaphoreTest {
    private static final int THREAD_COUNT = 50;//总共的线程数
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
    private static Semaphore semaphore = new Semaphore(50);//可以并发执行的线程数

    public static void main(String[] args) {
        for (int i = 0; i < 500; i++) {
            final int j=i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();//acquire()获取一个许可证
                        TimeUnit.SECONDS.sleep(1);
                        semaphore.release();//使用完之后调用release()归还许可证
                    } catch (InterruptedException e) {
                    }
                }
            });
        }
        threadPool.shutdown();

    }
}
