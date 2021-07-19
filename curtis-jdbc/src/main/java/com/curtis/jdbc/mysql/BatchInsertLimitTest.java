package com.curtis.jdbc.mysql;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * @author curtis.cai
 * @desc MySQL查询参数个数限制测试
 * @date 2021-07-17
 * @email curtis.cai@outlook.com
 * @reference
 */
public class BatchInsertLimitTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchInsertLimitTest.class);

    /*******************************************************************************************************************/
    /*************************************** MySQL批量插入时无行数和参数个数 ********************************************/
    /*******************************************************************************************************************/

    /**
     * MySQL直接执行拼接后的批量插入的SQL的方式无行数限制，也无参数个数限制
     */
    @Test
    public void testBatchInsertLimitWithStatement() {
        String driverName = "com.mysql.cj.jdbc.Driver";
        // String driverName = "com.mysql.jdbc.Driver"; // mysql-connector-java 5.*使用
        String url = "jdbc:mysql://192.168.2.101:3306/db_test?useSSL=true&characterEncoding=utf-8&serverTimezone=GMT";
        String user = "root";
        String password = "000000";
        try {
            Class.forName(driverName);

            String sqlInsert = "INSERT INTO temp_test_batch_limit(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10) VALUES ";
            StringBuilder sqlValue = new StringBuilder();
            String sql = "";

            // int insertRowCount = 10000; // Error:com.mysql.cj.jdbc.exceptions.PacketTooBigException: Packet for query is too large (5,089,482 > 4,194,304). You can change this value on the server by setting the 'max_allowed_packet' variable.
            int insertRowCount = 8000;
            for (int i = 1; i <= insertRowCount; i++) {
                sqlValue.append("(").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append("),");
                sqlValue.append("(").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append("),");
                sqlValue.append("(").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append("),");
                sqlValue.append("(").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append("),");
                sqlValue.append("(").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append("),");
                sqlValue.append("(").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append("),");
                sqlValue.append("(").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append("),");
                sqlValue.append("(").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append("),");
                sqlValue.append("(").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append("),");
                sqlValue.append("(").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append(",").append(i).append("),");
            }
            sql = sqlInsert + StringUtils.removeEnd(sqlValue.toString(), ",");
            LOGGER.info("sql:{}", sql);

            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            int insertCount = statement.executeUpdate(sql);
            // The result is:80000
            LOGGER.info("The result is:{}", insertCount);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("Error:{}", e.getMessage());
        }
    }

    /**
     * MySQL使用预编译的批量插入的SQL的方式无行数限制，也无参数个数限制
     */
    @Test
    public void testBatchInsertLimitWithPrepareStatement1() {
        String driverName = "com.mysql.cj.jdbc.Driver";
        // String driverName = "com.mysql.jdbc.Driver"; // mysql-connector-java 5.*使用
        String url = "jdbc:mysql://192.168.2.101:3306/db_test?useSSL=true&characterEncoding=utf-8&serverTimezone=GMT";
        String user = "root";
        String password = "000000";
        try {
            Class.forName(driverName);

            String sqlInsert = "INSERT INTO temp_test_batch_limit(a1) VALUES ";
            StringBuilder sqlValue = new StringBuilder();
            String sql = "";

            int insertRowCount = 10000;
            for (int i = 1; i <= insertRowCount; i++) {
                sqlValue.append("(?),");
            }
            sql = sqlInsert + StringUtils.removeEnd(sqlValue.toString(), ",");
            LOGGER.info("sql:{}", sql);

            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 1; i <= insertRowCount; i++) {
                preparedStatement.setInt(i, i);
            }
            int insertCount = preparedStatement.executeUpdate();
            // The result is:10000
            LOGGER.info("The result is:{}", insertCount);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("Error:{}", e.getMessage());
        }
    }

    /**
     * MySQL使用预编译的批量插入的SQL的方式无行数限制，也无参数个数限制
     */
    @Test
    public void testBatchInsertLimitWithPrepareStatement2() {
        String driverName = "com.mysql.cj.jdbc.Driver";
        // String driverName = "com.mysql.jdbc.Driver"; // mysql-connector-java 5.*使用
        String url = "jdbc:mysql://192.168.2.101:3306/db_test?useSSL=true&characterEncoding=utf-8&serverTimezone=GMT";
        String user = "root";
        String password = "000000";
        try {
            Class.forName(driverName);

            String sqlInsert = "INSERT INTO temp_test_batch_limit(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10) VALUES ";
            StringBuilder sqlValue = new StringBuilder();
            String sql = "";

            int insertRowCount = 10000;
            for (int i = 1; i <= insertRowCount; i++) {
                sqlValue.append("(?,?,?,?,?,?,?,?,?,?),");
            }
            sql = sqlInsert + StringUtils.removeEnd(sqlValue.toString(), ",");
            LOGGER.info("sql:{}", sql);

            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 1; i <= insertRowCount; i++) {
                preparedStatement.setInt((i - 1) * 10 + 1, i);
                preparedStatement.setInt((i - 1) * 10 + 2, i);
                preparedStatement.setInt((i - 1) * 10 + 3, i);
                preparedStatement.setInt((i - 1) * 10 + 4, i);
                preparedStatement.setInt((i - 1) * 10 + 5, i);
                preparedStatement.setInt((i - 1) * 10 + 6, i);
                preparedStatement.setInt((i - 1) * 10 + 7, i);
                preparedStatement.setInt((i - 1) * 10 + 8, i);
                preparedStatement.setInt((i - 1) * 10 + 9, i);
                preparedStatement.setInt((i - 1) * 10 + 10, i);
            }
            int insertCount = preparedStatement.executeUpdate();
            // The result is:10000
            LOGGER.info("The result is:{}", insertCount);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("Error:{}", e.getMessage());
        }
    }
}
