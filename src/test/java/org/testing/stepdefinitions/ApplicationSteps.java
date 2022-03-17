package org.testing.stepdefinitions;

import org.testing.factory.PageFactory;
import org.testing.pages.LoginPage;
import org.testing.utils.Browsers;

import com.microsoft.playwright.Page;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class ApplicationSteps{
	
	LoginPage loginPageA;
	Page pageA = PageFactory.getPage(Browsers.BrowserA);
	LoginPage loginPageB;
	Page pageB = PageFactory.getPage(Browsers.BrowserB);
	
	@Given("host launched meeting application using {string}")
	public void host_launched_meeting_application(String applicationUrl) {
			loginPageA = new LoginPage(pageA);	
			pageA.bringToFront();
			pageA.navigate(applicationUrl);
	}
	
	@And("guest launched meeting application using {string}")
	public void guest_launched_meeting_application(String applicationUrl) {
			loginPageB = new LoginPage(pageA);		
			pageB.navigate(applicationUrl);
	}
	
	@And("host navigates to {string}")
	public void host_navigates_url(String appUrl){
		pageA.bringToFront();
		pageA.navigate(appUrl);
	}

}
