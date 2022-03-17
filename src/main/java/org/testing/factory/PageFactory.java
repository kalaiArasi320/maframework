package org.testing.factory;

import java.util.HashMap;

import org.testing.utils.Browsers;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PageFactory {

	Page pageA;
	Page pageB;
	Browser browserA;
	Browser browserB;
	
	private static HashMap<Browsers,Page> pageMap = new HashMap<>();
	private static HashMap<Browsers, Browser> browserMap = new HashMap<>();

	
	public void init_pages(Browsers browserId, String browserTypeAsString){
		BrowserType browserType = null;
		switch (browserTypeAsString) {
		case "firefox":
			browserType = Playwright.create().firefox();
			break;
		case "chromium":
			browserType = Playwright.create().chromium();
			break;
		case "webkit":
			browserType = Playwright.create().webkit();
			break;

		}
		if (browserType == null) {
			throw new IllegalArgumentException("Could not launch a browser for type " + browserTypeAsString);
		}
		
		if(browserId == Browsers.BrowserA){
			browserA = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
			browserMap.put(Browsers.BrowserA,browserA);
			pageA = browserA.newPage();
			pageMap.put(Browsers.BrowserA,pageA);
		}else{
			browserB = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
			browserMap.put(Browsers.BrowserB,browserB);
			pageB = browserB.newPage();
			pageMap.put(Browsers.BrowserB,pageB);
		}
	}
	
	public static synchronized Page getPage(Browsers browserId) {
		if(browserId == Browsers.BrowserB)
			return pageMap.get(Browsers.BrowserB);
		else
			return pageMap.get(Browsers.BrowserA);
	}
	
	public static synchronized Browser getBrowser(Browsers browserId) {
		if(browserId == Browsers.BrowserB)
			return browserMap.get(Browsers.BrowserB);
		else
			return browserMap.get(Browsers.BrowserA);
	}
}


