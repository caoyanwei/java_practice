
package com.kwk.guava;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;


public class JoinerTest {

	@Test
	public void test1()
	{
		List<String> list = Lists.newArrayList();
		list.add("aabc");
		list.add("dddd");
		list.add("iiii");
		String ret = Joiner.on(',').join(list);
		System.out.println(ret);
	}
}
