package no.cmarker.quizgame.selenium;

import no.cmarker.quizgame.QuizgameApplication;
import no.cmarker.quizgame.selenium.po.IndexPO;
import no.cmarker.quizgame.selenium.po.MatchPO;
import no.cmarker.quizgame.selenium.po.SeleniumDriverHandler;
import no.cmarker.quizgame.services.QuizService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static junit.framework.TestCase.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * @author Christian Marker on 05/03/2018 at 16:41.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuizgameApplication.class, webEnvironment = RANDOM_PORT)
public class SeleniumLocalIT{
	
	private static WebDriver driver;
	private IndexPO index;
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private QuizService quizService;
	
	@BeforeClass
	public static void initClass(){
		
		driver = SeleniumDriverHandler.getChromeDriver();
		
		if (driver == null){
			throw new AssumptionViolatedException("Cannot find/initialize Chrome driver");
		}
	}
	
	@AfterClass
	public static void tearDown() {
		if(driver != null) {
			driver.close();
		}
	}
	
	@Before
	public void initTest(){
		driver.manage().deleteAllCookies();
		
		index = new IndexPO(driver, "localhost", port);
		index.toStartingPage();
		Assert.assertTrue("Failed to start from Home Page", index.isOnPage());
	}
	
	// -------------------- TESTS --------------------
	
	@Test
	public void testLoadMatch(){
		MatchPO po = index.startNewMatch();
		assertTrue(po.canSelectCategory());
	}
	
	@Test
	public void testFirstQuiz() {
		MatchPO po = index.startNewMatch();
		if (po.canSelectCategory()){
			po.selectCategory(1);
			
			assertTrue(po.isElementDisplayed("questionText"));
		}
	}
	
	@Test
	public void loseQuiz(){
		MatchPO po = index.startNewMatch();
		if (po.canSelectCategory()) {
			po.selectCategory(1);
			
			assertTrue(po.isElementDisplayed("questionText"));
			
			long quizId = po.getQuizId();
			int correctAnswerId = quizService.getQuiz(quizId).getCorrectAnswer();
			int tmpAnswer = new Random().nextInt(3);
			
			while (tmpAnswer == correctAnswerId){
				tmpAnswer = new Random().nextInt(3);
			}
			po.answerQuestion(tmpAnswer);
			assertTrue(po.isElementDisplayed("lostText"));
		}
	}
	
	@Test
	public void winQuiz(){
		MatchPO po = index.startNewMatch();
		if (po.canSelectCategory()){
			po.selectCategory(1);
			
			assertTrue(po.isElementDisplayed("questionText"));
			
			int numberOfQuestions = 3;
			
			for (int i = 1; i <= numberOfQuestions; i++) {
				long quizId = po.getQuizId();
				int correctAnswerId = quizService.getQuiz(quizId).getCorrectAnswer();
				
				po.answerQuestion(correctAnswerId);
				
			}
			assertTrue(po.isElementDisplayed("winText"));
		}
	}
}
