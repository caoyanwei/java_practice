
package com.kwk.guava;

import org.junit.Test;

import com.google.common.base.Strings;


public class StringsTest {
	
	@Test
	public void test1()
	{
		String ret = Strings.padEnd("abc", 10, '*');
		System.out.println(ret);
	}
}
