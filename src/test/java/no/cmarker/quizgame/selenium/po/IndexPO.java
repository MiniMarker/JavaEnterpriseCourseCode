package no.cmarker.quizgame.selenium.po;

import org.openqa.selenium.WebDriver;

import static junit.framework.TestCase.assertTrue;

/**
 * @author Christian Marker on 05/03/2018 at 16:39.
 */
public class IndexPO extends PageObject {
	
	public IndexPO(WebDriver driver, String host, int port) {
		super(driver, host, port);
	}
	
	@Override
	public boolean isOnPage() {
		return getDriver().getTitle().contains("QuizGame");
	}
	
	public void toStartingPage(){
		driver.get(host + ":" + port);
	}
	
	public MatchPO startNewMatch(){
		clickAndWait("newMatchBtnId");
		MatchPO po = new MatchPO(this);
		assertTrue(po.isOnPage());
		
		return po;
	}
	
	
}
