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

import org.junit.Test;

import com.google.common.base.Strings;

/**
 * TODO Comment of StringsTest
 * @author yanwei.cao
 *
 */
public class StringsTest {
	
	@Test
	public void test1()
	{
		String ret = Strings.padEnd("abc", 10, '*');
		System.out.println(ret);
	}
}
