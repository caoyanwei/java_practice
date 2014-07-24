package com.kwk.guava;


import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

public class SplitterTest {

    @Test
    public void basicTest() {
        Iterable<String> iterator = Splitter.on(',').split("1,2,3");
        List<String> strList = Lists.newArrayList(iterator);
        Assert.assertTrue(strList.size() == 3);
    }
}
