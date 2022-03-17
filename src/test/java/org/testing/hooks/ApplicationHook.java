package org.testing.hooks;

import java.util.Properties;

import org.testing.factory.PageFactory;
import org.testing.utils.Browsers;
import org.testing.utils.ConfigurationReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHook {
	
	private PageFactory pageFactory;
	private ConfigurationReader configurationReader;
	Properties prop;

	@Before(order = 0)
	public void getProperty() {
		configurationReader = new ConfigurationReader();
		prop = configurationReader.init_prop();
	}

	@Before(order = 1)
	public void launchBrowser() {
		pageFactory = new PageFactory();
		pageFactory.init_pages(Browsers.BrowserA, prop.getProperty("browserA"));
		pageFactory.init_pages(Browsers.BrowserB, prop.getProperty("browserB"));
	}

	@After(order = 0)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {}
	}
}
