package wolfesoftware.cookiecrud;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.Cookie;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import wolfesoftware.cryptography.exception.MakeItSecretException;
import wolfesoftware.keyvalueasstring.exception.KeyAlreadyExistsException;
import wolfesoftware.keyvalueasstring.exception.KeyDoesNotExistException;

public class CookieBakeryTest {

	CookieBakery bakery;
	private String key1 = "key1";
	private String value1 = "yummy1";
	private String key2 = "key2";
	private String value2 = "mmmm";
	private String key3 = "key3";
	private String value3 = "chocolate";
	private final char and = ';';
	private final char is = '=';

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		bakery = new CookieBakery(and, is);
	}

	@Test
	public void shouldCreatePersistentCookie() throws Exception {
		Cookie cookie = bakery.bakePersistentCookie();
		assertEquals(Integer.MAX_VALUE, cookie.getMaxAge());
	}

	@Test
	public void shouldWriteKeyValuePairToEmptyCookie()
			throws KeyAlreadyExistsException {
		Cookie cookie = bakery.bakePersistentCookie();
		bakery.addKeyValuePair(cookie, key1, value1);
		assertEquals(key1 + is + value1, cookie.getValue());
	}

	@Test
	public void shouldAddMultipleKeyValuePairsToCookie()
			throws KeyAlreadyExistsException {
		CookieBakery bakery = new CookieBakery();
		Cookie cookie = bakery.bakePersistentCookie();
		bakery.addKeyValuePair(cookie, key1, value1);
		bakery.addKeyValuePair(cookie, key2, value2);
		assertEquals(key2 + is + value2 + and + key1 + is + value1,
				cookie.getValue());
	}

	@Test
	public void shouldRemoveKeyValuePairsToCookie()
			throws KeyAlreadyExistsException {
		CookieBakery bakery = new CookieBakery();
		Cookie cookie = bakery.bakePersistentCookie();
		bakery.addKeyValuePair(cookie, key1, value1);
		bakery.addKeyValuePair(cookie, key2, value2);
		bakery.addKeyValuePair(cookie, key3, value3);
		bakery.removeKey(cookie, key2);
		assertEquals(key3 + is + value3 + and + key1 + is + value1,
				cookie.getValue());
	}

	@Test
	public void shouldUpdateValueInCookie() throws KeyAlreadyExistsException, KeyDoesNotExistException {
		CookieBakery bakery = new CookieBakery();
		Cookie cookie = bakery.bakePersistentCookie();
		bakery.addKeyValuePair(cookie, key1, value1);
		bakery.addKeyValuePair(cookie, key2, value2);
		bakery.addKeyValuePair(cookie, key3, value3);
		String newValue = "newValue";
		bakery.updateKeyValue(cookie, key2, newValue);
		assertEquals(key3 + is + value3 + and + key2 + is + newValue + and + key1 + is + value1,
				cookie.getValue());
	}
	
	@Test
	public void shouldGetValueInCookieForAGivenKey() throws KeyAlreadyExistsException, KeyDoesNotExistException, MakeItSecretException {
		CookieBakery bakery = new CookieBakery();
		Cookie cookie = bakery.bakePersistentCookie();
		bakery.addKeyValuePair(cookie, key1, value1);
		bakery.addKeyValuePair(cookie, key2, value2);
		bakery.addKeyValuePair(cookie, key3, value3);
		assertEquals(value2, bakery.getValue(cookie,key2));
	}

}
