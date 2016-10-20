package com.digitalriver.worldpayments.api.security5;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * A KeyHandler manages storage and lookup of merchant keypair and DRWP
 * public key
 */
public interface KeyHandler {

	/**
	 * Returns the private key associated with this handler, which should be
	 * used for creating new requests.
	 * 
	 * @return the key pair associated with this handler
	 */
	PrivateKey getPrivateKey();

	/**
	 * Returns the private key associated with this handler, which should be
	 * used for creating new requests.
	 * 
	 * Validates fingerprint with private key
	 * 
	 * 128 bit MD5 checksum of the modulus (fingerprint)
	 * 
	 * @param fingerprint
	 * @return
	 */
	PrivateKey getPrivateKey(byte[] fingerprint) throws IllegalArgumentException;

	/**
	 * 128 bit MD5 checksum of modulus (fingerprint)
	 * 
	 * @return a 16 bytes large array containing the fingerprint
	 */
	byte[] getPrivateKeyFingerprint();

	/**
	 * Returns the public key associated with DigitalRiver WorldPayments, which
	 * should be used for creating new requests.
	 * 
	 * @return a public key
	 */
	PublicKey getPublicKey();

	/**
	 * 128 bit MD5 checksum of the modulus (fingerprint)
	 * 
	 * @param fingerprint
	 * @return
	 */
	PublicKey getPublicKey(byte[] fingerprint) throws IllegalArgumentException;

	/**
	 * 128 bit MD5 checksum of modulus (fingerprint)
	 * 
	 * @return a 16 bytes large array containing the fingerprint
	 */
	byte[] getPublicKeyFingerprint();

}
