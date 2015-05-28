package wolfesoftware.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wolfesoftware.controller.exception.CookieControllerException;
import wolfesoftware.cookiebakery.CookieBakery;
import wolfesoftware.keyvalueasstring.exception.KeyAlreadyExistsException;
import wolfesoftware.keyvalueasstring.exception.KeyDoesNotExistException;

@Controller
public class CookieServerController {


	private final CookieBakery bakery;

	@Autowired
	public CookieServerController(CookieBakery bakery) {
		this.bakery = bakery;
	}

	@RequestMapping(value = "/addcookie/{cookieName}/{key}/{value}")
	public void addCookie(HttpServletRequest request, HttpServletResponse response, @PathVariable String cookieName, @PathVariable String key,
			@PathVariable String value, @RequestParam(defaultValue = "/showcookies") String redirectTo) throws IOException, KeyAlreadyExistsException {
		Cookie cookie = bakery.bakePersistentCookie(cookieName);
		cookie = bakery.addKeyValuePair(cookie, key, value);
		response.addCookie(cookie);
		response.sendRedirect(redirectTo + "?cookieName=" + cookieName + "" + "&key=" + key + "&value=" + value);
	}

	@RequestMapping(value = "/getvaluefromcookie/{cookieName}/{key}")
	public void getValueByCookieAndKey(HttpServletRequest request, HttpServletResponse response, @PathVariable String cookieName, @PathVariable String key,
			@RequestParam(defaultValue = "/showcookies") String redirectTo) throws IOException, KeyAlreadyExistsException {
		Cookie cookie = getCookieFromRequest(request, cookieName);
		response.sendRedirect(redirectTo + "?cookieName=" + cookieName + "" + "&key=" + key + "&value=" + bakery.getValue(cookie, key));
	}

	@RequestMapping(value = "/addvalue/{cookieName}/{key}/{value}")
	public void addValueToCookie(HttpServletRequest request, HttpServletResponse response, @PathVariable String cookieName, @PathVariable String key,
			@PathVariable String value) throws IOException, KeyAlreadyExistsException, CookieControllerException {
		Cookie cookie = getCookieFromRequest(request, cookieName);
		if (cookie == null) {
			throw new CookieControllerException("Cookie does not exist");
		}
		cookie = bakery.addKeyValuePair(cookie, key, value);
		response.addCookie(cookie);
		response.sendRedirect("/showcookies");
	}

	@RequestMapping(value = "/updatevalue/{cookieName}/{key}/{value}")
	public void updateValueInCookie(HttpServletRequest request, HttpServletResponse response, @PathVariable String cookieName, @PathVariable String key,
			@PathVariable String value) throws IOException, KeyAlreadyExistsException, CookieControllerException, KeyDoesNotExistException {
		Cookie cookie = getCookieFromRequest(request, cookieName);
		if (cookie == null) {
			throw new CookieControllerException("Cookie does not exist");
		}
		cookie = bakery.updateKeyValue(cookie, key, value);
		response.addCookie(cookie);
		response.sendRedirect("/showcookies");
	}

	@RequestMapping(value = "/deletebykey/{cookieName}/{key}")
	public void deleteValueInCookie(HttpServletRequest request, HttpServletResponse response, @PathVariable String cookieName, @PathVariable String key)
			throws IOException, KeyAlreadyExistsException, CookieControllerException, KeyDoesNotExistException {
		Cookie cookie = getCookieFromRequest(request, cookieName);
		if (cookie == null) {
			throw new CookieControllerException("Cookie does not exist");
		}
		cookie = bakery.removeKey(cookie, key);
		response.addCookie(cookie);
		response.sendRedirect("/showcookies");
	}

	@RequestMapping(value = "/removecookie/{cookieName}")
	public void removeCookie(HttpServletRequest request, HttpServletResponse response, @PathVariable String cookieName) throws IOException,
			KeyAlreadyExistsException, CookieControllerException {
		Cookie cookie = getCookieFromRequest(request, cookieName);
		if (cookie == null) {
			throw new CookieControllerException("Cookie does not exist");
		}
		cookie = bakery.makeCookieDie(cookie);
		response.addCookie(cookie);
		response.sendRedirect("/showcookies");
	}

	private Cookie getCookieFromRequest(HttpServletRequest request, String cookieName) {
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals(cookieName))
				return cookie;
		}
		return null;
	}

}
