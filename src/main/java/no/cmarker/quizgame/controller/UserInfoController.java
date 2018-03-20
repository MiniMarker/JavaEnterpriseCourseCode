package no.cmarker.quizgame.controller;


import no.cmarker.quizgame.entities.MatchStats;
import no.cmarker.quizgame.entities.User;
import no.cmarker.quizgame.services.MatchStatsService;
import no.cmarker.quizgame.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Christian Marker on 12/03/2018 at 18:39.
 */
@Named
@RequestScoped
public class UserInfoController {
	
	@Inject
	private MatchStatsService matchStatsService;
	
	public String getUsername(){
		return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
	}
	
	public MatchStats getStats(){
		return matchStatsService.getMatchStats(getUsername());
	}
}
