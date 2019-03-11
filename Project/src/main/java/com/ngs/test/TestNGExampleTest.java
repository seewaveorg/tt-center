package com.ngs.test;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.ngs.util.MessageUtil;

public class TestNGExampleTest {
   String message = "Hello World";	
   MessageUtil messageUtil = new MessageUtil(message);

   @Test
   public void testPrintMessage() {	  
      Assert.assertEquals(message,messageUtil.printMessage());
   }
}