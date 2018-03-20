package no.cmarker.quizgame.services;

import no.cmarker.quizgame.entities.Category;
import no.cmarker.quizgame.entities.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Christian Marker on 26/02/2018 at 16:16.
 */
@Service
@Transactional
public class CategoryService {
	
	@Autowired
	private EntityManager em;
	
	
	public CategoryService() {
	}
	
	/**
	 * Create a new category
	 * @param name name og the instance
	 * @return id of the category
	 */
	public Long createCategory(String name) {
		
		Category category = new Category();
		category.setName(name);
		
		em.persist(category);
		return category.getId();
	}
	
	/**
	 * Create a new SubCategory
	 * @param categoryId id of parent category
	 * @param name name of the SubCategory
	 * @return id of the category
	 */
	public Long createSubCategory(long categoryId, String name) {
		
		Category category = getCategoryById(categoryId);
		
		//Checks if the category exist
		if (category == null) {
			throw new IllegalArgumentException("Category with id: " + categoryId + "doeasn't exist");
		}
		
		SubCategory subCategory = new SubCategory();
		subCategory.setName(name);
		subCategory.setCategory(category);
		em.persist(subCategory);
		
		category.getSubCategories().size();
		
		return subCategory.getId();
	}
	
	/**
	 * Get the instance of subcategory
	 * @param id id of the wanted category instance
	 * @param withSub boolean if you want to load the subcategories
	 * @return
	 */
	public Category getCategory(long id, boolean withSub) {
		Category category = getCategoryById(id);
		
		if (withSub && getCategoryById(id) != null) {
			category.getSubCategories().size();
		}
		
		return category;
		
	}
	
	/**
	 * Get the instance of subcategory
	 * @param id id of the wanted subcategory instance
	 * @return instance of category
	 */
	public SubCategory getSubCategory(long id) {
		
		return em.find(SubCategory.class, id);
	}
	
	/**
	 * retrun all categories in database
	 * @param withSubCat boolean if you want to load the subcategories
	 * @return List of all categories
	 */
	public List<Category> getAllCategories(boolean withSubCat) {
		
		TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c", Category.class);
		List<Category> resultList = query.getResultList();
		
		if (withSubCat) {
			resultList.forEach(c -> c.getSubCategories().size());
		}
		
		return resultList;
	}
	
	/**
	 * Helping methods
	 */
	
	private Category getCategoryById(Long id) {
		
		return em.find(Category.class, id);
	}
}
