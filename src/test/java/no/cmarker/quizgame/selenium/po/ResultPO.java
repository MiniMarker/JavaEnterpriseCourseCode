package no.cmarker.quizgame.selenium.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Christian Marker on 05/03/2018 at 16:39.
 */
public class ResultPO extends PageObject {
	
	public ResultPO(PageObject po){
		super(po);
	}
	
	@Override
	public boolean isOnPage() {
		return getDriver().getTitle().contains("Match Result");
	}
	
	public boolean playerWon(){
		return getDriver().findElement(By.id("winText")).isDisplayed();
	}
	
	public boolean playerLost(){
		return getDriver().findElement(By.id("lostText")).isDisplayed();
	}
}
