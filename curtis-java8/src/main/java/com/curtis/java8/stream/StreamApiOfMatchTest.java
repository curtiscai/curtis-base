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
public class StreamApiOfMatchTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamApiOfMatchTest.class);

    /**
     * allMatch——检查是否匹配所有元素
     * noneMatch——检查是否没有匹配的元素
     * anyMatch——检查是否至少匹配一个元素
     * 注意：allMatch/anyMatch和noneMatch互斥，一个是匹配所有或者至少匹配一个，一个是没有匹配。allMatch可以看作是anyMatch的特例。
     * 三个函数都接收Predicate<? super T> predicate参数
     */
    @Test
    public void testMatch() {
        List<User> sourceList = Lists.newArrayList(new User(1, "curtis1", 15100001004L, false, BigDecimal.valueOf(180.3)),
                new User(2, "curtis2", 15100001003L, false, BigDecimal.valueOf(180.2)),
                null,
                new User(3, "curtis3", 15100001002L, true, null),
                null,
                new User(4, "curtis4", 15100001001L, true, BigDecimal.valueOf(180.1)));
        // allMatch——检查是否匹配所有元素
        boolean allMatch1 = sourceList.stream().filter(Objects::nonNull).allMatch(item -> item.getId() != null);
        // 10:46:01,270  INFO StreamApiOfMatchTest:39 - allMatch1 -> true
        LOGGER.info("allMatch1 -> {}", allMatch1);

        boolean allMatch2 = sourceList.stream().filter(Objects::nonNull).allMatch(item -> item.getHeight() != null);
        // 10:46:01,272  INFO StreamApiOfMatchTest:42 - allMatch2 -> false
        LOGGER.info("allMatch2 -> {}", allMatch2);

        // noneMatch——检查是否没有匹配的元素
        boolean noneMatch1 = sourceList.stream().filter(Objects::nonNull).noneMatch(item -> item.getId() == null);
        // 10:46:01,272  INFO StreamApiOfMatchTest:46 - noneMatch1 -> true
        LOGGER.info("noneMatch1 -> {}", noneMatch1);

        boolean noneMatch2 = sourceList.stream().filter(Objects::nonNull).noneMatch(item -> item.getHeight() == null);
        // 10:46:01,273  INFO StreamApiOfMatchTest:49 - noneMatch2 -> false
        LOGGER.info("noneMatch2 -> {}", noneMatch2);

        // anyMatch——检查是否至少匹配一个元素
        boolean anyMatch1 = sourceList.stream().filter(Objects::nonNull).anyMatch(item -> item.getHeight() == null);
        // 10:46:01,273  INFO StreamApiOfMatchTest:53 - anyMatch1 -> true
        LOGGER.info("anyMatch1 -> {}", anyMatch1);

        boolean anyMatch2 = sourceList.stream().filter(Objects::nonNull).anyMatch(item -> item.getHeight() != null);
        // 10:46:01,273  INFO StreamApiOfMatchTest:56 - anyMatch2 -> true
        LOGGER.info("anyMatch2 -> {}", anyMatch2);
    }
}
