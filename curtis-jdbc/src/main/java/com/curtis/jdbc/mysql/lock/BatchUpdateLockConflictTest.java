package com.curtis.jdbc.mysql.lock;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
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

    @Test
    public void testBatchUpdateLockConflict1() {

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
        LOGGER.info("sqlUpdate:{}", sqlUpdate);

        int batchSize = 100 * 10000;
        int idMax = 1000 * 10000;
        int batchCount = idMax / batchSize;
        List<Integer> idList = IntStream.rangeClosed(1, idMax).boxed().collect(Collectors.toList());

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Future> futureList = new ArrayList<>();
        for (int i = 1; i <= batchCount; i++) {
            int finalI = i;
            Future<?> future = executorService.submit(() -> {
                try {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
                    int startIndex = (finalI - 1) * batchSize + 1;
                    // int endIndex = finalI * batchSize - 1;
                    int endIndex = finalI * batchSize;
                    preparedStatement.setInt(1, startIndex);
                    preparedStatement.setInt(2, endIndex);
                    int updateCount = preparedStatement.executeUpdate();
                    LOGGER.info("startIndex:{},endIndex:{},updateCount:{}", startIndex, endIndex, updateCount);
                } catch (Exception e) {
                    e.printStackTrace();
                    LOGGER.info("Error:{}", e.getMessage());
                }
            });
            futureList.add(future);
        }

        for (Future future : futureList) {
            while (!future.isDone()) {
                Thread.yield();
            }
        }
    }

    /**
     * MySQL批量更新锁冲突测试
     */
    @Test
    public void testBatchUpdateLockConflict2() {
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

        String sqlUpdate = "UPDATE tb_test_lock SET v_1=v_1+1 WHERE id BETWEEN ? AND ?;";
        LOGGER.info("sqlUpdate:{}", sqlUpdate);

        int batchSize = 100 * 1000;
        int idMax = 1000 * 10000;
        int batchCount = idMax / batchSize;
        List<Integer> idList = IntStream.rangeClosed(1, idMax).boxed().collect(Collectors.toList());

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Future> futureList = new ArrayList<>();
        for (int i = 1; i <= batchCount; i++) {
            int finalI = i;
            Future<?> future = executorService.submit(() -> {
                try {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
                    int startIndex = (finalI - 1) * batchSize + 1;
                    int endIndex = finalI * batchSize - 1;
                    preparedStatement.setInt(1, startIndex);
                    preparedStatement.setInt(2, endIndex);
                    int updateCount = preparedStatement.executeUpdate();
                    LOGGER.info("startIndex:{},endIndex:{},updateCount:{}", startIndex, endIndex, updateCount);
                } catch (Exception e) {
                    e.printStackTrace();
                    LOGGER.info("Error:{}", e.getMessage());
                }
            });
            futureList.add(future);
        }

        for (Future future : futureList) {
            while (!future.isDone()) {
                Thread.yield();
            }
        }
    }

    @Test
    public void testBatchUpdateLockConflict3() {
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

        String sqlUpdate = "UPDATE tb_test_lock SET v_1=v_1+1 WHERE id BETWEEN ? AND ?;";
        LOGGER.info("sqlUpdate:{}", sqlUpdate);

        int batchSize = 100000;
        int idMax = 100 * 10000;
        int batchCount = idMax / batchSize;
        List<Integer> idList = IntStream.rangeClosed(1, idMax).boxed().collect(Collectors.toList());

        for (int i = 1; i <= batchCount; i++) {
            int finalI = i;
            try {
                Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
                int startIndex = (finalI - 1) * batchSize + 1;
                int endIndex = finalI * batchSize - 1;
                preparedStatement.setInt(1, startIndex);
                preparedStatement.setInt(2, endIndex);
                int updateCount = preparedStatement.executeUpdate();
                LOGGER.info("startIndex:{},endIndex:{},updateCount:{}", startIndex, endIndex, updateCount);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.info("Error:{}", e.getMessage());
            }
        }
    }
}
