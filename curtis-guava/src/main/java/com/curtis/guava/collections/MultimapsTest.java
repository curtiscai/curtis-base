package com.curtis.guava.collections;

import com.google.common.collect.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author curtis.cai
 * @desc Guava Collection之Multimaps api的使用
 * @date 2021-11-17
 * @email curtis.cai@outlook.com
 * @reference
 */
public class MultimapsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultimapsTest.class);

    /**
     * 一个Key多个值，类似于Map<key,List<T>>或者Map<key,Set<T>>
     */
    @Test
    public void test() {
        // 值不去重
        ArrayListMultimap<String, String> arrayListMultimap = ArrayListMultimap.create();
        arrayListMultimap.put("河北", "石家庄");
        arrayListMultimap.put("河北", "保定");
        arrayListMultimap.put("河北", "保定");

        LOGGER.info("arrayListMultimap->{}", arrayListMultimap);
        List<String> stringList = arrayListMultimap.get("河北");

        LinkedListMultimap<String, String> linkedListMultimap = LinkedListMultimap.create();
        linkedListMultimap.put("河北", "石家庄");
        linkedListMultimap.put("河北", "保定");
        linkedListMultimap.put("河北", "保定");

        LOGGER.info("linkedListMultimap->{}", linkedListMultimap);
        List<String> stringList1 = linkedListMultimap.get("河北");

        // 值去重
        SetMultimap<String,String> setMultimap = HashMultimap.create();
        setMultimap.put("河北", "石家庄");
        setMultimap.put("河北", "保定");
        setMultimap.put("河北", "保定");
        LOGGER.info("setMultimap->{}", setMultimap);
    }

    @Test
    public void testMultiset(){
        HashMultiset<String> multiset = HashMultiset.create();
        multiset.add("1");
        multiset.add("1");
        multiset.add("2");
        multiset.add("3");
        multiset.add("2");
        System.out.println(multiset);
    }
}
