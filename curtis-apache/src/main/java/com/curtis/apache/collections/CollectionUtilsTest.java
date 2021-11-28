package com.curtis.apache.collections;

import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Set;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-29
 * @email curtis.cai@outlook.com
 * @reference
 */
public class CollectionUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionUtilsTest.class);

    @Test
    public void testCollectionUtils() {
        Set<Integer> intSet1 = Sets.newHashSet(1, 1, 2, 3);
        Set<Integer> intSet2 = Sets.newHashSet(3, 4, 5, 5);
        Collection<Integer> intersection = CollectionUtils.intersection(intSet1, intSet2);
        Collection<Integer> union = CollectionUtils.union(intSet1, intSet2);
        Collection<Integer> subtract = CollectionUtils.subtract(intSet1, intSet2);
        // 00:12:34,636  INFO CollectionUtilsTest:30 - intersection -> [3], union -> [1, 2, 3, 4, 5], subtract -> [1, 2]
        LOGGER.info("intersection -> {}, union -> {}, subtract -> {}", intersection, union, subtract);
    }
}
