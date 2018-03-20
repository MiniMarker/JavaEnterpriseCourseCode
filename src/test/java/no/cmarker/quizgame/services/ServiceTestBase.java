package no.cmarker.quizgame.services;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Christian Marker on 26/02/2018 at 17:00.
 */
public class ServiceTestBase {
	
	@Autowired
	private ResetService resetService;
	
	@Before
	public void cleanDatabase() {
		resetService.resetDatabase();
	}
}
