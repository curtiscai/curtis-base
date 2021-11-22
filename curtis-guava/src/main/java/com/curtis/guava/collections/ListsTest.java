package com.curtis.guava.collections;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author curtis.cai
 * @desc Guava Collection之Lists api的使用
 * @date 2021-11-17
 * @email curtis.cai@outlook.com
 * @reference
 */
public class ListsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListsTest.class);

    /**
     * 分区/分页（非常常用，常用于内存分页或者将数据分页后分批处理的场景）
     */
    @Test
    public void testPartition() {
        List<String> strList = Lists.newArrayList("北京", "天津", "上海", "石家庄", "保定");
        List<List<String>> partition = Lists.partition(strList, 2);
        // 20:42:04,830  INFO ListsExample:58 - partition -> [[北京, 天津], [上海, 石家庄], [保定]]
        LOGGER.info("partition -> {}", partition);
    }

    /**
     * 使用传入的多个List集合入参按顺序构造笛卡尔List<List<>>
     */
    @Test
    public void testCartesianProduct() {
        List<String> strList = Lists.newArrayList("北京", "上海");
        List<String> intList = Lists.newArrayList("2021", "2022");
        List<List<String>> cartesianLists = Lists.cartesianProduct(strList, intList);
        // 20:23:16,581  INFO ListsExample:27 - cartesianLists -> [[北京, 2021], [北京, 2022], [上海, 2021], [上海, 2022]]
        LOGGER.info("cartesianLists -> {}", cartesianLists);
    }

    /**
     * 转换指定集合中的每一个元素
     */
    @Test
    public void testTransform() {
        List<String> strList = Lists.newArrayList("china", "german");

        // Lists Api写法
        List<String> transform1 = Lists.transform(strList, item -> item.toUpperCase());
        // 20:33:11,099  INFO ListsExample:44 - transform1 -> [CHINA, GERMAN]
        LOGGER.info("transform1 -> {}", transform1);
        // Java Stream Api写法
        List<String> transform2 = strList.stream().map(String::toUpperCase).collect(Collectors.toList());
        // 20:33:11,105  INFO ListsExample:47 - transform2 -> [CHINA, GERMAN]
        LOGGER.info("transform2 -> {}", transform2);
    }
}
