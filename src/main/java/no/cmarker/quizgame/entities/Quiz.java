package no.cmarker.quizgame.entities;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Christian Marker on 26/02/2018 at 16:14.
 */
@Entity
public class Quiz {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@ManyToOne
	private SubCategory subCategory;
	
	@NotBlank
	@Size(max = 128)
	@Column(unique=true)
	private String question;
	
	@NotBlank
	@Size(max = 128)
	private String alt1;
	
	@NotBlank
	@Size(max = 128)
	private String alt2;
	
	@NotBlank
	@Size(max = 128)
	private String alt3;
	
	@NotBlank
	@Size(max = 128)
	private String alt4;
	
	@Range(min = 0, max = 3)
	private int correctAnswer;
	
	/**
	 * Constructors
	 */
	public Quiz() {
		//Empty on purpose
	}
	
	public Quiz(String question, String alt1, String alt2, String alt3, String alt4, int correctAnswer, SubCategory subCategory) {
		this.question = question;
		this.alt1 = alt1;
		this.alt2 = alt2;
		this.alt3 = alt3;
		this.alt4 = alt4;
		this.correctAnswer = correctAnswer;
		this.subCategory = subCategory;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getAlt1() {
		return alt1;
	}
	
	public void setAlt1(String alt1) {
		this.alt1 = alt1;
	}
	
	public String getAlt2() {
		return alt2;
	}
	
	public void setAlt2(String alt2) {
		this.alt2 = alt2;
	}
	
	public String getAlt3() {
		return alt3;
	}
	
	public void setAlt3(String alt3) {
		this.alt3 = alt3;
	}
	
	public String getAlt4() {
		return alt4;
	}
	
	public void setAlt4(String alt4) {
		this.alt4 = alt4;
	}
	
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	
	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	public SubCategory getSubCategory() {
		return subCategory;
	}
	
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}
	
	@Override
	public String toString() {
		return "Quiz{" +
				"id=" + id +
				", question='" + question + '\'' +
				", alt1='" + alt1 + '\'' +
				", alt2='" + alt2 + '\'' +
				", alt3='" + alt3 + '\'' +
				", alt4='" + alt4 + '\'' +
				", correctAnswer=" + correctAnswer +
				", subCategory=" + subCategory +
				'}';
	}
	
	public String formattedToString(){
		return "Quiz " + id +
				"\nSubCategory: " + subCategory +
				"\nQuestion: " + question +
				"\nAlt 1: " + alt1 +
				"\nAlt 2: " + alt2 +
				"\nAlt 3: " + alt3 +
				"\nAlt 4: " + alt4 +
				"\nCorrect answer: " + correctAnswer;
	}
}
