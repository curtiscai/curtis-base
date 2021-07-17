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
public class BatchInsertLimitTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchInsertLimitTest.class);


    /*******************************************************************************************************************/
    /*************************************** SQL批量插入时两个限制：行数和参数个数 ********************************************/
    /*******************************************************************************************************************/

    /**
     * 直接执行拼接后的批量插入的SQL的方式有行数1000行的限制，但是无2100个参数的限制
     */
    @Test
    public void testJdbc3() {
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=db_test";
        String user = "sa";
        String password = "000000";
        try {
            Class.forName(driverName);

            String sqlInsert = "INSERT INTO temp_test_batch_limit(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10) VALUES ";
            StringBuilder sqlValue = new StringBuilder();
            String sql = "";

//            int insertRowCount = 100; // (100*10=1000行,10000个参数)test OK
//            int insertRowCount = 101; // (101*10=1010行,10100个参数)test Error com.microsoft.sqlserver.jdbc.SQLServerException: INSERT 语句中行值表达式的数目超出了允许的最大行值数 1000。
            int insertRowCount = 101; // test Error com.microsoft.sqlserver.jdbc.SQLServerException: INSERT 语句中行值表达式的数目超出了允许的最大行值数 1000。
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
            LOGGER.info("The result is:{}", insertCount);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("Error:{}", e.getMessage());
        }
    }

    /**
     *
     */
    @Test
    public void testJdbc4() {
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=db_test";
        String user = "sa";
        String password = "000000";
        try {
            Class.forName(driverName);

            String sqlInsert = "INSERT INTO temp_test_batch_limit(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10) VALUES ";
            StringBuilder sqlValue = new StringBuilder();
            String sql = "";

//            int queryParamCount = 209; // test OK
//            int queryParamCount = 210; // test Error com.microsoft.sqlserver.jdbc.SQLServerException: 传入的请求具有过多的参数。该服务器支持最多 2100 个参数。请减少参数的数目，然后重新发送该请求。
            int insertRowCount = 210;
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
            LOGGER.info("The result is:{}", insertCount);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("Error:{}", e.getMessage());
        }
    }

    @Test
    public void testJdbc5() {
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=db_test";
        String user = "sa";
        String password = "000000";
        try {
            Class.forName(driverName);

            String sqlInsert = "INSERT INTO temp_test_batch_limit(a1) VALUES ";
            StringBuilder sqlValue = new StringBuilder();
            String sql = "";

//            int queryParamCount = 1000; // test OK
//            int queryParamCount = 1000; // test OK
//            int queryParamCount = 1001; // test Error com.microsoft.sqlserver.jdbc.SQLServerException: INSERT 语句中行值表达式的数目超出了允许的最大行值数 1000。
//            int queryParamCount = 2098; // test Error com.microsoft.sqlserver.jdbc.SQLServerException: INSERT 语句中行值表达式的数目超出了允许的最大行值数 1000。
//            int queryParamCount = 2099; // test Error com.microsoft.sqlserver.jdbc.SQLServerException: 传入的请求具有过多的参数。该服务器支持最多 2100 个参数。请减少参数的数目，然后重新发送该请求。
//            int queryParamCount = 2100; // test Error com.microsoft.sqlserver.jdbc.SQLServerException: 传入的请求具有过多的参数。该服务器支持最多 2100 个参数。请减少参数的数目，然后重新发送该请求。
            int insertRowCount = 1001;
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
            LOGGER.info("The result is:{}", insertCount);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("Error:{}", e.getMessage());
        }
    }
}
