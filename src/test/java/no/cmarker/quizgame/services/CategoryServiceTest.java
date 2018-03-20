package no.cmarker.quizgame.services;

import no.cmarker.quizgame.QuizgameApplication;
import no.cmarker.quizgame.entities.Category;
import no.cmarker.quizgame.entities.SubCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * @author Christian Marker on 26/02/2018 at 17:05.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuizgameApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CategoryServiceTest extends ServiceTestBase {
	
	@Autowired
	private CategoryService categoryEjb;
	
	@Test
	public void testNoCategory() {
		List<Category> resultList = categoryEjb.getAllCategories(true);
		assertTrue(resultList.size() == 0);
	}
	
	@Test
	public void testCreateCategory() {
		long id = categoryEjb.createCategory("Test");
		assertNotNull(id);
		
	}
	
	@Test
	public void testGetCategory() {
		
		String catName = "Test";
		
		long id = categoryEjb.createCategory(catName);
		
		Category category = categoryEjb.getCategory(id, false);
		
		assertEquals(catName, category.getName());
	}
	
	@Test
	public void testCreateSubCategory() {
		
		String catName = "Foo";
		long idCat = categoryEjb.createCategory(catName);
		Category category = categoryEjb.getCategory(idCat, true);
		
		String subName = "Bar";
		long idSub = categoryEjb.createSubCategory(idCat, subName);
		SubCategory subCategory = categoryEjb.getSubCategory(idSub);
		
		/*
		SPØR OM DETTE!
		category.getSubCategories().add(subCategory);
		
		System.out.println(category.getSubCategories().size());
		
		assertEquals(1, category.getSubCategories().size());
		*/
		
		assertEquals((Long) idCat, subCategory.getCategory().getId());
		assertEquals(subName, subCategory.getName());
	}
	
	@Test
	public void testGetAllCategories() {
		long idCat1 = categoryEjb.createCategory("Category 1");
		long idSub1 = categoryEjb.createSubCategory(idCat1, "SubCategory 1, Category 1");
		
		long idCat2 = categoryEjb.createCategory("Category 2");
		long idSub2 = categoryEjb.createSubCategory(idCat2, "SubCategory 2, Category 2");
		
		long idCat3 = categoryEjb.createCategory("Category 3");
		long idSub3 = categoryEjb.createSubCategory(idCat3, "SubCategory 3, Category 3");
		
		List<Category> categoryList = categoryEjb.getAllCategories(false);
		assertEquals(3, categoryList.size());
		assertEquals(1, categoryEjb.getCategory(idCat1, true).getSubCategories().size());
		
	}
	
	@Test
	public void testCreateTwice() {
		
		String catName = "Math";
		
		long idCat1 = categoryEjb.createCategory(catName);
		
		try {
			long idCat2 = categoryEjb.createCategory(catName);
			fail();
		} catch (Exception e) {
			//forventet
		}
		
	}
	
}
