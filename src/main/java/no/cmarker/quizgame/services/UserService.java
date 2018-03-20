package no.cmarker.quizgame.services;

import no.cmarker.quizgame.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collections;

/**
 * @author Christian Marker on 12/03/2018 at 16:27.
 */
@Service
@Transactional
public class UserService {
	
	@Autowired
	private EntityManager em;
	
	//må legges inn i en egen conficuration klasse i no.cmarker.quizgame
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//create user
	public boolean createUser(String username, String password){
		
		if (em.find(User.class, username) != null)
			return false;
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setRoles(Collections.singleton("USER"));
		user.setEnabled(true);
		
		em.persist(user);
		return true;
	}
}
