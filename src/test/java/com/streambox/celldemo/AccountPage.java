package com.streambox.celldemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.jetty.html.Page;
//import org.apache.commons.io.FileUtiles
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.io.BufferedReader;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class AccountPage {


	protected WebDriver driver;
	protected static String name;
	
	public AccountPage(){//constructor
		driver = null;
		//this.driver = new FirefoxDriver();
		//Dimension d = new Dimension(1200,1200);
		//driver.manage().window().setSize(d);
	}
	/*
	public void setPageObject(WebDriver driver){
		this.driver=driver;
		Dimension d = new Dimension(1200,1200);
		driver.manage().window().setSize(d);
		
		
	}*/
	
	public AccountPage(WebDriver driver){
		this.driver=driver;
		Dimension d = new Dimension(1200,1200);
		driver.manage().window().setSize(d);
		
		
	}
	
	
	public int SetWebSite(String string){
	if(string!=null){
		this.driver.get(string);
		return 0;
	}else{
		System.out.println("parameter in the SetWebSite() = null ");
		return 1;
	}
		
	}
	
	
	public String getCurrentURL(){
		
		String url=this.driver.getCurrentUrl();
		
		return url;
	}
	
	
	public int Login(String name, String pass){
		if((name==null)||(pass==null)){
			System.out.println("Error: login name or password is null in the function Login()");
			return 1;
		}
		
		this.name=name;
		
		//WebElement element5=driver.findElement(By.className("entryfield"));
		
		//WebElement element5=driver.findElement(By.id("l1")); //this is d2-server
		WebElement element5=driver.findElement(By.xpath("//div[@id='login']/div[@id='loginform']/div[@id='flogin']/div[@class='flex']/input[@id='username']")); //this is Livedev3-server
		
		
		element5.sendKeys(name);//LiveServer2
		//element5.sendKeys("admin");//LiveServer2
		
		System.out.println("on the way1");
		
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		//WebElement element6=driver.findElement(By.id("passw1")); //this is for d2-server
		
		WebElement element6=driver.findElement(By.xpath("//div[@id='login']/div[@id='loginform']/div[@id='flogin']/div[@class='flex']/input[@id='password']")); //this is livedev3-server
		
		//WebElement element6=driver.findElement(By.xpath("//div[@class='flex']/input[@id='password']"));
		//element6.sendKeys("demo");
		element6.sendKeys(pass);
			
				//System.out.println("on the way: " + driver.findElement(By.id("password")));
		
				//driver.findElement(By.xpath("//tbody/tr/td[@align='left']/input[@class='btn btn-primary']")).click();
				
		//****************************Login Button****************************************************//
		//driver.findElement(By.xpath("//div[@id='mainContent']/form[@class='well']/table/tbody/tr/td[@align='left']/input[@class='btn btn-primary']")).click();//this is for d2-server
		driver.findElement(By.xpath("//div[@id='login']/div[@id='loginform']/div[@id='flogin']/div[@class='flex']/button[@id='btn_sb_login']")).click();
		
		//driver.findElement(By.xpath("//button[@id='btn_sb_login']")).click();
		System.out.println("Click Sign In");
				
		return 0;
		
	}
	
	public int run() throws InterruptedException{
		
		try{
			To_Check_theAccount(driver, name);

			To_ChangeConfirmAccount(driver);
			
			return 0;
			
			}catch(Exception e){
			return 1;
				
			}
		
	}
	
	
	
	//*****************************************************************************************************************//
	
	protected static void To_ChangeConfirmAccount(WebDriver driver) {
		// TODO Auto-generated method stub
		
		try{
		
			//***************************This sentence is for opening the admon[System Admin of Admin]*************************************//
		//driver.findElement(By.xpath("//button[@class = 'btn dropdown-toggle']")).click();
		
		System.out.println("Now Page stay at the advanced mode");
		//driver.findElement(By.xpath("//a[@href='/ls/u/hiro']")).click();;
		
		
		System.out.println("Succeed in going to the Account Information");
		
		WebElement element1 = driver.findElement(By.xpath("//input[@id='uloc1']"));
		element1.clear();
		element1.sendKeys("bellevue");
		//element1.submit();
		
		//********send FirstName************//
		WebElement element2 = driver.findElement(By.xpath("//p[@class='controls']/input[@id='ufirst1']"));
		element2.clear();
		element2.sendKeys("hiro1");
		//***********************************//
		

		//********send LastName************//
		WebElement element3 = driver.findElement(By.xpath("//p[@class='controls']/input[@id='ulast1']"));
		element3.clear();
		element3.sendKeys("naka1");
		//***********************************//
		
		
		//********send CompanyName************//
		WebElement element4 = driver.findElement(By.xpath("//p[@class='controls']/input[@id='ucompany1']"));
		element4.clear();
		element4.sendKeys("streambox");
		//***********************************//
		
		
		//********send E-mail:************//
		WebElement element5 = driver.findElement(By.xpath("//p[@class='controls']/input[@id='uemail1']"));
		element5.clear();
		element5.sendKeys("streambox");
		//***********************************//
		
		//********send Phone:************//
		WebElement element6 = driver.findElement(By.xpath("//p[@class='controls']/input[@id='uphone1']"));
		element6.clear();
		element6.sendKeys("23425234");
		//***********************************//
		
		
		driver.findElement(By.xpath("//p[@class = 'controls']/input[@id = 'btn_user_save']")).click();
		
		driver.findElement(By.xpath("//button[@class = 'btn btn-cancel']")).click();
		System.out.println("Click the Save");
		
		Check_Application_form(driver);
		
		
		
		}catch(Exception e){
			
			
			System.out.println("Error");
			
		}

		
		
	}


	 private static void Check_Application_form(WebDriver driver) {
		// TODO Auto-generated method stub
		
		System.out.println("In the Check_Application_form()...");
		
	

		//**************Check the Type Information*************************//
		//driver.findElement(By.xpath("//p[@class='controls']/select[@id='utype1']")).click();
		//driver.findElement(By.xpath("//option[@value='0']")).click();
		//**********************end****************************************
		
		
		//************************Retrieve the String From Application Form***********************************************//
		
		
		//*************************Check login Name**********************************************//
		
		WebElement element0 = driver.findElement(By.xpath("//fieldset/div[@class='span7']/p[@class='controls']/input[@id='uname1']"));
		//WebElement element0 = driver.findElement(By.xpath("//p[@class='controls']/input[@id='uname1']"));
		String LoginName=element0.getAttribute("value");
		String logpattern= name;
		System.out.println("//*****Checking Login Name******// ");
		CheckString(LoginName,logpattern);
		
		//********************************end******************************************************//
		
		
		//************************Check the FirstName***************************************************//
		WebElement element7 = driver.findElement(By.xpath("//fieldset/div[@class='span7']/p[@class='controls']/input[@id='ufirst1']"));
		String FiUsername = element7.getAttribute("value");
		String FiUnamepattern = "hiro1";
		System.out.println("//*****Checking FastName******// ");
		CheckString(FiUsername,FiUnamepattern);
		//*******************************end**********************************************//
		
		
		//******************************Check LastName*******************************************//
		WebElement element5=driver.findElement(By.xpath("//fieldset/div[@class='span7']/p[@class='controls']/input[@id='ulast1']"));
		String LaUsername = element5.getAttribute("value");
		String LaUnamepattern = "naka1";
		System.out.println("//******Checking LastName****// ");
		CheckString(LaUsername,LaUnamepattern);
		//*******************************end**********************************************//
		
		
		
		//************************Check the Location*******************************************//
		WebElement element1 =driver.findElement(By.xpath("//fieldset/div[@class='span7']/p[@class='controls']/input[@id='uloc1']"));
		String LocationStr =element1.getAttribute("value");
		System.out.println("Location Information: "+ LocationStr);
		String lopattern = "bellevue";
		System.out.println("//*****Checking Location******// ");
		CheckString(LocationStr, lopattern);
		//*********************************end***************************************************//
		
		
		//**********************Checking Company name******************************//
		WebElement element3=driver.findElement(By.xpath("//fieldset/div[@class='span7']/p[@class='controls']/input[@id='ucompany1']"));
		String Companyname = element3.getAttribute("value");
		String copattern ="streambox";
		System.out.println("//*****Checking CompanyName******// ");
		CheckString(Companyname,copattern);
		//**********************end*******************************//
		
		
		//**********************Checking Email******************************//
		WebElement element=driver.findElement(By.xpath("//fieldset/div[@class='span7']/p[@class='controls']/input[@id='uemail1']"));
		String EmailAdd = element.getAttribute("value");
		String empattern ="streambox";
		System.out.println("//*****Checking Email Address******// ");
		CheckString(EmailAdd,empattern);
		//**********************end*******************************//
		
		
		//**********************Checking Phone******************************//
		WebElement element6=driver.findElement(By.xpath("//fieldset/div[@class='span7']/p[@class='controls']/input[@id='uphone1']"));
		String Phonenum = element6.getAttribute("value");
		String popattern ="23425234";
		System.out.println("//*****Checking Phone Number******// ");
		CheckString(Phonenum,popattern);
		//**********************end*******************************//
		
		
		//WebElement element2 =driver.findElement(By.xpath("//a[@href = 'http://store.streambox.com/streambox-live-data-package']"));
		//String Account_Information = element2.getText();
		
		
		driver.navigate().back();
		//************************************
		driver.findElement(By.xpath("//button[@class = 'btn dropdown-toggle']")).click();
		
		//System.out.println("Now Page stay at the advanced mode");
		
		driver.findElement(By.xpath("//a[@href='/ls/slsusers.php?user=5']")).click();
		
		//***********************************
		
		
		//CheckString(LocationStr,"bellevue");
		
		

		//System.out.println("The location is correct: "+ Account_Information + "  !!");// output the Live Enterprise
		
	
		System.out.println(" } End Check_Application_form()");
		
	}//end of Check_Application_form()

	
	private static boolean CheckString(char str1[],char pattern[], int first){
		if(str1.length!=pattern.length){
			System.out.println("The length is different!");
			return false;
		
		}
		
		if((str1[first]==pattern[first])&&(first<str1.length-1)){
			//System.out.println(str1[first]);
			CheckString(str1,pattern,++first);
			return true;
			
		}
		
		System.out.println("out of the loop");
		return false;
	}
	
	

	private static void CheckString(String str1, String pattern) {
		// TODO Auto-generated method stub
		
	System.out.println("//***********Checking String************************//");
		
		char[]str1cp=new char[str1.length()];
		char[]patterncp=new char[pattern.length()];
		
		for(int i=0;i<str1.length();i++){
		str1cp[i]= str1.charAt(i);
		patterncp[i]=pattern.charAt(i);
		}
		int first=0;
		
		if(CheckString(str1cp,patterncp,first)){
			
			System.out.println("String match succeed!!");
		}else{
			System.out.println("Warnning!!: blank matching fail!!");		
			//return false;
			}
			
			
		
		/*
		if(str1==pattern){
			//System.out.println("Location blank match!!");
			return true;
		}else{
		System.out.println("Warnning!!: Location blank matching fail!!");		
		return false;
		}
		*/
	}
	//end of Check String


	private static void To_Check_theAccount(WebDriver driver, String Loginname) throws InterruptedException {
		// TODO Auto-generated method stub
		//Thread.sleep(10);
		System.out.println("Inside of func: To_Check...");
		
		try{
		
		//driver.findElement(By.className("btn dropdown-toggle")).click();
		//WebElement element1 = 
		//		driver.findElement(By.xpath("//div[@class = 'btn-group']/button[@class = 'btn dropdown-toggle']")).click();
		//element1.click();
		//driver.findElement(By.xpath("//a[@class='cmd_set_advanced']")).click();	
		//driver.findElement(By.xpath("//div[@class = 'ctn_header_login text-right style_auto_divider']/div[@class='btn-group']/button[@class='btn dropdown-toggle']")).click();
			//
			//************************************d2server admin page right header*****************************//
			driver.findElement(By.xpath("//div[@id='ctn_session_action']/div[@class='btn-group']/button[@class='btn dropdown-toggle']")).click();
		//("//div[@class='flex']/input[@id='password']"));
		
		//driver.findElement(By.xpath("//a[@class='cmd_set_advanced']")).click();
		//************************open the right header***********************************************//
			
			//WebElement elem = driver.findElement(By.xpath("//div[@id='ctn_session_action']/div[@class='btn-group open']/ul[@class='dropdown-menu pull-right']/li//a[@href='/ls/slsusers.php?user=5']"));
			
			
			//*************This is homework7/15*******************//
			
			
			String url= driver.getCurrentUrl();
			try{
			URL firefox = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(firefox.openStream()));
			String inputLine;
			int size=0;
			int K =4;
			String reader[] = null;
			reader = new String[100];
			
			while((inputLine = in.readLine())!=null){
				
				System.out.println(inputLine);
				
			}
			in.close();
			}catch(MalformedURLException e){
				
				System.out.println(e);
				
			}
			
			
			switch(Loginname){
			
			case "hiro1contributer": 
				driver.findElement(By.xpath("//div[@id='ctn_session_action']/div[@class='btn-group open']/ul[@class='dropdown-menu pull-right']/li//a[@href='/ls/slsusers.php?user=15']")).click();
			
			case"hiro2operator":
				driver.findElement(By.xpath("//div[@id='ctn_session_action']/div[@class='btn-group open']/ul[@class='dropdown-menu pull-right']/li//a[@href='/ls/slsusers.php?user=17']")).click();
			case"admin":
				driver.findElement(By.xpath("//div[@id='ctn_session_action']/div[@class='btn-group open']/ul[@class='dropdown-menu pull-right']/li//a[@href='/ls/slsusers.php?user=5']")).click();
			
			case"hiro3groupadmin":
			driver.findElement(By.xpath("//div[@id='ctn_session_action']/div[@class='btn-group open']/ul[@class='dropdown-menu pull-right']/li//a[@href='/ls/slsusers.php?user=16']")).click();
				
			default:
				{System.out.println("Error when you move to Account Page. Plase check the xpath in AccountPage.java");
				break;
				}
			}
			//driver.findElement(By.xpath("//div[@id='ctn_session_action']/div[@class='btn-group open']/ul[@class='dropdown-menu pull-right']/li//a[@href='/ls/slsusers.php?user=5']")).click();
				//+ "//a[@class = 'cmd_manage_account']")).click();
				
		
		//driver.findElement(By.xpath("//a[@class='cmd_set_advanced']")).click();
		System.out.println("Succeeded in going advanced mode in account menue");
		
		}catch(Exception e){
			
			System.out.println("error occur in func: To_Check_the_account_..");
			
		}
		
		
	}


	//*******************************************//
	
	
}//end of PageObject class
