package no.cmarker.quizgame.services;

import no.cmarker.quizgame.entities.Category;
import no.cmarker.quizgame.entities.Quiz;
import no.cmarker.quizgame.entities.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Christian Marker on 26/02/2018 at 16:58.
 */
@Service
@Transactional
public class ResetService {
	
	@PersistenceContext
	private EntityManager em;
	
	public void resetDatabase() {
		deleteEntity(Quiz.class, SubCategory.class, Category.class);
	}
	
	private void deleteEntity(Class<?>... entities) {
		
		/*
		 * We use Class<?> to avoid SQL-Injection!
		 */
		
		for (Class<?> entity : entities){
			String name = entity.getSimpleName();
			Query query = em.createQuery("delete from " + name);
			query.executeUpdate();
		}
	}
}
