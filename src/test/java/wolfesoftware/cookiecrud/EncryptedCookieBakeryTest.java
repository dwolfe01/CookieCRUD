package wolfesoftware.cookiecrud;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import wolfesoftware.cryptography.exception.MakeItSecretException;
import wolfesoftware.keyvalueasstring.exception.KeyAlreadyExistsException;
import wolfesoftware.keyvalueasstring.exception.KeyDoesNotExistException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/application-context.xml")
public class EncryptedCookieBakeryTest {

	@Resource
	EncryptedCookieBakery bakery;
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
	}

	@Test
	public void shouldCreatePersistentCookieWithNameAndEmptyPath() throws Exception {
		String name = "name";
		Cookie cookie = bakery.bakePersistentCookie(name);
		assertEquals(bakery.getDefaultCookieAge(), cookie.getMaxAge());
		assertEquals(name, cookie.getName());
		assertEquals("/", cookie.getPath());
	}

	@Test
	public void shouldWriteKeyValuePairToEmptyCookie() throws KeyAlreadyExistsException {
		Cookie cookie = bakery.bakePersistentCookie();
		cookie = bakery.addKeyValuePair(cookie, key1, value1);
		assertEquals(key1 + is + value1, cookie.getValue());
	}

	@Test
	public void shouldDeleteCookieBySettingMaxAgeToZero() throws KeyAlreadyExistsException {
		Cookie cookie = bakery.bakePersistentCookie();
		cookie = bakery.addKeyValuePair(cookie, key1, value1);
		cookie = bakery.makeCookieDie(cookie);
		assertEquals("/", cookie.getPath());
		assertEquals(0, cookie.getMaxAge());
		assertEquals("", cookie.getValue());
	}

	@Test
	public void shouldAddMultipleKeyValuePairsToCookie() throws KeyAlreadyExistsException {
		Cookie cookie = bakery.bakePersistentCookie();
		cookie = bakery.addKeyValuePair(cookie, key1, value1);
		cookie = bakery.addKeyValuePair(cookie, key2, value2);
		assertEquals(key1 + is + value1 + and + key2 + is + value2, cookie.getValue());
	}

	@Test
	public void shouldRemoveKeyValuePairsToCookie() throws KeyAlreadyExistsException {
		Cookie cookie = bakery.bakePersistentCookie();
		cookie = bakery.addKeyValuePair(cookie, key1, value1);
		cookie = bakery.addKeyValuePair(cookie, key2, value2);
		cookie = bakery.addKeyValuePair(cookie, key3, value3);
		cookie = bakery.removeKey(cookie, key2);
		assertEquals(key1 + is + value1 + and + key3 + is + value3, cookie.getValue());
	}

	@Test
	public void shouldUpdateValueInCookie() throws KeyAlreadyExistsException, KeyDoesNotExistException {
		Cookie cookie = bakery.bakePersistentCookie();
		cookie = bakery.addKeyValuePair(cookie, key1, value1);
		cookie = bakery.addKeyValuePair(cookie, key2, value2);
		cookie = bakery.addKeyValuePair(cookie, key3, value3);
		String newValue = "newValue";
		cookie = bakery.updateKeyValue(cookie, key2, newValue);
		assertEquals(key1 + is + value1 + and + key2 + is + newValue + and + key3 + is + value3, cookie.getValue());
	}

	@Test
	public void shouldGetValueInCookieForAGivenKey() throws KeyAlreadyExistsException, KeyDoesNotExistException, MakeItSecretException {
		Cookie cookie = bakery.bakePersistentCookie();
		cookie = bakery.addKeyValuePair(cookie, key1, value1);
		cookie = bakery.addKeyValuePair(cookie, key2, value2);
		cookie = bakery.addKeyValuePair(cookie, key3, value3);
		assertEquals(value2, bakery.getValue(cookie, key2));
	}

}
