package no.cmarker.quizgame.entities;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Christian Marker on 26/02/2018 at 16:14.
 */
@Entity
public class SubCategory {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank
	@Size(max=128)
	private String name;
	
	@NotNull
	@ManyToOne
	private Category category;
	
	/**
	 * Constructor
	 */
	public SubCategory() {
		//Empty on purpose
	}
	
	public SubCategory(String name, Category category) {
		this.name = name;
		this.category = category;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "SubCategory{" +
				"id=" + id +
				", name='" + name + '\'' +
				", category=" + category +
				'}';
	}
}
