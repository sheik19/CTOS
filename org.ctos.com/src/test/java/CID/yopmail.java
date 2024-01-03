package CID;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;


public class yopmail {

	public static void main(String[] args) {
		WebDriver driver;
		// TODO Auto-generated method stub
		try {
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		//ChromeOptions chromeOptions = new ChromeOptions();
		ChromeOptions opt=new ChromeOptions();
        opt.addArguments("--remote-allow-origins=*");
        //Launching the browser
        driver=new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Navigate to the demoqa website
		driver.get("https://yopmail.com/en/");
		driver.findElement(By.xpath("//input[@class='ycptinput']")).sendKeys("Test1226130@yopmail.com");
		driver.findElement(By.xpath("//i[@class='material-icons-outlined f36']")).click();
		
		driver.switchTo().frame("ifinbox");
		driver.findElement(By.xpath("//div[text()='CTOS Identity Registration']")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("ifmail");
		String emailContent = driver.findElement(By.xpath("//main[@class='yscrollbar']")).getText();
		System.out.println(emailContent);
		String nic=driver.findElement(By.xpath("//*[@id='mail']/div/center/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td/table/tbody/tr/td/table[1]/tbody/tr/td/table/tbody/tr[1]/td[2]")).getText();		
		System.out.println(nic);	
		String password=driver.findElement(By.xpath("//*[@id='mail']/div/center/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td/table/tbody/tr/td/table[1]/tbody/tr/td/table/tbody/tr[2]/td[2]/span/strong")).getText();		
		System.out.println(password);
		String email=driver.findElement(By.xpath("//*[@id='mail']/div/center/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td/table/tbody/tr/td/table[1]/tbody/tr/td/table/tbody/tr[3]/td[2]/strong")).getText();
		System.out.println(email);
		String mobile=driver.findElement(By.xpath("//*[@id='mail']/div/center/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td/table/tbody/tr/td/table[1]/tbody/tr/td/table/tbody/tr[4]/td[2]/strong")).getText();
		System.out.println(mobile);		
		driver.findElement(By.xpath("//a[text()='Login']")).click();
		String window1=driver.getWindowHandle();
		//String[] content=emailContent.split(" ");
		//System.out.println(content);
		driver.switchTo().newWindow(WindowType.TAB);
		String window2=driver.getWindowHandle();
		driver.get("https://uat-ctosid.ctos.com.my/ctosid_new/LoginPage");

		//driver.switchTo().alert().accept();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(nic);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password) ;
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.switchTo().newWindow(WindowType.TAB);
		String window3=driver.getWindowHandle();
		driver.get("https://yopmail.com/en/");
		driver.findElement(By.xpath("//input[@class='ycptinput']")).clear();
		driver.findElement(By.xpath("//input[@class='ycptinput']")).sendKeys("Test1226130@yopmail.com");
		driver.findElement(By.xpath("//i[@class='material-icons-outlined f36']")).click();
		driver.switchTo().frame("ifinbox");
		driver.findElement(By.xpath("//div[@class='m'][1]//div[text()='Verify Your Login']")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("ifmail");
		String OTP=driver.findElement(By.xpath("//table[3]/tbody/tr/td/table/tbody/tr/td/p[2]/strong")).getText();
		System.out.println(OTP);  
		driver.switchTo().defaultContent();
		driver.switchTo().window(window2);
		driver.findElement(By.xpath("//input[@name='cacCode']")).sendKeys(OTP);
		driver.findElement(By.xpath("//button[@id='verifyId']")).click();
		driver.quit();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		

	}

}
