package com.curtis.jdbc.mysql.lock;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author curtis.cai
 * @desc MySQL批量更新锁冲突测试
 * @date 2021-08-14
 * @email curtis.cai@outlook.com
 * @reference
 */
public class BatchUpdateLockConflictTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchUpdateLockConflictTest.class);

    /**
     * 测试循环进行批量更新：循环10次
     * 完成全部批量更新： -> 耗时PT1M4.423S秒
     */
    @Test
    public void testBatchUpdateLockConflictWithLoop() {
        String driverName = "com.mysql.cj.jdbc.Driver";
        // String driverName = "com.mysql.jdbc.Driver"; // mysql-connector-java 5.*使用
        String url = "jdbc:mysql://192.168.2.101:3306/db_test?useSSL=true&characterEncoding=utf-8&serverTimezone=GMT";
        String user = "root";
        String password = "000000";

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sqlUpdate = "UPDATE tb_test_lock SET v_1=v_1+1,v_3=v_3+1,v_4=v_4+1,v_5=v_5+1,v_6=v_6+1,v_7=v_7+1,v_8=v_8+1 WHERE id BETWEEN ? AND ?;";
        // LOGGER.info("sqlUpdate:{}", sqlUpdate);

        int batchSize = 100 * 10000;
        int idMax = 1000 * 10000;
        int batchCount = idMax / batchSize;
        // List<Integer> idList = IntStream.rangeClosed(1, idMax).boxed().collect(Collectors.toList());

        LocalTime startTime = LocalTime.now();
        for (int i = 1; i <= batchCount; i++) {
            LocalTime innerStartTime = LocalTime.now();
            int finalI = i;
            try {
                Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
                int startIndex = (finalI - 1) * batchSize + 1;
                int endIndex = finalI * batchSize;
                preparedStatement.setInt(1, startIndex);
                preparedStatement.setInt(2, endIndex);
                int updateCount = preparedStatement.executeUpdate();

                LocalTime innerEndTime = LocalTime.now();
                Duration innerBetween = Duration.between(innerStartTime, innerEndTime);
                /*
                22:25:46,597  INFO BatchUpdateLockConflictTest:86 - 完成单次批量更新： -> startIndex:1, endIndex:1000000, updateCount:1000000, 耗时PT8.603S秒
                22:25:53,385  INFO BatchUpdateLockConflictTest:86 - 完成单次批量更新： -> startIndex:1000001, endIndex:2000000, updateCount:1000000, 耗时PT6.786S秒
                22:26:00,199  INFO BatchUpdateLockConflictTest:86 - 完成单次批量更新： -> startIndex:2000001, endIndex:3000000, updateCount:1000000, 耗时PT6.814S秒
                22:26:06,823  INFO BatchUpdateLockConflictTest:86 - 完成单次批量更新： -> startIndex:3000001, endIndex:4000000, updateCount:1000000, 耗时PT6.624S秒
                22:26:13,574  INFO BatchUpdateLockConflictTest:86 - 完成单次批量更新： -> startIndex:4000001, endIndex:5000000, updateCount:1000000, 耗时PT6.75S秒
                22:26:19,836  INFO BatchUpdateLockConflictTest:86 - 完成单次批量更新： -> startIndex:5000001, endIndex:6000000, updateCount:1000000, 耗时PT6.262S秒
                22:26:25,542  INFO BatchUpdateLockConflictTest:86 - 完成单次批量更新： -> startIndex:6000001, endIndex:7000000, updateCount:1000000, 耗时PT5.706S秒
                22:26:31,309  INFO BatchUpdateLockConflictTest:86 - 完成单次批量更新： -> startIndex:7000001, endIndex:8000000, updateCount:1000000, 耗时PT5.767S秒
                22:26:36,980  INFO BatchUpdateLockConflictTest:86 - 完成单次批量更新： -> startIndex:8000001, endIndex:9000000, updateCount:1000000, 耗时PT5.671S秒
                22:26:42,410  INFO BatchUpdateLockConflictTest:86 - 完成单次批量更新： -> startIndex:9000001, endIndex:10000000, updateCount:1000000, 耗时PT5.43S秒
                 */
                LOGGER.info("完成单次批量更新： -> startIndex:{}, endIndex:{}, updateCount:{}, 耗时{}秒", startIndex, endIndex, updateCount, innerBetween);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.info("单次批量更新发生异常，异常信息为： -> {}：{}", e.getClass().getName(), e.getMessage());
            }
        }
        LocalTime endTime = LocalTime.now();
        Duration between = Duration.between(startTime, endTime);
        // 22:26:42,411  INFO BatchUpdateLockConflictTest:95 - 完成全部批量更新： -> 耗时PT1M4.423S秒
        LOGGER.info("完成全部批量更新： -> 耗时{}秒", between);
    }

    /**
     * 测试并发分页批量更新 - 间隔索引键值：10个线程
     * 完成全部批量更新： -> 耗时PT46.302S秒
     */
    @Test
    public void testBatchUpdateLockConflictWithConcurrency() {
        String driverName = "com.mysql.cj.jdbc.Driver";
        // String driverName = "com.mysql.jdbc.Driver"; // mysql-connector-java 5.*使用
        String url = "jdbc:mysql://192.168.2.101:3306/db_test?useSSL=true&characterEncoding=utf-8&serverTimezone=GMT";
        String user = "root";
        String password = "000000";

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sqlUpdate = "UPDATE tb_test_lock SET v_1=v_1+1,v_3=v_3+1,v_4=v_4+1,v_5=v_5+1,v_6=v_6+1,v_7=v_7+1,v_8=v_8+1 WHERE id BETWEEN ? AND ?;";
        // LOGGER.info("sqlUpdate:{}", sqlUpdate);

        int batchSize = 100 * 10000;
        int idMax = 1000 * 10000;
        int batchCount = idMax / batchSize;
        // List<Integer> idList = IntStream.rangeClosed(1, idMax).boxed().collect(Collectors.toList());

        LocalTime startTime = LocalTime.now();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // ExecutorService executorService = Executors.newFixedThreadPool(1);
        // 1. 间隔批量更新
        List<Future<?>> futureList = new ArrayList<>();
        for (int i = 1; i <= batchCount; i++) {
            int finalI = i;
            Future<?> future = executorService.submit(() -> {
                try {
                    LocalTime innerStartTime = LocalTime.now();
                    Connection connection = DriverManager.getConnection(url, user, password);
                    PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
                    int startIndex = (finalI - 1) * batchSize + 1;
                    int endIndex = finalI * batchSize - 1;
                    preparedStatement.setInt(1, startIndex);
                    preparedStatement.setInt(2, endIndex);
                    int updateCount = preparedStatement.executeUpdate();
                    LocalTime innerEndTime = LocalTime.now();
                    Duration innerBetween = Duration.between(innerStartTime, innerEndTime);
                    /*
                    22:38:23,428  INFO BatchUpdateLockConflictTest:153 - 完成单次批量更新： -> startIndex:9000001, endIndex:9999999, updateCount:999999, 耗时PT32.825S秒
                    22:38:24,068  INFO BatchUpdateLockConflictTest:153 - 完成单次批量更新： -> startIndex:4000001, endIndex:4999999, updateCount:999999, 耗时PT33.474S秒
                    22:38:29,853  INFO BatchUpdateLockConflictTest:153 - 完成单次批量更新： -> startIndex:1, endIndex:999999, updateCount:999999, 耗时PT39.258S秒
                    22:38:29,854  INFO BatchUpdateLockConflictTest:153 - 完成单次批量更新： -> startIndex:1000001, endIndex:1999999, updateCount:999999, 耗时PT39.259S秒
                    22:38:29,854  INFO BatchUpdateLockConflictTest:153 - 完成单次批量更新： -> startIndex:2000001, endIndex:2999999, updateCount:999999, 耗时PT39.259S秒
                    22:38:29,854  INFO BatchUpdateLockConflictTest:153 - 完成单次批量更新： -> startIndex:5000001, endIndex:5999999, updateCount:999999, 耗时PT39.259S秒
                    22:38:36,845  INFO BatchUpdateLockConflictTest:153 - 完成单次批量更新： -> startIndex:6000001, endIndex:6999999, updateCount:999999, 耗时PT46.249S秒
                    22:38:36,845  INFO BatchUpdateLockConflictTest:153 - 完成单次批量更新： -> startIndex:3000001, endIndex:3999999, updateCount:999999, 耗时PT46.249S秒
                    22:38:36,844  INFO BatchUpdateLockConflictTest:153 - 完成单次批量更新： -> startIndex:7000001, endIndex:7999999, updateCount:999999, 耗时PT46.249S秒
                    22:38:36,849  INFO BatchUpdateLockConflictTest:153 - 完成单次批量更新： -> startIndex:8000001, endIndex:8999999, updateCount:999999, 耗时PT46.254S秒
                     */
                    LOGGER.info("完成单次批量更新： -> startIndex:{}, endIndex:{}, updateCount:{}, 耗时{}秒", startIndex, endIndex, updateCount, innerBetween);
                } catch (Exception e) {
                    e.printStackTrace();
                    LOGGER.info("单次批量更新发生异常，异常信息为： -> {}：{}", e.getClass().getName(), e.getMessage());
                }
            });
            futureList.add(future);
        }

        for (Future<?> future : futureList) {
            while (!future.isDone()) {
                Thread.yield();
            }
        }

        // 2. 更新间隔值
        String sqlIntervalUpdate = "UPDATE tb_test_lock SET v_1=v_1+1,v_3=v_3+1,v_4=v_4+1,v_5=v_5+1,v_6=v_6+1,v_7=v_7+1,v_8=v_8+1 ";
        List<String> placeholderList = new ArrayList<>();
        List<Integer> idList = new ArrayList<>();
        for (int i = 1; i <= batchCount; i++) {
            placeholderList.add("?");
            idList.add(i * batchSize);
        }
        String joinPlaceholderStr = StringUtils.join(placeholderList, ",");
        sqlIntervalUpdate = sqlIntervalUpdate + "WHERE id IN (" + joinPlaceholderStr + ")";

        try {
            LocalTime intervalStartTime = LocalTime.now();
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlIntervalUpdate);
            for (int i = 1; i <= batchCount; i++) {
                preparedStatement.setInt(i, idList.get(i-1));
            }
            int updateCount = preparedStatement.executeUpdate();
            LocalTime intervalEndTime = LocalTime.now();
            Duration intervalBetween = Duration.between(intervalStartTime, intervalEndTime);
            // 22:38:36,892  INFO BatchUpdateLockConflictTest:190 - 完成间隔数据更新： -> 更新数据条数：10, 更新耗时：PT0.037S秒
            LOGGER.info("完成间隔数据更新： -> 更新数据条数：{}, 更新耗时：{}秒", updateCount, intervalBetween);
        } catch (Exception e){
            // e.printStackTrace();
            LOGGER.info("单次批量更新发生异常，异常信息为： -> {}：{}", e.getClass().getName(), e.getMessage());
        }

        LocalTime endTime = LocalTime.now();
        Duration between = Duration.between(startTime, endTime);
        // 22:38:36,893  INFO BatchUpdateLockConflictTest:199 - 完成全部批量更新： -> 耗时PT46.302S秒
        LOGGER.info("完成全部批量更新： -> 耗时{}秒", between);
    }


    /**
     * 测试并发分页批量更新 - 不间隔索引键值：10个线程
     * 抛出异常：com.mysql.cj.jdbc.exceptions.MySQLTransactionRollbackException：Lock wait timeout exceeded; try restarting transaction
     *
     */
    @Test
    public void testBatchUpdateLockConflictWithConcurrency2() {

        String driverName = "com.mysql.cj.jdbc.Driver";
        // String driverName = "com.mysql.jdbc.Driver"; // mysql-connector-java 5.*使用
        String url = "jdbc:mysql://192.168.2.101:3306/db_test?useSSL=true&characterEncoding=utf-8&serverTimezone=GMT";
        String user = "root";
        String password = "000000";

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sqlUpdate = "UPDATE tb_test_lock SET v_1=v_1+1,v_3=v_3+1,v_4=v_4+1,v_5=v_5+1,v_6=v_6+1,v_7=v_7+1,v_8=v_8+1 WHERE id BETWEEN ? AND ?;";

        int batchSize = 100 * 10000;
        int idMax = 1000 * 10000;
        int batchCount = idMax / batchSize;
        // List<Integer> idList = IntStream.rangeClosed(1, idMax).boxed().collect(Collectors.toList());

        LocalTime startTime = LocalTime.now();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<?>> futureList = new ArrayList<>();
        for (int i = 1; i <= batchCount; i++) {
            int finalI = i;
            Future<?> future = executorService.submit(() -> {
                try {
                    LocalTime innerStartTime = LocalTime.now();
                    Connection connection = DriverManager.getConnection(url, user, password);
                    PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
                    int startIndex = (finalI - 1) * batchSize + 1;
                    // int endIndex = finalI * batchSize - 1;
                    int endIndex = finalI * batchSize;
                    preparedStatement.setInt(1, startIndex);
                    preparedStatement.setInt(2, endIndex);
                    int updateCount = preparedStatement.executeUpdate();
                    LocalTime innerEndTime = LocalTime.now();
                    Duration innerBetween = Duration.between(innerStartTime, innerEndTime);
                    /*
                    23:08:21,327  INFO BatchUpdateLockConflictTest:260 - 完成单次批量更新： -> startIndex:9000001, endIndex:10000000, updateCount:1000000, 耗时PT22.558S秒
                    23:08:53,561  INFO BatchUpdateLockConflictTest:263 - 单次批量更新发生异常，异常信息为： -> com.mysql.cj.jdbc.exceptions.MySQLTransactionRollbackException：Lock wait timeout exceeded; try restarting transaction
                    23:09:06,073  INFO BatchUpdateLockConflictTest:263 - 单次批量更新发生异常，异常信息为： -> com.mysql.cj.jdbc.exceptions.MySQLTransactionRollbackException：Lock wait timeout exceeded; try restarting transaction
                    23:09:07,171  INFO BatchUpdateLockConflictTest:263 - 单次批量更新发生异常，异常信息为： -> com.mysql.cj.jdbc.exceptions.MySQLTransactionRollbackException：Lock wait timeout exceeded; try restarting transaction
                    23:09:08,229  INFO BatchUpdateLockConflictTest:263 - 单次批量更新发生异常，异常信息为： -> com.mysql.cj.jdbc.exceptions.MySQLTransactionRollbackException：Lock wait timeout exceeded; try restarting transaction
                    23:09:08,454  INFO BatchUpdateLockConflictTest:263 - 单次批量更新发生异常，异常信息为： -> com.mysql.cj.jdbc.exceptions.MySQLTransactionRollbackException：Lock wait timeout exceeded; try restarting transaction
                    23:09:08,550  INFO BatchUpdateLockConflictTest:263 - 单次批量更新发生异常，异常信息为： -> com.mysql.cj.jdbc.exceptions.MySQLTransactionRollbackException：Lock wait timeout exceeded; try restarting transaction
                    23:09:09,110  INFO BatchUpdateLockConflictTest:263 - 单次批量更新发生异常，异常信息为： -> com.mysql.cj.jdbc.exceptions.MySQLTransactionRollbackException：Lock wait timeout exceeded; try restarting transaction
                    23:09:09,127  INFO BatchUpdateLockConflictTest:263 - 单次批量更新发生异常，异常信息为： -> com.mysql.cj.jdbc.exceptions.MySQLTransactionRollbackException：Lock wait timeout exceeded; try restarting transaction
                    23:09:09,195  INFO BatchUpdateLockConflictTest:263 - 单次批量更新发生异常，异常信息为： -> com.mysql.cj.jdbc.exceptions.MySQLTransactionRollbackException：Lock wait timeout exceeded; try restarting transaction
                     */
                    LOGGER.info("完成单次批量更新： -> startIndex:{}, endIndex:{}, updateCount:{}, 耗时{}秒", startIndex, endIndex, updateCount, innerBetween);
                } catch (Exception e) {
                    // e.printStackTrace();
                    LOGGER.info("单次批量更新发生异常，异常信息为： -> {}：{}", e.getClass().getName(), e.getMessage());
                }
            });
            futureList.add(future);
        }

        for (Future<?> future : futureList) {
            while (!future.isDone()) {
                Thread.yield();
            }
        }

        LocalTime endTime = LocalTime.now();
        Duration between = Duration.between(startTime, endTime);
        // 23:09:09,196  INFO BatchUpdateLockConflictTest:278 - 完成全部批量更新： -> 耗时PT1M10.438S秒
        LOGGER.info("完成全部批量更新： -> 耗时{}秒", between);
    }

}
