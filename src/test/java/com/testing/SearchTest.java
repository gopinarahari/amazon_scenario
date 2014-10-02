package com.testing;


import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class SearchTest extends TestBase{
	public String searchstring;
	
	public String emailid;
	public String customerName;
	public String password;
	
	public SearchTest(String searchstring, String emailid,String customername,String password)
	{
		this.searchstring=searchstring;
		this.emailid=emailid;
		this.customerName=customername;
		this.password=password;
				
	}
	@Before
	public void beforeTest() throws IOException
	{
		intialize();
		if(TestUtil.isSkip("SearchTest"))
		   Assume.assumeTrue(false);
	}
	@Test
	public void loginTest() throws InterruptedException
	{
		driver.get(CONFIG.getProperty("testsitename"));
		TestUtil.doLogin(searchstring,emailid, customerName,password);
	}
	@Parameters
	public static Collection<Object []> datasupplier()
	{
			Object data [][] = TestUtil.getdata("SearchTest");
			return Arrays.asList(data);
	}

}
