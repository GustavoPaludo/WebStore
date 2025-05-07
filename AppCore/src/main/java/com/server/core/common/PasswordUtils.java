package com.server.core.common;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import io.micrometer.common.util.StringUtils;

public class PasswordUtils {

	private static final String ALGORITHM = "AES";
	private static final String SECRET_KEY = "asd1g45g7uas90";

	public static String encrypt(String data) throws Exception {
		SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encrypted = cipher.doFinal(data.getBytes());
		return Base64.getEncoder().encodeToString(encrypted);
	}

	public static String decrypt(String encryptedData) throws Exception {
		SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
		return new String(original);
	}

	public static boolean validatePassword(String entryPassword, String savedPassword) {
		if(StringUtils.isBlank(savedPassword) || StringUtils.isBlank(savedPassword)) {
			return false;
		}

		boolean isSamePassword = false;

		try {
			isSamePassword = decrypt(entryPassword).equals(decrypt(savedPassword));
		} catch (Exception e) {
			return false;
		}

		return isSamePassword;
	}
}
