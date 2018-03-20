package no.cmarker.quizgame.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Christian Marker on 12/03/2018 at 15:54.
 */
@Entity
public class MatchStats {

	@Id @GeneratedValue
	private Long id;
	
	@OneToOne
	private User user;
	
	@NotNull @Min(0)
	private int victories;
	
	@NotNull @Min(0)
	private int defeats;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getVictories() {
		return victories;
	}
	
	public void setVictories(int victories) {
		this.victories = victories;
	}
	
	public int getDefeats() {
		return defeats;
	}
	
	public void setDefeats(int defeats) {
		this.defeats = defeats;
	}
}
