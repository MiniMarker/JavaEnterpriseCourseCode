package no.cmarker.quizgame;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Christian Marker on 05/03/2018 at 16:31.
 */

@Controller
public class RedirectForwardHandler {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String forward(){
		return "forward:index.xhtml";
	}
	
}
