package wolfesoftware.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import wolfesoftware.controller.exception.CookieControllerException;
import wolfesoftware.cookiecrud.CookieBakery;
import wolfesoftware.keyvalueasstring.exception.KeyAlreadyExistsException;

@Controller
public class CookieController {

	private final CookieBakery bakery;

	@Autowired
	public CookieController(CookieBakery bakery) {
		this.bakery = bakery;
	}

	@RequestMapping(value = "/showcookies")
	public ModelAndView showCookies(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<String> cookies = new ArrayList<String>();
		getCookiesAsStringArray(request, cookies);
		ModelAndView modelAndView = new ModelAndView("showcookies");
		modelAndView.addObject("listOfCookies", cookies);
		return modelAndView;
	}

	@RequestMapping(value = "/addcookie/{cookieName}/{key}/{value}")
	public void addCookie(HttpServletRequest request,
			HttpServletResponse response,@PathVariable String cookieName, @PathVariable String key,
			@PathVariable String value) throws IOException,
			KeyAlreadyExistsException {
		Cookie cookie = bakery.bakePersistentCookie(cookieName);
		cookie = bakery.addKeyValuePair(cookie, key, value);
		response.addCookie(cookie);
		response.sendRedirect("/showcookies");
	}
	
	@RequestMapping(value = "/addvalue/{cookieName}/{key}/{value}")
	public void updateCookie(HttpServletRequest request,
			HttpServletResponse response,@PathVariable String cookieName, @PathVariable String key,
			@PathVariable String value) throws IOException,
			KeyAlreadyExistsException, CookieControllerException {
		Cookie cookie = getCookieFromRequest(request,cookieName);
		if (cookie==null){
			throw new CookieControllerException("Cookie does not exist");
		}
		cookie = bakery.addKeyValuePair(cookie, key, value);
		response.addCookie(cookie);
		response.sendRedirect("/showcookies");
	}
	
	@RequestMapping(value = "/removecookie/{cookieName}")
	public void removeCookie(HttpServletRequest request,
			HttpServletResponse response,@PathVariable String cookieName) throws IOException,
			KeyAlreadyExistsException, CookieControllerException {
		Cookie cookie = getCookieFromRequest(request,cookieName);
		if (cookie==null){
			throw new CookieControllerException("Cookie does not exist");
		}
		cookie = bakery.makeCookieDie(cookie);
		response.addCookie(cookie);
		response.sendRedirect("/showcookies");
	}

	private Cookie getCookieFromRequest(HttpServletRequest request, String cookieName) {
		for (Cookie cookie:request.getCookies()){
			if (cookie.getName().equals(cookieName))
				return cookie;
		}
		return null;
	}

	private void getCookiesAsStringArray(HttpServletRequest request,
			List<String> cookies) {
		if (!(null == request.getCookies())) {
			for (Cookie cookie : request.getCookies()) {
				cookies.add(getCookieAsString(cookie));
				System.out.println(getCookieAsString(cookie));
			}
		}
	}

	private String getCookieAsString(Cookie cookie) {
		return "name:" + cookie.getName() + " value:" + cookie.getValue()
				+ " age:" + cookie.getMaxAge() + " path:" + cookie.getPath()
				+ " domain:" + cookie.getDomain();
	}
}
