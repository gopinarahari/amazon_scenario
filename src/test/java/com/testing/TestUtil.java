package com.testing;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;




public class TestUtil extends TestBase{
	public static void doLogin(String searchstring, String emailid,String customerName,String password) throws InterruptedException
	{
		
		try{
			getObject("search_box").clear();
			getObject("search_box").sendKeys(searchstring);
			getObject("go_button").click();
			//Thread.sleep(5000L);
			getObject("result_link").click();
			Thread.sleep(5000L);
			getObject("add_to_basket").click();
			getObject("edit_basket").click();
			getObject("edit_basket2").click();
			getObject("select_3_phones").click();
			//Thread.sleep(2000L);
			getObject("checkout").click();
			getObject("emailid").sendKeys(emailid);
			getObject("radiobutton").click();
			getObject("signin_submit").click();
			getObject("register_name").sendKeys(customerName);
			getObject("register_email_check").sendKeys(emailid);
			getObject("register_password").sendKeys(password);
			getObject("register_password_check").sendKeys(password);
			getObject("create_account").click();
			getObject("fullname").sendKeys(customerName);
			getObject("address_line1").sendKeys("1,IdenCourt");
			getObject("address_city").sendKeys("Newbury");
			getObject("address_county").sendKeys("Berkshire");
			getObject("address_code").sendKeys("RG142AU");
			getObject("address_phnumber").sendKeys("07796181319");			
			WebElement  dropbox =dr.findElement(By.xpath("//select[@id='AddressType']"));
			List<WebElement> list = dropbox.findElements(By.tagName("option"));
			dropbox.sendKeys(list.get(1).getText());
			getObject("continue").click();
			getObject("delivery_address").click();
			//Thread.sleep(2000L);
			getObject("delivery_address").click();
			//System.out.println(password);
		
			
		
		}
		catch (Exception e)
		{
			System.out.println("unbale to find the results for search string");
		}
		
			
	}
	public static boolean isSkip(String testname)
	{
		
		for(int rowNum=2;rowNum<=datatable.getRowCount("test cases");rowNum++)
	       {
	    	   if(testname.equals(datatable.getCellData("test cases", "TCID", rowNum)))
	    	   {
	    		   if(datatable.getCellData("test cases", "Run Mode", rowNum).equals("Y"))
	    		   {
	    			   return false;
	    		   }
	    		   else
	    		   {
	    			   return true;
	    		   }
	    	   }
	    	   
	       }
		return true;
	}
	
	public static Object [][] getdata(String sheetname)
	{
	
		if(datatable == null){
			// load the suite 1 sheet
			datatable = new Xls_Reader(System.getProperty("user.dir")+"\\src\\test\\java\\com\\testing\\Suite1.xlsx");
			
		}
		
		int rows=datatable.getRowCount(sheetname)-1;
		if(rows <=0){
			Object[][] testData =new Object[1][0];
			return testData;
			
		}
	    rows = datatable.getRowCount(sheetname);  // 3
		int cols = datatable.getColumnCount(sheetname);
		System.out.println("Test Name -- "+sheetname);
		System.out.println("total rows -- "+ rows);
		System.out.println("total cols -- "+cols);
		Object data[][] = new Object[rows-1][cols];
		
		for(int rowNum = 2 ; rowNum <= rows ; rowNum++){
			
			for(int colNum=0 ; colNum< cols; colNum++){
				data[rowNum-2][colNum]=datatable.getCellData(sheetname, colNum, rowNum);
			}
		}
		
		return data;
	}

}
