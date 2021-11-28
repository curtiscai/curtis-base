package com.curtis.apache.collections;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.ListUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-28
 * @email curtis.cai@outlook.com
 * @reference
 */
public class ListUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListUtilsTest.class);

    @Test
    public void testCollection() {
        List<Integer> intList1 = Lists.newArrayList(1, 2, 3);
        List<Integer> intList2 = Lists.newArrayList(3, 4, 5);

        List<Integer> intersection = ListUtils.intersection(intList1, intList2);
        List<Integer> union = ListUtils.union(intList1, intList2);
        List<Integer> subtract = ListUtils.subtract(intList1, intList2);
        List<Integer> sum = ListUtils.sum(intList1, intList2);
        // 23:42:36,241  INFO ListUtilsTest:31 - intersection -> [3], union -> [1, 2, 3, 3, 4, 5], subtract -> [1, 2], sum -> [1, 2, 3, 4, 5]
        LOGGER.info("intersection -> {}, union -> {}, subtract -> {}, sum -> {}", intersection, union, subtract, sum);
    }

    @Test
    public void testPartition() {
        List<Integer> intList = Lists.newArrayList(1, 2, 3);
        List<List<Integer>> partition = ListUtils.partition(intList, 2);
        // 23:43:46,157  INFO ListUtilsTest:39 - partition -> [[1, 2], [3]]
        LOGGER.info("partition -> {}", partition);
    }
}
