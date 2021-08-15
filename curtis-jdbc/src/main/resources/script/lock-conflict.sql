-- 创建用于测试锁冲突的数据表
DROP TABLE IF EXISTS tb_test_lock;
CREATE TABLE IF NOT EXISTS tb_test_lock (
    id int(11) NOT NULL,
    v_1 int(11) DEFAULT NULL,
    v_2 int(11) DEFAULT NULL,
    v_3 int(11) DEFAULT NULL,
    v_4 int(11) DEFAULT NULL,
    v_5 int(11) DEFAULT NULL,
    v_6 int(11) DEFAULT NULL,
    v_7 int(11) DEFAULT NULL,
    v_8 int(11) DEFAULT NULL,
    v_9 int(11) DEFAULT NULL,
    v_10 int(11) DEFAULT NULL,
    v_11 int(11) DEFAULT NULL,
    v_12 int(11) DEFAULT NULL,
    v_13 int(11) DEFAULT NULL,
    v_14 int(11) DEFAULT NULL,
    v_15 int(11) DEFAULT NULL,
    v_16 int(11) DEFAULT NULL,
    v_17 int(11) DEFAULT NULL,
    v_18 int(11) DEFAULT NULL,
    v_19 int(11) DEFAULT NULL,
    v_20 int(11) DEFAULT NULL,
    PRIMARY KEY (id),
    KEY idx_v_2 (v_2)
) ENGINE=InnoDB DEFAULT CHARSET utf8 COMMENT '用于测试锁的数据表';

-- 插入测试数据
INSERT INTO tb_test_lock
    (id, v_1, v_2)
VALUES
    (1,1,2),(2,2,2),(3,3,3),(4,4,4),(5,5,5),(6,6,6),(7,7,7),(8,8,8),(9,9,9),(10,10,10),
    (11,11,11),(12,12,12),(13,13,13),(14,14,14),(15,15,15),(16,16,16),(17,17,17),(18,18,18),(19,19,19),(20,20,20),
    (21,21,22),(22,22,22),(23,23,23),(24,24,24),(25,25,25),(26,26,26),(27,27,27),(28,28,28),(29,29,29),(30,30,30),
    (31,31,32),(32,32,32),(33,33,33),(34,34,34),(35,35,35),(36,36,36),(37,37,37),(38,38,38),(39,39,39),(40,40,40),
    (51,51,52),(52,52,52),(53,53,53),(54,54,54),(55,55,55),(56,56,56),(57,57,57),(58,58,58),(59,59,59),(60,60,60);


-- 创建用于生成任意条数测试数据的存储过程
DROP PROCEDURE IF EXISTS prod_create_tb_test_lock_data;
CREATE PROCEDURE prod_create_tb_test_lock_data(IN num INT)
BEGIN
  DECLARE i INT DEFAULT 0;
  DECLARE exec_sql TEXT CHARSET utf8mb4 DEFAULT '';
  SET exec_sql := 'INSERT INTO tb_test_lock(id, v_1, v_2, v_3, v_4, v_5, v_6, v_7, v_8, v_9, v_10, v_11, v_12, v_13, v_14, v_15, v_16, v_17, v_18, v_19, v_20) VALUES ';
  WHILE i < num DO
        SET @val = i+1;
        SET @insert_val := CONCAT_WS(',', @val, @val, @val, @val, @val, @val, @val, @val, @val, @val, @val, @val, @val, @val, @val, @val, @val, @val, @val, @val, @val);
        SET @insert_val := CONCAT('(', @insert_val, ')', ',');
        SET exec_sql := CONCAT(exec_sql, @insert_val);
        SET i := i + 1;
        IF (i % 1000 = 0) THEN
          SET @exec_sql := LEFT(exec_sql, CHAR_LENGTH(exec_sql) - 1);
#           SELECT @exec_sql;
          PREPARE stmt FROM @exec_sql;
          EXECUTE stmt;
          DEALLOCATE PREPARE stmt;
          SET exec_sql := 'INSERT INTO tb_test_lock(id, v_1, v_2, v_3, v_4, v_5, v_6, v_7, v_8, v_9, v_10, v_11, v_12, v_13, v_14, v_15, v_16, v_17, v_18, v_19, v_20) VALUES ';
        END IF;
  END WHILE;
END;

-- 生成100w测试数据
-- completed in 1 m 34 s 512 ms
CALL prod_create_tb_test_lock_data(100 * 10000);
