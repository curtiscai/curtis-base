package com.curtis.apache.lang3;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Test;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-26
 * @email curtis.cai@outlook.com
 * @reference
 */
public class TupleTest {
    @Test
    public void test(){
        Pair<String, String> of = ImmutablePair.of("", "");
        Triple<String,String,String> triple = ImmutableTriple.of("","","");


    }
}
