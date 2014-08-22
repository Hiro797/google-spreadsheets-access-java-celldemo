package com.streambox.celldemo;


import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.JavascriptExecutor;

public class SystemAdminLivedev3Account extends AccountPage{
	
	//public PageObject obj =new PageObject();
	public AccountPage obj;
	private WebDriver driver;
	
	public SystemAdminLivedev3Account(){
		this.driver = new FirefoxDriver();
		obj=new AccountPage(driver);
			
		//this.obj=null;
	}
	
	
	void sysadminrun() throws InterruptedException{
		
		try{
		
			obj.SetWebSite("http://livedev3.streambox.com/ls/login.php");
			//obj.SetWebSite("http://www.thecheesecakefactory.com");
			
			
			//obj.SetWebSite("http://75.101.135.145"); //this is d2-server
		//obj.SetWebSite("http://d2.streambox.com/ls/SLSLogin.php");
		
			
			
		obj.Login("username","password");
		
		
		
		
		obj.run();
		
		//****************getXpath*******************//
				//String href = driver.getAttribute("css=a@href");
				//String xxx = driver.findElement(By.className("a")).getAttribute("css=a@href");
				//System.out.println(xxx);
				//***********************************//
				
			
			System.out.println("Start reading html");
			
			 
             /*
			List<WebElement> RadioNamelist = driver
					 .findElements(By
					 .xpath("//img[contains(@class,'sectionheader')]"));
			*/
			String text = driver.findElement(By.cssSelector("div.container-fluid")).toString();
			System.out.println(text);
			
			/*
			List<WebElement> RadioNamelist = driver
					 .findElements(By.xpath("//body[@class = 'tpl_advanced']"));
			*/
			
			List<WebElement> RadioNamelist = driver
					 .findElements(By.xpath("//a[text()='Default Routing Protocol: LDMP']"));
			
			
			
			//xpath("//a[text()='Default Routing Protocol: LDMP']"));
			System.out.println(RadioNamelist.get(0).getLocation());
			
			
			WebElement element = driver.findElement(By.xpath("//a[text()='Default Routing Protocol: LDMP']"));
			String contents = (String)((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML;", RadioNamelist.get(0)); 
			
			System.out.println(contents);
					System.out.println("Result:   "+driver.getPageSource().equals("text()='Default Routing Protocol: LDMP'"));
			
			//= "//a[text()='Default Routing Protocol: LDMP']";
			
			//<a href="">D</a>
			
			
			
					 System.out.println(RadioNamelist.size());
			//System.out.println("Number of boxes "+link_boxes.size() );
			for(int i=0;i<RadioNamelist.size();i++){
				 System.out.println(RadioNamelist.get(i).getAttribute("text"));
				 }
            
			
			
			
            /*
			List<WebElement> link_boxes=driver.findElements(By.xpath("//*[@id='current']"));
             for(int j=0;j<link_boxes.size();j++){
                    
                     WebElement box = link_boxes.get(j);
                     List<WebElement> links = box.findElements(By.tagName("a"));
                     //System.out.println("Total links for---"+link_boxes.get(j+1)+"---are--- "+links.size() );
                     System.out.println("====================================="+j);
                     for(int i=0 ; i<links.size();i++){
                             System.out.println("*********************************************");
                             System.out.println(links.get(i).getText());
                             System.out.println(links.get(i).getAttribute("href"));
                            
                     }
             }
			*/
			//System.out.println("URL : " + driver.findElement(By.className("a")).getAttribute("href"));
			
			
		        /*
			 for(WebElement e : ele)
		        {
		           //String doctorname = e.getText();
		           String linkValue = e.getAttribute("class");
		        System.out.println(linkValue);
		        }
		        
		        System.out.println("dsv");
		        System.out.println("dsv");
			
			
		String Input= driver.findElement(By.tagName("Body")).getAttribute("onload");
				
		WebElement ele = driver.findElement(By.xpath("//div[@id='topNavContainer']/ul[@id='topNav']/li/a[@class ='MenueBarItemSubmenu]"));
		//WebElement ele = driver.findElement(By.xpath("//div[@classs='container']/div/div[@id='ctn_nav']/ul[@class='gradient level1 nav'/li[@class ='active']/a[@id=current']"));
				String str = ele.getAttribute("innerHTML");
				System.out.println(str);
		*/
		//_Create_otherAccount();
		
		
		
		}catch(Exception e){
			
			System.out.println("SysAdministrator Error");
			
		}
		
		
		
	}
	
	void _Create_otherAccount(){
		
		driver.findElement(By.xpath("//div[@id='menu']/div[@class = 'navbar-inner']/div[@class = 'container-fluid']/div/div[@id = 'ctn_nav']/ul[@class = 'gradient level1 nav']/li[@class = 'dropdown']/a[@class = 'dropdown-toggle']")).click();
		
		//if(<=0)
		
		//driver.findElement(By.xpath("//div[@id='menu']/div[@class = 'navbar-inner']/div[@class = 'container-fluid']/div/div[@id = 'ctn_nav']/ul[@class = 'gradient level1 nav']/li[@class = 'dropdown open']/ul[@class = 'dropdown-menu']/li[@class = 'active']/a[@id = 'current']")).click();
		
		driver.findElement(By.xpath("//div[@id='menu']/div[@class = 'navbar-inner']/div[@class = 'container-fluid']/div/div[@id = 'ctn_nav']/ul[@class = 'gradient level1 nav']/li[@class = 'dropdown open']/ul[@class = 'dropdown-menu']/li/a[@href = 'slsusers.php']")).click();
		
		//************************Add Account**********************************************//
		driver.findElement(By.xpath("//div[@id='container']/div[@class = 'container-fluid']/ul[@id = 'submenu']/li[@id = 'submenu_addNewUser']/a[@class = 'submenu_control btn']")).click();
		
		
		
		//***************************Set up Login*********************************//
		WebElement element1 = driver.findElement(By.xpath("//p[@class='controls']/input[@id='uname1']"));
		element1.clear();
		element1.sendKeys("hiro1GroupAdmin");
		//***************************End Set up Login*********************************//
		

		//***************************Set up Pass*********************************//
		WebElement element2 = driver.findElement(By.xpath("//p[@class='controls']/input[@id='upass1']"));
		element2.clear();
		element2.sendKeys("demoGroupAdmin");
		//***************************End Set up Pass*********************************//
		

		//***************************Set up Pass*********************************//
		WebElement element2s = driver.findElement(By.xpath("//p[@class='controls']/input[@id='upass2']"));
		element2s.clear();
		element2s.sendKeys("demoGroupAdmin");
		//***************************End Set up Pass*********************************//

		
		//***************************Set up Group*********************************//
		WebElement element3 = driver.findElement(By.xpath("//p[@class='controls']/input[@id='udrm1']"));
		element3.clear();
		element3.sendKeys("hiro1");
		//***************************End Set up Group*********************************//
		
		obj.To_ChangeConfirmAccount(driver);
		
		
		
		

		//************************Show Account**********************************************//
		driver.findElement(By.xpath("//div[@id='container']/div[@class = 'container-fluid']/ul[@id = 'submenu']/li[@id = 'submenu_getRole']/a[@class = 'submenu_control btn']")).click();
		driver.findElement(By.xpath("//div[@id='container']/div[@class = 'container-fluid']/ul[@id = 'submenu']/li[@id = 'submenu_getUser']/a[@class = 'submenu_control btn']")).click();
	
		//*************************Serchbox****************************************//
		WebElement elementsearch = driver.findElement(By.xpath("//div[@id='menu']/div[@class = 'navbar-inner']/div[@class = 'container-fluid']/div/div[@class='navbar-form pull-right']/div[@id='searchBar']/form[@class = 'navbar-form input-append']/input[@id='flt']"));
		elementsearch.clear();
		elementsearch.sendKeys("hiro1");
		
		System.out.println("Delete the GroupAdministrator");
		driver.findElement(By.xpath("//div[@id='container']/div[@id='mainContent']/div[@id='ctn_users']/div[@id='dt_users_wrapper']/table[@id='dt_users']/tbody[@role='alert']/tr[@class ='odd']/td[@class ='']/div[@class = 'btn-group']/a[@class = 'btn btn-small']/span[@class='icon-trash']")).click();
		
		
		
	}
	
	
	
	
	

}//end of the class of system admin
