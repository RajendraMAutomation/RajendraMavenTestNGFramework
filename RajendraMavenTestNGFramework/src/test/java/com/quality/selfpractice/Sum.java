package com.quality.selfpractice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Sum {

	
	/*public static void main(String []args) {
		int a=10;
		int b=20;
		System.out.println(a+b);
	}*/
	@Test
	void sum() {
		int a=10;
		int b=20;
		Assert.assertEquals(30, a+b);
		
	}
}
