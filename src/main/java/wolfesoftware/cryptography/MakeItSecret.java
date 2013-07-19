package wolfesoftware.cryptography;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import wolfesoftware.cryptography.exception.MakeItSecretException;

public class MakeItSecret {

	private String ALGO = "AES";
	private Key key;

	public MakeItSecret(String privateKey) throws MakeItSecretException {
		if (privateKey.length()!=16) throw new MakeItSecretException("Key should be 16 characters in length");
		key = generateKey(privateKey);
	}

	public String encrypt(String toBeEncrypted) throws MakeItSecretException {
		String encryptedValue = "";
		try {
			Cipher encryptor = Cipher.getInstance(ALGO);
			encryptor.init(Cipher.ENCRYPT_MODE, key);
			byte[] encVal = encryptor.doFinal(toBeEncrypted.getBytes());
			encryptedValue = new BASE64Encoder().encode(encVal);
		} catch (Exception e) {
			throw new MakeItSecretException(e);
		}
		return encryptedValue;
	}

	public String decrypt(String toBeDecrypted) throws MakeItSecretException {
		byte[] decryptedValue;
		try {
			Cipher decryptor = Cipher.getInstance(ALGO);
			decryptor.init(Cipher.DECRYPT_MODE, key);
			byte[] decodedValue = new BASE64Decoder()
					.decodeBuffer(toBeDecrypted);
			decryptedValue = decryptor.doFinal(decodedValue);
		} catch (Exception e) {
			throw new MakeItSecretException(e);
		}
		return new String(decryptedValue);
	}

	private Key generateKey(String privateKey) {
		Key key = new SecretKeySpec(privateKey.getBytes(), ALGO);
		return key;
	}

}
