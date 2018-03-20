package no.cmarker.quizgame.selenium.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;

/**
 * @author Christian Marker on 05/03/2018 at 16:39.
 */
public class MatchPO extends PageObject {
	public MatchPO(WebDriver driver, String host, int port) {
		super(driver, host, port);
	}
	
	public MatchPO(PageObject po) {
		this(po.getDriver(), po.getHost(), po.getPort());
	}
	
	@Override
	public boolean isOnPage() {
		return getDriver().getTitle().contains("Match");
	}
	
	public boolean canSelectCategory() {
		//returns true of there are showing > 0 categories on screen
		return getCategoryIds().size() > 0;
	}
	
	public List<String> getCategoryIds() {
		return getDriver().findElements(By.xpath("//input[@data-ctgid]"))
				.stream()
				.map(e -> e.getAttribute("data-ctgid"))
				.collect(Collectors.toList());
	}
	
	public void selectCategory(int categoryId) {
		clickAndWait("ctgBtnId_" + categoryId);
	}
	
	public boolean isElementDisplayed(String elementId) {
		
		return getDriver().findElement(By.id(elementId)).isDisplayed();
	}
	
	public long getQuizId(){
		String id = getDriver().findElement(By.xpath("//*[@data-quizid]")).getAttribute("data-quizid");
		assertNotNull(id);
		return Long.parseLong(id);
	}
	
	public void answerQuestion(int index){
		clickAndWait("quizAlt" + index);
	}
}

// /html/body/form[1]/input[*INDEX*]
