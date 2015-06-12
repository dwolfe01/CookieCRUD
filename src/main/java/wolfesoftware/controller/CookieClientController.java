package wolfesoftware.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
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
public class CookieClientController {

	String defaultCookieName;
	String cookieCrudServer;
	String me;

	public CookieClientController(String defaultCookieName, String cookieCrudServer, String me) {
		this.defaultCookieName = defaultCookieName;
		this.cookieCrudServer = cookieCrudServer;
		this.me = me;
	}

	@RequestMapping(value = { "/showcookies", "/" })
	public ModelAndView showCookies(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView modelAndView = new ModelAndView("showcookies");
		modelAndView.addObject("listOfCookies", getCookiesAsStringArray(request));
		return modelAndView;
	}

	@RequestMapping(value = "/sharevalue/{key}/{value}")
	public void shareValue(HttpServletRequest request, HttpServletResponse response, @PathVariable String key, @PathVariable String value) throws IOException {
		// TODO use String format
		response.sendRedirect(cookieCrudServer + "/addcookie/" + defaultCookieName + "/" + key + "/" + value + "?redirectTo=" + me + "/showvalue");
	}
	

	@RequestMapping(value = "/addvalue/{key}/{value}")
	public void addValue(HttpServletRequest request, HttpServletResponse response, @PathVariable String key, @PathVariable String value) throws IOException {
		// TODO use String format
		response.sendRedirect(cookieCrudServer + "/addvalue/" + defaultCookieName + "/" + key + "/" + value + "?redirectTo=" + me + "/showvalue");
	}

	@RequestMapping(value = "/getvalue/{key}")
	public void getValue(HttpServletRequest request, HttpServletResponse response, @PathVariable String key) throws IOException {
		response.sendRedirect(cookieCrudServer + "/getvaluefromcookie/" + defaultCookieName + "/" + key + "?redirectTo=" + me + "/showvalue");
	}

	@RequestMapping(value = "/showvalue")
	public ModelAndView showValue(HttpServletRequest request, @RequestParam(defaultValue = "cookieName") String cookieName,
			@RequestParam(defaultValue = "key") String key, @RequestParam(defaultValue = "value") String value) throws IOException {
		ModelAndView modelAndView = new ModelAndView("showvalue");
		modelAndView.addObject("aboutMe", "Server: " + cookieCrudServer + " me: " + me);
		modelAndView.addObject("cookieName", cookieName);
		modelAndView.addObject("key", key);
		modelAndView.addObject("value", value);
		return modelAndView;
	}
	
	private List<String> getCookiesAsStringArray(HttpServletRequest request) {
		List<String> cookies = new ArrayList<String>();
		if (!(null == request.getCookies())) {
			for (Cookie cookie : request.getCookies()) {
				cookies.add(getCookieAsString(cookie));
			}
		}
		return cookies;
	}
	
	private String getCookieAsString(Cookie cookie) {
		return "name:" + cookie.getName() + " value:" + cookie.getValue();
	}

}
