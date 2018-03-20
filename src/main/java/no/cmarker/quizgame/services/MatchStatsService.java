package no.cmarker.quizgame.services;

import no.cmarker.quizgame.entities.MatchStats;
import no.cmarker.quizgame.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Christian Marker on 12/03/2018 at 17:16.
 */
@Service
@Transactional
public class MatchStatsService {
	
	@Autowired
	private EntityManager em;
	
	public void reportVictory(String username){
		MatchStats matchStat = getMatchStats(username);
		
		matchStat.setVictories(matchStat.getVictories() + 1);
	}
	
	public void reportDefeat(String username){
		MatchStats matchStat = getMatchStats(username);
		
		matchStat.setDefeats(matchStat.getDefeats() + 1);
	}
	
	public MatchStats getMatchStats(String username){
		TypedQuery<MatchStats> query = em.createQuery("SELECT m FROM MatchStats m WHERE m.user.username = ?1", MatchStats.class);
		query.setParameter(1, username);
		
		List<MatchStats> results = query.getResultList();
		
		if (!results.isEmpty()){
			return results.get(0);
		}
		
		// if matchstat does not exist, Create
		User user = em.find(User.class, username);
		if (user == null){
			throw new IllegalArgumentException("User with username:" + username + " does not exist");
		}
		
		MatchStats matchStat = new MatchStats();
		matchStat.setUser(user);
		em.persist(matchStat);
		
		return matchStat;
		
	}
}
