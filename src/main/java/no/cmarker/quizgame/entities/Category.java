package no.cmarker.quizgame.entities;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Christian Marker on 26/02/2018 at 16:14.
 */
@Entity
public class Category {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank
	@Size(max=128)
	@Column(unique=true)
	private String name;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<SubCategory> subCategories;
	
	/**
	 * Constructor
	 */
	public Category() {
		subCategories = new ArrayList<>();
	}
	
	public Category(String name, List<SubCategory> subCategories) {
		this.name = name;
		this.subCategories = new ArrayList<>();
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
	
	public List<SubCategory> getSubCategories() {
		return subCategories;
	}
	
	public void setSubCategories(List<SubCategory> subCategories) {
		this.subCategories = subCategories;
	}
	
	@Override
	public String toString() {
		return "Category{" +
				"id=" + id +
				", name='" + name + '\'' +
				", subCategories=" + subCategories +
				'}';
	}
}
