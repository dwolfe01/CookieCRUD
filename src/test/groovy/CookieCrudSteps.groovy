package com.wolfesoftware

import static org.junit.Assert.assertEquals

import cucumber.api.groovy.EN
import cucumber.api.groovy.Hooks
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

this.metaClass.mixin(Hooks)
this.metaClass.mixin(EN)

System.setProperty("webdriver.chrome.driver", "/Users/dwolfe/development/chromedriver");
ChromeDriver driver = new ChromeDriver();

Given(~'I get page (.*)'){ String url ->
	driver.get(url)
	//new File('target/test.htm').write(driver.getPageSource())
}

Given(~'I see page title (.*)'){title ->
	WebElement element = driver.findElementByXPath("//title")
	assertEquals(title,element.text)
	//new File('target/test.htm').write(driver.getPageSource())
}