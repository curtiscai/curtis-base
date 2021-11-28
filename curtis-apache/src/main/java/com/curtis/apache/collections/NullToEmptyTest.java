package com.curtis.apache.collections;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.SetUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-28
 * @email curtis.cai@outlook.com
 * @reference
 */
public class NullToEmptyTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NullToEmptyTest.class);

    @Test
    public void testNullToEmpty() {
        List<Object> emptyList = ListUtils.emptyIfNull(null);

        Set<Object> emptySet = SetUtils.emptyIfNull(null);

        Map<Object, Object> emptyMap = MapUtils.emptyIfNull(null);

        Collection<Object> empty = CollectionUtils.emptyIfNull(null);

        LOGGER.info("emptyList -> {}, emptySet -> {}, emptyMap -> {}, empty -> {}", emptyList, emptySet, emptyMap, empty);
    }
}
