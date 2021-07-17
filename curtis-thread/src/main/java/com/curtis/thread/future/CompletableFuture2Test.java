package com.curtis.thread.future;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFuture2Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompletableFuture2Test.class);

    public void doTask(int second) {
        LOGGER.info("   Begin Task In {}", LocalTime.now().toString());
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("Complete Task In {}", LocalTime.now().toString());
    }

    public String doTaskWithResult(int second) {
        LOGGER.info("   Begin Task In {}", LocalTime.now().toString());
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("Complete Task In {}", LocalTime.now().toString());
        return "this is a test result";
    }

    @Test
    public void testSimpleFuture() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        new Thread(() -> {
            try {
                LOGGER.info("exec method of get {}", LocalTime.now().toString());
                LOGGER.info("if completed in any fashion: normally, exceptionally, or via cancellation. {}", completableFuture.isDone());
                completableFuture.get();
                LOGGER.info("finish method of get {}", LocalTime.now().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
            LOGGER.info("begin sleep 5 seconds {}", LocalTime.now().toString());
            TimeUnit.SECONDS.sleep(5);
            LOGGER.info("finish sleep 5 seconds {}", LocalTime.now().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            LOGGER.info("exec method of complete {}", LocalTime.now().toString());
            completableFuture.complete("this is a test result");
            LOGGER.info("exec method of complete {}", LocalTime.now().toString());
            LOGGER.info("if completed in any fashion: normally, exceptionally, or via cancellation. {}", completableFuture.isDone());
        }).start();
    }

    @Test
    public void testSimpleFuture2() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        new Thread(() -> {
            try {
                LOGGER.info("exec method of get {}", LocalTime.now().toString());
                LOGGER.info("if completed in any fashion: normally, exceptionally, or via cancellation. {}", completableFuture.isDone());
                completableFuture.get();
                LOGGER.info("finish method of get {}", LocalTime.now().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                LOGGER.info("catch a execution exception: {}", e.getMessage());
                e.printStackTrace();
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
            LOGGER.info("begin sleep 5 seconds {}", LocalTime.now().toString());
            TimeUnit.SECONDS.sleep(5);
            LOGGER.info("finish sleep 5 seconds {}", LocalTime.now().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            LOGGER.info("exec method of completeExceptionally {}", LocalTime.now().toString());
            completableFuture.completeExceptionally(new RuntimeException("this is a customer exception!!!"));
            LOGGER.info("exec method of completeExceptionally {}", LocalTime.now().toString());
            LOGGER.info("if completed in any fashion: normally, exceptionally, or via cancellation. {}", completableFuture.isDone());
        }).start();
    }

    @Test
    public void testGet() {
        // 创建完成后方法立即返回
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> doTask(5));
        LOGGER.info("   Begin Task In {}", LocalTime.now().toString());
        try {
            // 阻塞当前线程执行，直到future完成
            Void voidResult1 = voidCompletableFuture.get();
            Void voidResult2 = voidCompletableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        LOGGER.info("Complete Task In {}", LocalTime.now().toString());
    }

    /**
     * CompletableFuture提供了四个静态方法用来创建CompletableFuture对象：
     * <p>
     * 1 public static CompletableFuture<Void>   runAsync(Runnable runnable)
     * 2 public static CompletableFuture<Void>   runAsync(Runnable runnable, Executor executor)
     * 3 public static <U> CompletableFuture<U>  supplyAsync(Supplier<U> supplier)
     * 4 public static <U> CompletableFuture<U>  supplyAsync(Supplier<U> supplier, Executor executor)
     * Asynsc表示异步,而supplyAsync与runAsync不同在与前者异步返回一个结果,后者是void.第二个函数第二个参数表示是用我们自己创建的线程池,
     * 否则采用默认的ForkJoinPool.commonPool()作为它的线程池.其中Supplier是一个函数式接口,代表是一个生成者的意思,传入0个参数,返回一个结果.
     */

    @Test
    public void testRunAsync() {
        // 创建完成后方法立即返回
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> doTask(5));
        LOGGER.info("   Begin Task In {}", LocalTime.now().toString());
        try {
            // 阻塞当前线程执行，直到future完成
            Void voidResult = voidCompletableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        LOGGER.info("Complete Task In {}", LocalTime.now().toString());
    }

    @Test
    public void testSupplyAsync() {
        // 创建完成后方法立即返回
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> doTaskWithResult(5));
        LOGGER.info("   Begin Task In {}", LocalTime.now().toString());
        try {
            // 阻塞当前线程执行，直到future完成
            String result = completableFuture.get();
            LOGGER.info("The result is {}", result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        LOGGER.info("Complete Task In {}", LocalTime.now().toString());
    }

    @Test
    public void testRunAsync2() {
        // 创建完成后方法立即返回
        CompletableFuture<Void> voidCompletableFuture1 = CompletableFuture.runAsync(() -> doTask(5));
        CompletableFuture<Void> voidCompletableFuture2 = CompletableFuture.runAsync(() -> doTask(2));
        LOGGER.info("   Begin Task In {}", LocalTime.now().toString());
        try {
            // 居然不是等voidCompletableFuture1完成然后才会等待2完成，而是谁先结束则谁先执行
            Void voidResult1 = voidCompletableFuture1.get();
            LOGGER.info("get result of future1 and begin get result of future2");
            Void voidResult2 = voidCompletableFuture2.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        LOGGER.info("Complete Task In {}", LocalTime.now().toString());
    }
}
