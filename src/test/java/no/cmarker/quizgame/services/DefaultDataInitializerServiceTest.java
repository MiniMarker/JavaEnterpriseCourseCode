package no.cmarker.quizgame.services;

import no.cmarker.quizgame.QuizgameApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;

/**
 * @author Christian Marker on 26/02/2018 at 17:19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuizgameApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = BEFORE_CLASS)
public class DefaultDataInitializerServiceTest {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private QuizService quizService;
	
	@Test
	public void testInit(){
		assertTrue(categoryService.getAllCategories(false).size() > 0);
		
		assertTrue(categoryService.getAllCategories(true).stream()
		.mapToLong(c -> c.getSubCategories().size()).sum() > 0);
		
		assertTrue(quizService.getQuizzes().size() > 0);
	}
	
}
