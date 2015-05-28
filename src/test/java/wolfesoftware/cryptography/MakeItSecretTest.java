package wolfesoftware.cryptography;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import wolfesoftware.cryptography.exception.MakeItSecretException;

public class MakeItSecretTest {

	//must be 16 chars long
	private String symetricKey = "TheBestSecretKey";
	private MakeItSecret makeItSecret;
	
	@Before
	public void setup() throws MakeItSecretException{
		makeItSecret = new MakeItSecret(symetricKey);
	}

	@Test
	public void shouldEncryptStringUsingPrivateKey() throws MakeItSecretException{
		assertEquals("7rwAQi9DyJ17FAL9fdC5PQ==", makeItSecret.encrypt("hello world"));
	}
	
	@Test
	public void shouldDecryptStringUsingPrivateKey() throws MakeItSecretException{
		assertEquals("hello world", makeItSecret.decrypt("7rwAQi9DyJ17FAL9fdC5PQ=="));
	}
	
	@Test(expected=MakeItSecretException.class)
	public void shouldThrowSecretKeyIsNot16CharactersLongMakeItSecretException() throws MakeItSecretException{
		new MakeItSecret("boo");
	}
	
}
