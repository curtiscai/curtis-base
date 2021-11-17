package com.curtis.guava.collections;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * @author curtis.cai
 * @desc Guava Collection之Table api的使用
 * @date 2021-11-18
 * @email curtis.cai@outlook.com
 * @reference
 */
public class TableTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TableTest.class);

    /**
     * 类似于Map<rowKey,Map<columnKey,value>>
     */
    @Test
    public void testTable() {
        Table<String, String, Integer> hashBasedTable = HashBasedTable.create();
        hashBasedTable.put("北京", "2020", 1);
        hashBasedTable.put("北京", "2021", 1);
        hashBasedTable.put("上海", "2020", 2);
        hashBasedTable.put("上海", "2021", 3);
        // 22:41:19,768  INFO TableTest:27 - hashBasedTable -> {北京={2020=1, 2021=1}, 上海={2020=2, 2021=3}}
        LOGGER.info("hashBasedTable -> {}", hashBasedTable);


        Set<String> rowKeySet = hashBasedTable.rowKeySet();
        // 22:43:53,239  INFO TableTest:35 - rowKeySet -> [北京, 上海]
        LOGGER.info("rowKeySet -> {}", rowKeySet);

        Set<String> columnKeySet = hashBasedTable.columnKeySet();
        // 22:43:53,242  INFO TableTest:38 - columnKeySet -> [2020, 2021]
        LOGGER.info("columnKeySet -> {}", columnKeySet);


        Map<String, Integer> row = hashBasedTable.row("北京");
        // 22:43:53,242  INFO TableTest:42 - row -> {2020=1, 2021=1}
        LOGGER.info("row -> {}", row);

        Map<String, Integer> column = hashBasedTable.column("2021");
        // 22:43:53,243  INFO TableTest:45 - column -> {北京=1, 上海=3}
        LOGGER.info("column -> {}", column);


        Integer value = hashBasedTable.get("北京", "2021");
        // 22:43:53,244  INFO TableTest:49 - value -> 1
        LOGGER.info("value -> {}", value);

        Set<Table.Cell<String, String, Integer>> cells = hashBasedTable.cellSet();
        // 22:54:34,963  INFO TableTest:60 - cells -> [(北京,2020)=1, (北京,2021)=1, (上海,2020)=2, (上海,2021)=3]
        LOGGER.info("cells -> {}", cells);

    }
}
