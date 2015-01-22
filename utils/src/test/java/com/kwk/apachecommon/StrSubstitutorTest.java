package com.kwk.apachecommon;

import org.apache.commons.lang.text.StrSubstitutor;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class StrSubstitutorTest {
    @Test
    public void basicTest() {
        Map valuesMap = new HashMap();
        valuesMap.put("animal", "quick brown fox");
        valuesMap.put("target", "lazy dog");
        String templateString = "The ${animal} jumped over the ${target}.";
        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        String resolvedString = sub.replace(templateString);
        System.out.println(resolvedString);
    }
}
