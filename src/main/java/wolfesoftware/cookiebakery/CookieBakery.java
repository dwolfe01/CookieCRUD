package wolfesoftware.cookiebakery;

import javax.servlet.http.Cookie;

import wolfesoftware.keyvalueasstring.KeyValueAsStringBuilder;
import wolfesoftware.keyvalueasstring.exception.KeyAlreadyExistsException;
import wolfesoftware.keyvalueasstring.exception.KeyDoesNotExistException;

//this class is responsible for creating and manipulating cookies. It is threadsafe.
public class CookieBakery {

	private char and = ',';
	private char is = '=';
	private final int defaultCookieAge = 60 * 60 * 24;

	public CookieBakery() {
	}

	public CookieBakery(char and, char is) {
		this.and = and;
		this.is = is;
	}

	public Cookie bakePersistentCookie() {
		return this.bakePersistentCookie("DefaultCookieName");
	}

	public Cookie bakePersistentCookie(String name) {
		Cookie cookie = new Cookie(name, "");
		cookie.setMaxAge(defaultCookieAge);
		cookie.setPath("/");
		return cookie;
	}

	public Cookie bakePersistentCookie(String name, String value) {
		Cookie cookie = new Cookie(name, "");
		cookie.setMaxAge(defaultCookieAge);
		cookie.setPath("/");
		cookie.setValue(value);
		return cookie;
	}

	public Cookie addKeyValuePair(Cookie cookie, String key, String value) throws KeyAlreadyExistsException {
		String cookieValue = cookie.getValue();
		KeyValueAsStringBuilder cookieValues = new KeyValueAsStringBuilder(and, is, cookieValue);
		cookieValues.addKeyValue(key, value);
		cookie = bakePersistentCookie(cookie.getName(), cookieValues.toString());
		return cookie;
	}

	public Cookie removeKey(Cookie cookie, String key) {
		String cookieValue = cookie.getValue();
		KeyValueAsStringBuilder cookieValues = new KeyValueAsStringBuilder(and, is, cookieValue);
		cookieValues.removeKey(key);
		cookie = bakePersistentCookie(cookie.getName(), cookieValues.toString());
		return cookie;
	}

	public Cookie updateKeyValue(Cookie cookie, String key, String newValue) throws KeyDoesNotExistException {
		String cookieValue = cookie.getValue();
		KeyValueAsStringBuilder cookieValues = new KeyValueAsStringBuilder(and, is, cookieValue);
		cookieValues.updateValue(key, newValue);
		cookie = bakePersistentCookie(cookie.getName(), cookieValues.toString());
		return cookie;
	}

	public String getValue(Cookie cookie, String key) {
		KeyValueAsStringBuilder cookieValues = new KeyValueAsStringBuilder(cookie.getValue());
		return cookieValues.getValue(key);
	}

	public Cookie makeCookieDie(Cookie cookie) {
		cookie = bakePersistentCookie(cookie.getName(), "");
		cookie.setMaxAge(0);
		return cookie;
	}

	public int getDefaultCookieAge() {
		return defaultCookieAge;
	}

}
