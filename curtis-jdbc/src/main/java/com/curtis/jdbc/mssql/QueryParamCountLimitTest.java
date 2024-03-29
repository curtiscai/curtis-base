package com.curtis.jdbc.mssql;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * @author curtis.cai
 * @desc SQL Server查询参数个数限制测试
 * @date 2021-07-17
 * @email curtis.cai@outlook.com
 * @reference
 */
public class QueryParamCountLimitTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryParamCountLimitTest.class);

    /*******************************************************************************************************************/
    /***************************************** SQL数据查询时参数限制：参数个数 **********************************************/
    /*******************************************************************************************************************/

    /**
     * SQL Server直接使用拼接后的SQL去查询的方式没有参数方面的限制
     */
    @Test
    public void testQueryParamCountLimitWithStatement() {
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=db_test";
        String user = "sa";
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
     * SQL Server使用预编译的SQL去查询的方式有参数方面的限制（2100）
     */
    @Test
    public void testQueryParamCountLimitWithPrepareStatement() {
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=db_test";
        String user = "sa";
        String password = "000000";
        try {
            Class.forName(driverName);

            String sqlSelect = "SELECT COUNT(*),MAX(a1),MIN(a1) FROM temp_test_batch_limit WHERE a1 IN (";
            StringBuilder sqlValue = new StringBuilder();
            String sql = "";

//            int queryParamCount = 2100;
//            int queryParamCount = 2098; // test OK
//            int queryParamCount = 2099; // test Error com.microsoft.sqlserver.jdbc.SQLServerException: 传入的请求具有过多的参数。该服务器支持最多 2100 个参数。请减少参数的数目，然后重新发送该请求。
            int queryParamCount = 2099; // test Error com.microsoft.sqlserver.jdbc.SQLServerException: 传入的请求具有过多的参数。该服务器支持最多 2100 个参数。请减少参数的数目，然后重新发送该请求。
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
