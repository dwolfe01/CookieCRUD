package wolfesoftware.cookiecrud;

import javax.servlet.http.Cookie;

import org.springframework.stereotype.Component;

import wolfesoftware.cryptography.exception.MakeItSecretException;
import wolfesoftware.keyvalueasstring.KeyValueAsStringBuilder;
import wolfesoftware.keyvalueasstring.exception.KeyAlreadyExistsException;
import wolfesoftware.keyvalueasstring.exception.KeyDoesNotExistException;

//this class is responsible for creating and manipulating cookies. It is threadsafe.
@Component
public class CookieBakery {
	
	private char and = ';';
	private char is = '=';
	
	public CookieBakery(){
	}

	public CookieBakery(char and, char is) {
		this.and = and;
		this.is = is;
	}

	public Cookie bakePersistentCookie() {
		Cookie cookie = new Cookie("", "");
		cookie.setMaxAge(Integer.MAX_VALUE);
		return cookie;
	}

	public Cookie addKeyValuePair(Cookie cookie, String key, String value) throws KeyAlreadyExistsException {
		String cookieValue = cookie.getValue();
		KeyValueAsStringBuilder cookieValues = new KeyValueAsStringBuilder(and, is, cookieValue);
		cookieValues.addKeyValue(key, value);
		cookie.setValue(cookieValues.toString());
		return cookie;
	}
	
	public Cookie removeKey(Cookie cookie,String key) {
		String cookieValue = cookie.getValue();
		KeyValueAsStringBuilder cookieValues = new KeyValueAsStringBuilder(and, is, cookieValue);
		cookieValues.removeKey(key);
		cookie.setValue(cookieValues.toString());
		return cookie;
	}

	public Cookie updateKeyValue(Cookie cookie, String key, String newValue) throws KeyDoesNotExistException {
		String cookieValue = cookie.getValue();
		KeyValueAsStringBuilder cookieValues = new KeyValueAsStringBuilder(and, is, cookieValue);
		cookieValues.updateValue(key, newValue);
		cookie.setValue(cookieValues.toString());
		return cookie;
	}

	public String getValue(Cookie cookie, String key){
		KeyValueAsStringBuilder cookieValues = new KeyValueAsStringBuilder(cookie.getValue());
		return cookieValues.getValue(key);
	}


}
