package no.cmarker.quizgame.entities;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author Christian Marker on 12/03/2018 at 15:49.
 */
@Entity
@Table(name="USERS")
public class User {
	
	@Id
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> roles;
	
	@NotNull
	private boolean enabled;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<String> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
