package com.curtis.guava.utilities;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-09-15
 * @email curtis.cai@outlook.com
 * @reference
 */
public class JoinerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(JoinerTest.class);

    @Test
    public void testJoiner() {
        List<String> stringList = Arrays.asList("Java", "Scala", "SQL", "python");

        String join = Joiner.on(",").join(stringList);
        // 20:26:35,384  INFO JoinerTest:29 - join -> Java,Scala,SQL,python
        LOGGER.info("join -> {}", join);

        String joinWithJava8 = stringList.stream().collect(Collectors.joining(","));
        // 20:26:35,388  INFO JoinerTest:32 - joinWithJava8 -> Java,Scala,SQL,python
        LOGGER.info("joinWithJava8 -> {}", joinWithJava8);

    }

    @Test
    public void testJoinerWithNullValueButSkip() {
        List<String> stringList = Arrays.asList("Java", "Scala", null, "SQL", "python");

        try {
            String join = Joiner.on(",").join(stringList);
            LOGGER.info("join -> {}", join);
        } catch (NullPointerException e) {
            LOGGER.error(e.getClass().getName(), e);
        }

        String joinSkipNulls = Joiner.on(",").skipNulls().join(stringList);
        // 20:19:18,419  INFO JoinerTest:43 - joinSkipNulls -> Java,Scala,SQL,python
        LOGGER.info("joinSkipNulls -> {}", joinSkipNulls);

    }

    @Test
    public void testJoinerWithNullValueButUseDefaultValue() {
        List<String> stringList = Arrays.asList("Java", "Scala", null, "SQL", "python");

        try {
            String join = Joiner.on(",").join(stringList);
            LOGGER.info("join -> {}", join);
        } catch (NullPointerException e) {
            LOGGER.error(e.getClass().getName(), e);
        }

        String joinWithDefaultValue = Joiner.on(",").useForNull("DEFAULT").join(stringList);
        // 20:19:03,458  INFO JoinerTest:59 - joinWithDefaultValue -> Java,Scala,DEFAULT,SQL,python
        LOGGER.info("joinWithDefaultValue -> {}", joinWithDefaultValue);

        String joinWithDefaultValueOfJava8 = stringList.stream().map(item -> item == null || item.isEmpty() ? "DEFAULT" : item).collect(Collectors.joining(","));
        // 20:30:20,897  INFO JoinerTest:71 - joinWithDefaultValueOfJava8 -> Java,Scala,DEFAULT,SQL,python
        LOGGER.info("joinWithDefaultValueOfJava8 -> {}", joinWithDefaultValueOfJava8);
    }

    @Test
    public void testJoinerOnMapWithNullValueButUseDefaultValue() {
        Map<String, Integer> stringMap = ImmutableMap.of("curtis1", 21, "curtis2", 22);

        String joinWithDefaultValue = Joiner.on(",").useForNull("DEFAULT").withKeyValueSeparator(":").join(stringMap);
        // 20:53:12,079  INFO JoinerTest:84 - joinWithDefaultValue -> curtis1:21,curtis2:22
        LOGGER.info("joinWithDefaultValue -> {}", joinWithDefaultValue);
    }
}
