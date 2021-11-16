package com.curtis.java8.stream;

import com.curtis.java8.model.User;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-10-13
 * @email curtis.cai@outlook.com
 * @reference
 */
public class StreamApiOfStatisticTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamApiOfStatisticTest.class);

    @Test
    public void testStatistics() {
        List<User> sourceList = Lists.newArrayList(new User(1, "curtis1", 15100001004L, false, BigDecimal.valueOf(180.3)),
                new User(2, "curtis2", 15100001003L, false, BigDecimal.valueOf(180.2)),
                null,
                new User(3, "curtis3", 15100001002L, true, null),
                null,
                new User(4, "curtis4", 15100001001L, true, BigDecimal.valueOf(180.1)));

        Optional<User> max = sourceList.stream().filter(Objects::nonNull).filter(item -> item.getHeight() != null).max(Comparator.comparing(User::getHeight));
        max.ifPresent(item -> LOGGER.info("max -> {}", item.getHeight()));
        Optional<User> min = sourceList.stream().filter(Objects::nonNull).filter(item -> item.getHeight() != null).min(Comparator.comparing(User::getHeight));
        min.ifPresent(item -> LOGGER.info("min -> {}", item.getHeight()));
        long count = sourceList.stream().filter(Objects::nonNull).count();
        LOGGER.info("count -> {}", count);
        Double sum = sourceList.stream().filter(Objects::nonNull).filter(item -> item.getHeight() != null).mapToDouble(item -> item.getHeight().doubleValue()).sum();
        LOGGER.info("sum -> {}", sum);
        OptionalDouble average = sourceList.stream().filter(Objects::nonNull).filter(item -> item.getHeight() != null).mapToDouble(item -> item.getHeight().doubleValue()).average();
        LOGGER.info("average -> {}", average);



        // 对于Decimal类型要进行统计，要转换成Double然后使用DoubleSummaryStatistics，统计结果也是Double，一定要进行相应的格式化
        DoubleSummaryStatistics doubleSummaryStatistics = sourceList.stream().filter(Objects::nonNull).filter(item -> item.getHeight() != null)
                .collect(Collectors.summarizingDouble(item -> item.getHeight().doubleValue()));
        System.out.println(doubleSummaryStatistics);
        double max1 = doubleSummaryStatistics.getMax();
        double min1 = doubleSummaryStatistics.getMin();
        long count1 = doubleSummaryStatistics.getCount();
        double average1 = doubleSummaryStatistics.getAverage();
        double sum1 = doubleSummaryStatistics.getSum();
        LOGGER.info("max1 -> {}, min1 -> {}, count1 -> {}, average1 -> {}, sum1 -> {}", max1, min1, count1, average1, sum1);

        Double averagingDouble = sourceList.stream().filter(Objects::nonNull).filter(item -> item.getHeight() != null).collect(Collectors.averagingDouble(item -> item.getHeight().doubleValue()));
        Double summingDouble = sourceList.stream().filter(Objects::nonNull).filter(item -> item.getHeight() != null).collect(Collectors.summingDouble(item -> item.getHeight().doubleValue()));


    }
}
