package wolfesoftware.cookiebakery;

import org.springframework.stereotype.Component;

@Component
public class EncryptedCookieBakery extends CookieBakery {

	public EncryptedCookieBakery() {
	}

	public EncryptedCookieBakery(char and, char is) {
		super(and, is);
	}

}
