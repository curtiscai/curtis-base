package com.curtis.apache.lang3;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-26
 * @email curtis.cai@outlook.com
 * @reference
 */
public class TupleTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TupleTest.class);

    @Test
    public void testPairAndTruple() {
        Pair<String, String> immutablePair = ImmutablePair.of("left", "right");
        String left = immutablePair.getLeft();
        String right = immutablePair.getRight();
        LOGGER.info("immutablePair -> {}, left -> {}, right -> {}", immutablePair, left, right);

        Triple<String, String, String> immutableTriple = ImmutableTriple.of("left", "middle", "right");
        String left1 = immutableTriple.getLeft();
        String middle = immutableTriple.getMiddle();
        String right1 = immutableTriple.getRight();
        LOGGER.info("immutableTriple -> {}, left1 -> {}, middle -> {}, right1 -> {}", immutableTriple, left1, middle, right1);
    }
}
