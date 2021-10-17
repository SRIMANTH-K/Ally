package com.deque.axe;

import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sampletest {

	private static final URL scriptUrl = Sampletest.class.getResource("/axe.min.js");
	
	private WebDriver driver;
	
	@Test
	public void testOne(){
		WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	 driver.get("https://www.amazon.com");
	 AXE.inject(driver, scriptUrl);
	 JSONObject responseJSON = new AXE.Builder(driver,    scriptUrl).analyze();
	 JSONArray violations = responseJSON.getJSONArray("violations");
	 if (violations.length() == 0)
	 {
	    Assert.assertTrue(true, "No violations found");
	 }
	 else {
	    AXE.writeResults("path & name of the file you want to save the  report", responseJSON);
	    Assert.assertTrue(false, AXE.report(violations));
	 }
	}
	
	
}
