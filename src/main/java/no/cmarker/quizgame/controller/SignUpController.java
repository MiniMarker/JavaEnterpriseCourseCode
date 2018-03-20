package no.cmarker.quizgame.controller;


import no.cmarker.quizgame.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 * @author Christian Marker on 12/03/2018 at 18:40.
 */
@Named
@SessionScoped
public class SignUpController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private String username;
	private String password;
	
	public String signUpUser(){
		
		boolean registred = false;
		
		try {
			registred = userService.createUser(username, password);
		} catch (Exception e){
			//NADA
		}
		
		if (registred){
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					userDetails, password, userDetails.getAuthorities());
			
			authenticationManager.authenticate(token);
			
			if (token.isAuthenticated()){
				SecurityContextHolder.getContext().setAuthentication(token);
			}
			
			return "/index.jsf?faces-redirect=true";
		} else {
			return "/signup.jsf?faces-redirect=true&error=true";
		}
	}
	
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
}
