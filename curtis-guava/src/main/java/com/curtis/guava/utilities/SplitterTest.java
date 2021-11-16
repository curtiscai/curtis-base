package com.curtis.guava.utilities;

import com.google.common.base.Splitter;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-09-15
 * @email curtis.cai@outlook.com
 * @reference
 */
public class SplitterTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SplitterTest.class);

    @Test
    public void testsSplitOnSplit() {
        List<String> stringList = Splitter.on("|").splitToList("hello|world");
        // 21:03:11,781  INFO SplitterTest:25 - stringList -> [hello, world]
        LOGGER.info("stringList -> {}", stringList);
    }

    @Test
    public void testsSplitOnSplitOmitEmpty() {
        List<String> stringList = Splitter.on("|").splitToList("hello|world|||");
        // 21:04:12,056  INFO SplitterTest:33 - stringList -> [hello, world, , , ]
        LOGGER.info("stringList -> {}", stringList);

        List<String> stringListOmitEmptyString = Splitter.on("|").omitEmptyStrings().splitToList("hello|world|||");
        // 21:05:16,468  INFO SplitterTest:37 - stringListOmitEmptyString -> [hello, world]
        LOGGER.info("stringListOmitEmptyString -> {}", stringListOmitEmptyString);
    }

    @Test
    public void testsSplitOnSplitOmitEmptyTrimResult() {
        List<String> stringList = Splitter.on("|").splitToList("hello | world|||");
        // 21:06:46,482  INFO SplitterTest:43 - stringList -> [hello ,  world, , , ]
        LOGGER.info("stringList -> {}", stringList);

        List<String> stringListOmitEmptyString = Splitter.on("|").omitEmptyStrings().trimResults().splitToList("hello|world|||");
        // 21:06:46,484  INFO SplitterTest:47 - stringListOmitEmptyString -> [hello, world]
        LOGGER.info("stringListOmitEmptyString -> {}", stringListOmitEmptyString);
    }

    @Test
    public void testsSplitOnSplitFixLength() {
        List<String> stringList = Splitter.fixedLength(4).splitToList("aaaabbbbccccdddd");
        // 21:08:19,873  INFO SplitterTest:55 - stringList -> [aaaa, bbbb, cccc, dddd]
        LOGGER.info("stringList -> {}", stringList);
    }

    @Test
    public void testsSplitOnPatternString() {
        List<String> stringList = Splitter.onPattern("\\|").omitEmptyStrings().trimResults().splitToList("hello | world|||");
        // 21:10:46,758  INFO SplitterTest:62 - stringList -> [hello, world]
        LOGGER.info("stringList -> {}", stringList);

        final List<String> stringList2 = Splitter.on(Pattern.compile("\\|")).omitEmptyStrings().trimResults().splitToList("hello | world|||");
        // 21:12:55,687  INFO SplitterTest:67 - stringList2 -> [hello, world]
        LOGGER.info("stringList2 -> {}", stringList2);
    }

    @Test
    public void testsSplitOnSplitToMap() {
        Map<String,String> stringMap = Splitter.onPattern("\\|").omitEmptyStrings().trimResults().withKeyValueSeparator("=").split("hello=HELLO | world=WORLD|||");
        // 21:14:50,883  INFO SplitterTest:75 - stringList -> {hello=HELLO, world=WORLD}
        LOGGER.info("stringList -> {}", stringMap);
    }
}
