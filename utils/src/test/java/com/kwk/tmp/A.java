package com.kwk.tmp;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.kwk.perf.PerformanceMarker;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

public class A {
    @Test
    public void basicTest() {
        PerformanceMarker marker = new PerformanceMarker("set");
        marker.mark("aaaa");
        HashSet<Integer> set = Sets.newHashSet();

        List<Integer> list = Lists.newArrayList();
        for (int i = 0; i < 1000; ++i) {
            for (int j = 0; j < 1000; ++j) {
                list.add(j);
            }
            set.addAll(list);
            list.clear();
        }

        for (int i = 0; i < 1000; ++i) {
            for (int j = 0; j < 1000; ++j) {
                list.add(j);
            }
            set.addAll(list);
            list.clear();
        }


        System.out.println(set.size());
        System.out.println(marker.end("bbbb"));
    }
}
