package no.cmarker.quizgame.services;

import no.cmarker.quizgame.entities.Quiz;
import no.cmarker.quizgame.entities.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;

/**
 * @author Christian Marker on 26/02/2018 at 16:26.
 */
@Service
@Transactional
public class QuizService {
	
	@Autowired
	private EntityManager em;
	
	public QuizService(){
	
	}
	
	public long createQuiz(long subCategoryId,
	                       String question,
	                       String alt1,
	                       String alt2,
	                       String alt3,
	                       String alt4,
	                       int correctAnswer){
		
		SubCategory subCategory = em.find(SubCategory.class, subCategoryId);
		if (subCategory == null){
			throw new IllegalArgumentException("subcategory with id=" + subCategoryId + " does not exist");
		}
		
		Quiz quiz = new Quiz();
		quiz.setQuestion(question);
		quiz.setAlt1(alt1);
		quiz.setAlt2(alt2);
		quiz.setAlt3(alt3);
		quiz.setAlt4(alt4);
		quiz.setCorrectAnswer(correctAnswer);
		quiz.setSubCategory(subCategory);
		
		em.persist(quiz);
		return quiz.getId();
	}
	
	public Quiz getQuiz(long id){
		return em.find(Quiz.class, id);
	}
	
	public List<Quiz> getQuizzes(){
		TypedQuery<Quiz> query = em.createQuery("SELECT q FROM Quiz q", Quiz.class);
		return query.getResultList();
	}
	
	public List<Quiz> getRandomQuizzes(int n, long categoryId){
		
		//get number of total rows in DB
		TypedQuery<Long> sizeQuery = em.createQuery("SELECT count(q) FROM Quiz q where q.subCategory.category.id=?1", Long.class);
		sizeQuery.setParameter(1, categoryId);
		long numberOfRows = sizeQuery.getSingleResult();
		
		if (n > numberOfRows){
			throw new IllegalArgumentException("Cannot choose " + n + " unique quizzes out of the " + numberOfRows + " existing");
		}
		
		//pick random Quiz
		Random rnd = new Random();
		List<Quiz> resultList = new ArrayList<>();
		Set<Integer> chosen = new HashSet<>();
		
		while (chosen.size() < n) {
			int quizId = rnd.nextInt((int) numberOfRows);
			
			if (chosen.contains(quizId)){
				continue;
			}
			
			chosen.add(quizId);
			
			//Select chosen row from DB
			TypedQuery<Quiz> query = em.createQuery("SELECT q FROM Quiz q WHERE q.subCategory.category.id=?1", Quiz.class);
			query.setParameter(1, categoryId);
			query.setMaxResults(1);
			query.setFirstResult(quizId);
			
			resultList.add(query.getSingleResult());
		}
		return resultList;
	}
	
}
