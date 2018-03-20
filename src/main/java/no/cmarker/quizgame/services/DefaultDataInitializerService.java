package no.cmarker.quizgame.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.function.Supplier;

/**
 * @author Christian Marker on 26/02/2018 at 16:18.
 */
@Service
public class DefaultDataInitializerService {
	
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private QuizService quizService;
	
	@PostConstruct
	public void init(){
		
		Long ctgM = attempt(() -> categoryService.createCategory("Math"));
		Long ctgSE = attempt(() -> categoryService.createCategory("Software Engineering"));
		
		Long subCtgAlg = attempt(() -> categoryService.createSubCategory(ctgM, "Algebra"));
		Long subCtgTri = attempt(() -> categoryService.createSubCategory(ctgM, "Trigonometry"));
		Long subCatJava = attempt(() -> categoryService.createSubCategory(ctgSE, "Java"));
		Long subCatAlgDat = attempt(() -> categoryService.createSubCategory(ctgSE, "Algorithms and Data Structures"));
		
		//insert createMethods here
		createAlgDatQuestions(subCatAlgDat);
		createAlgebraQuestions(subCtgAlg);
		createJavaQuestions(subCatJava);
		createTrigonometryQuestions(subCtgTri);
		
	}
	
	private  <T> T attempt(Supplier<T> lambda){
		try{
			return lambda.get();
		} catch (Exception e){
			return null;
		}
	}
	
	private void createAlgebraQuestions(Long subCategory){
		quizService.createQuiz(subCategory,
				"30 - 12 ÷ 3 × 2 = ",
				"12",
				"3",
				"null",
				"22",
				3);
		quizService.createQuiz(subCategory,
				"(-b +- sqrt(b^x -4ac)) / 2a     What is x?",
				"1",
				"2",
				"3",
				"4",
				1);
		quizService.createQuiz(subCategory,
				"ln(a*b) = ?",
				"ln a + ln b",
				"ln a * ln b",
				"ln(a)^b",
				"ln (a)^ln b",
				0);
	}
	
	private void createTrigonometryQuestions(Long subCategory){
		quizService.createQuiz(subCategory,
				"What is the definition of pi?",
				"3.1353",
				"3,1415",
				"3,1412",
				"4,1415",
				1);
		quizService.createQuiz(subCategory,
				"What is the equation for area of a cricle",
				"pi * d",
				"pi + r^2",
				"pi * r^2",
				"pi * d^2",
				2);
		quizService.createQuiz(subCategory,
				"What is pi?",
				"Just a random number",
				"A number which is based on growth of all living organisms in the universe",
				"Area of a circle with r = 1cm",
				"Circumference of a circle with d = 1cm",
				3);
	}
	
	private void createJavaQuestions(Long subCategory){
		quizService.createQuiz(
				subCategory,
				"What is a 'volatile' variable?",
				"Java does not have volatile variables",
				"A variable whose value cannot be changed",
				"A variable whose value might change every time it is read",
				"A variable whose value is never cached",
				3);
		quizService.createQuiz(
				subCategory,
				"What is a 'final' variable?",
				"A variable that can be used only when the JVM is shutting down",
				"A variable whose value cannot be changed once initialized",
				"A variable whose value might change every time it is read",
				"Java does not have final variables",
				1);
	}
	
	private void createAlgDatQuestions(Long subCategory){
		quizService.createQuiz(
				subCategory,
				"What best describe the runtime complexity of binary search?",
				"5n",
				"O(n)",
				"O(log n)",
				"O(n log n)",
				2);
		
		quizService.createQuiz(
				subCategory,
				"What can you say about sorting algorithms?",
				"Merge Sort is strictly better than Quick Sort",
				"Quick Sort is strictly better than Merge Sort",
				"Bubble Sort is better than Merge/Quick Sort, as it uses less space",
				"Merge Sort is asymptotically optimal in the number of comparisons",
				3);
	}
}
