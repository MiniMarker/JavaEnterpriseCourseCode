package no.cmarker.quizgame.controller;

import no.cmarker.quizgame.entities.Category;
import no.cmarker.quizgame.entities.Quiz;
import no.cmarker.quizgame.services.CategoryService;
import no.cmarker.quizgame.services.MatchStatsService;
import no.cmarker.quizgame.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;


import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.util.List;

/**
 * @author Christian Marker on 26/02/2018 at 16:29.
 */
@Named
@SessionScoped
public class MatchController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private QuizService quizService;
	
	@Autowired
	private MatchStatsService statsService;
	
	@Autowired
	private UserInfoController infoController;
	
	private final int NUMBER_OF_QUESTION = 3;
	
	private boolean isOngoingMatch = false;
	private Long selectedCategory;
	private List<Quiz> quizList;
	private int counter;
	
	private String txtFieldCategory;
	private String txtFieldSub;
	
	private boolean adminCategorySelected = false;
	private boolean adminSubCategorySelected = false;
	private boolean adminQuizSelected = false;
	
	/**
	 * Check if there is an ongoing match.
	 *
	 * @return true if match is ongoing
	 */
	public boolean isMatchOn() {
		return isOngoingMatch;
	}
	
	/**
	 * Start a new match.
	 *
	 * @return redirect-url with params
	 */
	public String newMatch() {
		
		String username = infoController.getUsername();
		
		if (isOngoingMatch){
			statsService.reportDefeat(username);
		}
		
		isOngoingMatch = true;
		selectedCategory = null;
		return "/ui/match.jsf&faces-redirect=true";
	}
	
	/**
	 * Return all available categories.
	 *
	 * @return List of all available categories
	 */
	public List<Category> getCategories() {
		return categoryService.getAllCategories(false);
	}
	
	/**
	 * Check if the category for the current match has been selected.
	 *
	 * @return true if a category is selected
	 */
	public boolean isCategorySelected() {
		return selectedCategory != null;
	}
	
	/**
	 * Select a category with the given id for the current match.
	 * Fills quizList with questions to be asked
	 *
	 * @param catId category to be selected
	 */
	public void selectCategory(long catId) {
		selectedCategory = catId;
		counter = 0;
		quizList = quizService.getRandomQuizzes(NUMBER_OF_QUESTION, selectedCategory);
	}
	
	/**
	 * Return the current quiz (out of 3) in the match.
	 *
	 * @return
	 */
	public Quiz getCurrentQuiz() {
		return quizList.get(counter);
	}
	
	/**
	 * Answer the current quiz with the answer at the given index (e.g., 0 to 3 included).
	 * If answer is wrong, exit quiz
	 *
	 * @param index of selected question
	 * @return redirect-url with params
	 */
	public String answerQuiz(int index) {
		
		Quiz quiz = getCurrentQuiz();
		
		String username = infoController.getUsername();
		
		if (index == quiz.getCorrectAnswer()) {
			counter++;
			if (counter == NUMBER_OF_QUESTION) {
				isOngoingMatch = false;
				statsService.reportVictory(username);
				return "/ui/result.jsf?victory=true&faces-redirect=true";
			}
		} else {
			isOngoingMatch = false;
			statsService.reportDefeat(username);
			return "/ui/result.jsf?defeat=true&faces-redirect=true";
		}
		return null;
	}
	
	public int getIncreasedCounter() {
		return counter + 1;
	}
	
	public int getNumberOfQuizzes() {
		return NUMBER_OF_QUESTION;
	}
	
	
	/*
	ADMIN
	 */
	public void createCategory() {
		categoryService.createCategory(txtFieldCategory);
		System.out.println("Category: " + txtFieldCategory);
		txtFieldCategory = "";
	}
	
	public void createSubCategory(int categoryId){
		categoryService.createSubCategory(categoryId, txtFieldSub);
		System.out.println("Sub created: " + txtFieldSub);
		txtFieldSub = "";
		
	}
	
	public String getTxtFieldCategory() {
		return txtFieldCategory;
	}
	
	public void setTxtFieldCategory(String txtFieldCategory) {
		this.txtFieldCategory = txtFieldCategory;
	}
	
	public String getTxtFieldSub() {
		return txtFieldSub;
	}
	
	public void setTxtFieldSub(String txtFieldSub) {
		this.txtFieldSub = txtFieldSub;
	}
}
