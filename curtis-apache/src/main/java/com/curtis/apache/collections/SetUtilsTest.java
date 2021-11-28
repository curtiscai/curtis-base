package com.curtis.apache.collections;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.SetUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-28
 * @email curtis.cai@outlook.com
 * @reference
 */
public class SetUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SetUtilsTest.class);

    @Test
    public void testSetUtils() {
        Set<Integer> intSet1 = Sets.newHashSet(1, 1, 2, 3);
        Set<Integer> intSet2 = Sets.newHashSet(3, 4, 5, 5);

        Set<Integer> intersection = SetUtils.intersection(intSet1, intSet2);
        Set<Integer> union = SetUtils.union(intSet1, intSet2);
        Set<Integer> difference = SetUtils.difference(union, union);
        // 23:50:28,582  INFO SetUtilsTest:34 - intersection -> [3], union -> [1, 2, 3, 4, 5], difference -> []
        LOGGER.info("intersection -> {}, union -> {}, difference -> {}", intersection, union, difference);
    }
}
