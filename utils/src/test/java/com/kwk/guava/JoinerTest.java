/**
 * Project: guava
 * 
 * File Created at 2014年2月19日
 * $Id$
 * 
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.kwk.guava;

import java.util.List;

import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * TODO Comment of JoinerTest
 * @author yanwei.cao
 *
 */
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
