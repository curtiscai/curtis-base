package com.curtis.guava.collections;

import com.google.common.collect.BoundType;
import com.google.common.collect.ImmutableRangeMap;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author curtis.cai
 * @desc Guava Collection之Range api的使用
 * @date 2021-11-18
 * @email curtis.cai@outlook.com
 * @reference
 */
public class RangeTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RangeTest.class);

    @Test
    public void testRange() {
        Range<Integer> range = Range.range(1, BoundType.OPEN, 10, BoundType.CLOSED);
        boolean containsLower = range.contains(1);
        boolean contains = range.contains(5);
        boolean containsUpper = range.contains(10);
        // 22:05:24,851  INFO RangeTest:27 - containsLower -> false, contains -> true, containsUpper -> true
        LOGGER.info("containsLower -> {}, contains -> {}, containsUpper -> {}", containsLower, contains, containsUpper);

        Range<Integer> openClosedRange = Range.openClosed(1, 10);
        // 22:09:56,583  INFO RangeTest:30 - openClosedRange -> (1..10]
        LOGGER.info("openClosedRange -> {}", openClosedRange);

        Range<Integer> closedOpenRange = Range.closedOpen(1, 10);
        // 22:09:56,583  INFO RangeTest:33 - closedOpenRange -> [1..10)
        LOGGER.info("closedOpenRange -> {}", closedOpenRange);

        Range<Integer> lessThanRange = Range.lessThan(1);
        // 22:11:44,326  INFO RangeTest:38 - lessThanRange -> (-∞..1)
        LOGGER.info("lessThanRange -> {}", lessThanRange);

        Range<Integer> greaterThanRange = Range.greaterThan(10);
        // 22:11:44,327  INFO RangeTest:41 - greaterThanRange -> (10..+∞)
        LOGGER.info("greaterThanRange -> {}", greaterThanRange);
    }

    @Test
    public void testRange2() {

    }
}
