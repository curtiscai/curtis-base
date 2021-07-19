package com.curtis.jdbc.mysql;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * @author curtis.cai
 * @desc MySQL最大允许数据包测试
 * @date 2021-07-17
 * @email curtis.cai@outlook.com
 * @reference
 */
public class MaxAllowedPacketTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MaxAllowedPacketTest.class);

    /*******************************************************************************************************************/
    /*************************************** MySQL允许最大SQL数据包限制：默认4M ********************************************/
    /*******************************************************************************************************************/

    /**
     * MySQL批量插入只有数据包大小限制
     */
    @Test
    public void testMaxAllowedPacketLimitWithBatchInsert() {
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

//            int insertRowCount = 4095; // test OK
            int insertRowCount = 4096; // Error:com.mysql.cj.jdbc.exceptions.PacketTooBigException: Packet for query is too large (4,194,386 > 4,194,304).
            // You can change this value on the server by setting the 'max_allowed_packet' variable.
            for (int i = 1; i <= insertRowCount; i++) {
                sqlValue.append("(23456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,12345678),");
                sqlValue.append("(23456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,12345678),");
                sqlValue.append("(23456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,12345678),");
                sqlValue.append("(23456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,12345678),");
                sqlValue.append("(23456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,12345678),");
                sqlValue.append("(23456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,12345678),");
                sqlValue.append("(23456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,12345678),");
                sqlValue.append("(23456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,12345678),");
                sqlValue.append("(23456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,12345678),");
                sqlValue.append("(23456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,12345678),");
                sqlValue.append("(1,1,1,1,1,1,1,1,1,123),");
            }
            sql = sqlInsert + StringUtils.removeEnd(sqlValue.toString(), ",");
            LOGGER.info("sql:{}", sql);

            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            int insertCount = statement.executeUpdate(sql);
            // The result is:45045
            LOGGER.info("The result is:{}", insertCount);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("Error:{}", e.getMessage());
        }
    }

    /**
     * MySQL查询只有数据包大小限制
     */
    @Test
    public void testMaxAllowedPacketLimitWithQuery() {
        String driverName = "com.mysql.cj.jdbc.Driver";
        // String driverName = "com.mysql.jdbc.Driver"; // mysql-connector-java 5.*使用
        String url = "jdbc:mysql://192.168.2.101:3306/db_test?useSSL=true&characterEncoding=utf-8&serverTimezone=GMT";
        String user = "root";
        String password = "000000";
        try {
            Class.forName(driverName);

            String sqlSelect = "SELECT COUNT(*) FROM temp_test_batch_limit WHERE a1 IN (";
            StringBuilder sqlValue = new StringBuilder();
            String sql;
//            int queryParamCount = 4095; // test OK
            int queryParamCount = 4096; // test Error com.mysql.cj.jdbc.exceptions.PacketTooBigException: Packet for query is too large (4,194,361 > 4,194,304).
            // You can change this value on the server by setting the 'max_allowed_packet' variable.
            for (int i = 1; i <= queryParamCount; i++) {
                sqlValue.append("123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,");
                sqlValue.append("123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,");
                sqlValue.append("123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,");
                sqlValue.append("123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,");
                sqlValue.append("123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,");
                sqlValue.append("123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,");
                sqlValue.append("123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,");
                sqlValue.append("123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,");
                sqlValue.append("123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,");
                sqlValue.append("123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,123456789,");
                sqlValue.append("11,1,1,1,1,1,1,1,1,1234,");
            }
            sql = sqlSelect + StringUtils.removeEnd(sqlValue.toString(), ",") + ")";
            LOGGER.info("sql:{}", sql);

            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int result = resultSet.getInt(1);
                LOGGER.info("The result is:{}", result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("Error:{}", e.getMessage());
        }
    }
}
