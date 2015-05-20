package wolfesoftware.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/*
 * This is aware of the cookieCrudServer
 * Will set a value on the cookieCrudServer
 * Can we be used to retrieve a value from the cookieCrudServer
 */
@Controller
public class ExchangeValueController {

	String defaultCookieName;
	String cookieCrudServer;
	String me;
	
	public ExchangeValueController(String defaultCookieName, String cookieCrudServer, String me){
		this.defaultCookieName = defaultCookieName;
		this.cookieCrudServer = cookieCrudServer;
		this.me = me;
	}

	@RequestMapping(value = "/sharevalue/{key}/{value}")
	public void addValue(HttpServletRequest request, HttpServletResponse response, @PathVariable String key, @PathVariable String value) throws IOException {
		// TODO use String format
		response.sendRedirect(cookieCrudServer + "/addcookie/" + defaultCookieName + "/" + key + "/" + value + "?redirectTo=" + me + "/showvalue");
	}

	@RequestMapping(value = "/getvalue/{key}")
	public void getValue(HttpServletRequest request, HttpServletResponse response, @PathVariable String key) throws IOException {
		response.sendRedirect(cookieCrudServer + "/getvaluefromcookie/" + defaultCookieName + "/" + key + "?redirectTo=" + me + "/showvalue");
	}

	@RequestMapping(value = "/showvalue")
	public ModelAndView showValue(HttpServletRequest request, @RequestParam(defaultValue = "cookieName") String cookieName,
			@RequestParam(defaultValue = "key") String key, @RequestParam(defaultValue = "value") String value) throws IOException {
		ModelAndView modelAndView = new ModelAndView("showvalue");
		modelAndView.addObject("cookieName", cookieName);
		modelAndView.addObject("key", key);
		modelAndView.addObject("value", value);
		return modelAndView;
	}

}
