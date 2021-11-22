package com.curtis.guava.collections;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.*;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-23
 * @email curtis.cai@outlook.com
 * @reference
 */
public class BaseTest {

    /**
     * 声明并初始化集合更加方便
     */
    @Test
    public void testBase() {
        // 1. 传统方式
        ArrayList<Object> arrayList1 = new ArrayList<>();
        arrayList1.add(1);
        LinkedList<Object> linkedList1 = new LinkedList<>();
        HashMap<Object, Object> hashMap1 = new HashMap<>();
        LinkedHashMap<Object, Object> linkedHashMap1 = new LinkedHashMap<>();

        // 2. Guava Api方式
        List<Integer> arrayList2 = Lists.newArrayList(1, 2, 3);
        List<Integer> linkedList2 = Lists.newLinkedList();
        HashMap<String, String> hashMap2 = Maps.newHashMap();
        LinkedHashMap<String, String> linkedHashMap2 = Maps.newLinkedHashMap();
    }
}
