package com.model;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class HashService  {

	final String secretKey = "My name is Luffy";
	final String initialVectorString = "0123456789123456"; // This has to be 16 characters
	
	private static String md5(final String input) throws NoSuchAlgorithmException {
		final MessageDigest md = MessageDigest.getInstance("MD5");
		final byte[] messageDigest = md.digest(input.getBytes());
		final BigInteger number = new BigInteger(1, messageDigest);
		return String.format("%032x", number);
	}

	private Cipher initCipher(final int mode)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException {
		final SecretKeySpec skeySpec = new SecretKeySpec(md5(secretKey).getBytes(), "AES");
		final IvParameterSpec initialVector = new IvParameterSpec(initialVectorString.getBytes());
		final Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
		cipher.init(mode, skeySpec, initialVector);
		return cipher;
	}

	public String encrypt(final String dataToEncrypt) {
		String encryptedData = null;
		try {
			// Initialize the cipher
			final Cipher cipher = this.initCipher(Cipher.ENCRYPT_MODE);
			// Encrypt the data
			final byte[] encryptedByteArray = cipher.doFinal(dataToEncrypt.getBytes());
			// Encode using Base64
			encryptedData = (new BASE64Encoder()).encode(encryptedByteArray);
		} catch (Exception e) {
			System.err.println("Problem encrypting the data");
			e.printStackTrace();
		}
		return encryptedData;
	}

	public String decrypt(final String encryptedData) {
		String decryptedData = null;
		try {
			// Initialize the cipher
			final Cipher cipher = this.initCipher(Cipher.DECRYPT_MODE);
			// Decode using Base64
			final byte[] encryptedByteArray = (new BASE64Decoder()).decodeBuffer(encryptedData);
			// Decrypt the data
			final byte[] decryptedByteArray = cipher.doFinal(encryptedByteArray);
			decryptedData = new String(decryptedByteArray, "UTF8");
		} catch (Exception e) {
			System.err.println("Problem decrypting the data");
			e.printStackTrace();
		}
		return decryptedData;
	}

}
