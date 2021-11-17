package com.curtis.guava.collections;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author curtis.cai
 * @desc Guava Collection之Sets api的使用
 * @date 2021-11-17
 * @email curtis.cai@outlook.com
 * @reference
 */
public class SetsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SetsTest.class);

    /**
     * List转无序或者有序Set（工作中非常常用）
     */
    @Test
    public void testListToSet() {
        List<String> cityList = Lists.newArrayList("北京", "天津", "上海", "天津");
        // 使用List构建无序Set
        Set<String> citySet = Sets.newHashSet(cityList);
        // 21:05:26,326  INFO SetsTest:29 - citySet -> [上海, 天津, 北京]
        LOGGER.info("citySet -> {}", citySet);

        // 使用List构建有序Set
        LinkedHashSet<String> cityLinkedHashSet = Sets.newLinkedHashSet(cityList);
        // 21:06:39,815  INFO SetsTest:33 - cityLinkedHashSet -> [北京, 天津, 上海]
        LOGGER.info("cityLinkedHashSet -> {}", cityLinkedHashSet);

    }

    /**
     * 计算两个Set集合的交并补集（工作中非常常用）
     * 在工作中常常会有参数验证，比如待写入库中数据要验证包含的维度数据是否合法/有效，此时可以通过补集来验证
     */
    @Test
    public void testSetCollectionOperations() {
        Set<String> set1 = Sets.newHashSet("北京", "天津", "上海");
        Set<String> set2 = Sets.newHashSet("北京", "天津", "石家庄");
        // 交集
        Sets.SetView<String> intersection = Sets.intersection(set1, set2);
        // 21:22:27,135  INFO SetsTest:50 - intersection -> [北京, 天津]
        LOGGER.info("intersection -> {}", intersection);

        // 并集
        Sets.SetView<String> union = Sets.union(set1, set2);
        // 21:22:27,138  INFO SetsTest:53 - union -> [上海, 北京, 天津, 石家庄]
        LOGGER.info("union -> {}", union);

        // 补集
        Sets.SetView<String> difference = Sets.difference(set1, set2);
        // 21:22:27,138  INFO SetsTest:56 - difference -> [上海]
        LOGGER.info("difference -> {}", difference);

        // 差集
        Sets.SetView<String> symmetricDifference = Sets.symmetricDifference(set2, set1);
        // 21:22:27,139  INFO SetsTest:59 - symmetricDifference -> [石家庄, 上海]
        LOGGER.info("symmetricDifference -> {}", symmetricDifference);
    }

    @Test
    public void testTreeSet() {
        Set<Integer> hashSet = Sets.newHashSet(1, 3, 2, 4);
        TreeSet<Integer> treeSet = Sets.newTreeSet(hashSet);
        System.out.println(treeSet);
        treeSet.descendingIterator().forEachRemaining(System.out::println);
    }
}
