package com.curtis.jdbc.mysql;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * @author curtis.cai
 * @desc MySQL查询参数个数无限制测试
 * @date 2021-07-17
 * @email curtis.cai@outlook.com
 * @reference
 */
public class QueryParamCountLimitTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryParamCountLimitTest.class);

    /*******************************************************************************************************************/
    /***************************************** MySQL数据查询时无参数个数限制 **********************************************/
    /*******************************************************************************************************************/

    /**
     * MySQL直接使用拼接后的SQL去查询的方式没有参数方面的限制
     */
    @Test
    public void testQueryParamCountLimitWithStatement() {
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
            int queryParamCount = 10000;
            for (int i = 1; i <= queryParamCount; i++) {
                sqlValue.append(i).append(",");
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

    /**
     * MySQL使用预编译的SQL去查询的方式也无参数方面的限制
     */
    @Test
    public void testQueryParamCountLimitWithPrepareStatement() {
        String driverName = "com.mysql.cj.jdbc.Driver";
        // String driverName = "com.mysql.jdbc.Driver"; // mysql-connector-java 5.*使用
        String url = "jdbc:mysql://192.168.2.101:3306/db_test?useSSL=true&characterEncoding=utf-8&serverTimezone=GMT";
        String user = "root";
        String password = "000000";
        try {
            Class.forName(driverName);

            String sqlSelect = "SELECT COUNT(*),MAX(a1),MIN(a1) FROM temp_test_batch_limit WHERE a1 IN (";
            StringBuilder sqlValue = new StringBuilder();
            String sql = "";

            int queryParamCount = 10000;
            for (int i = 1; i <= queryParamCount; i++) {
                sqlValue.append("?").append(",");
            }
            sql = sqlSelect + StringUtils.removeEnd(sqlValue.toString(), ",") + ")";
            LOGGER.info("sql:{}", sql);

            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 1; i <= queryParamCount; i++) {
                preparedStatement.setInt(i, i);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
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
