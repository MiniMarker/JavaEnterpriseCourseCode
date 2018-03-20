package no.cmarker.quizgame.services;

import no.cmarker.quizgame.QuizgameApplication;
import no.cmarker.quizgame.entities.Quiz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Christian Marker on 26/02/2018 at 17:02.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuizgameApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class QuizServiceTest extends ServiceTestBase {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private QuizService quizService;
	
	@Test
	public void testNoQuiz() {
		assertEquals(0, quizService.getQuizzes().size());
	}
	
	@Test
	public void testCreateQuiz() {
		long subId = createCtgAndSub("foo", "bar");
		
		quizService.createQuiz(subId, "FooBar", "yes", "no", "maybe?", "hellno!", 0);
		assertEquals(1, quizService.getQuizzes().size());
		
	}
	
	@Test
	public void wrongNumOfTestsCreated() {
		
		long ctgId = testCreateQuizzes("one", "two", "three");
		
		try {
			quizService.getRandomQuizzes(5, ctgId);
			fail();
		} catch (Exception e) {
			//excepted
		}
	}
	
	@Test
	public void correctNumOfTestsCreated() {
		
		long ctgId = testCreateQuizzes("one", "two", "three");
		
		List<Quiz> result = quizService.getRandomQuizzes(3, ctgId);
		
		assertEquals(3, result.size());
	}
	
	//HELPING METHODS
	
	public long testCreateQuizzes(String... names) {
		
		long subId = createCtgAndSub("foo", "bar");
		
		for (String name : names) {
			quizService.createQuiz(subId, name, "1", "2", "3", "4", 0);
		}
		
		return categoryService.getSubCategory(subId).getCategory().getId();
	}
	
	private long createCtgAndSub(String ctgName, String subName) {
		long ctgId = categoryService.createCategory(ctgName);
		return categoryService.createSubCategory(ctgId, subName);
	}
}
