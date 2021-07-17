-- 用于测试批量提交数据的表-10个Int字段
CREATE TABLE temp_test_batch_limit(
    a1 INT,
    a2 INT,
    a3 INT,
    a4 INT,
    a5 INT,
    a6 INT,
    a7 INT,
    a8 INT,
    a9 INT,
    a10 INT
) COMMENT '用于测试批量提交数据的表-10个Int字段';

-- 用于存储生成的SQL语句的数据表
CREATE TABLE temp_test_sql_text(
    sql_str LONGTEXT
) COMMENT '用于存储生成的SQL语句的数据表';