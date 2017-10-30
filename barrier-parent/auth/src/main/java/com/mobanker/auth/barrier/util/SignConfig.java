package com.mobanker.auth.barrier.util;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class SignConfig {
	/**
	 * DES 密钥(对外提供服务用)
	 */
	public static String desKey = "q1l2j7r8";
	/**
	 * RSA 公钥(对外提供服务用)
	 */
	public static final String QL_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4y+xGS2+6T8lSKrPzM1VjQNe7ixBNXAdkDZfSDpXwu4i4VKzo044y6ia54ZTamiiwXJtStJmiHQFthysRI+wz+nQVgHjhXjSvfigmzkwMWnlaKOak+G2ArRo/sDmxFj9ksO9s/XYrn9etcaK6nXHOVvk5YXN3kNmXI4XXUmn1zQIDAQAB";
	/**
	 * RSA 私钥(对外提供服务用)
	 */
	public static final String PRIVATE_KEY ="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAO+8U3TfDLLtJzOY+SdF0YH3OeFcpFUSi1XvIEhQ8w5xLpgsar8BVNqUVyjTdfyvr/kSlFED0gkvWfqnvpPR6XPYr7iRPGVj/AmJfw9+xNdrlBSqi6WUWLM+rqHNOuSmuYfWF+uxPQxCY+cIUHZsS+rjyZkU5q2dF2Z1MPLYUXkPAgMBAAECgYAk1DWt+qYkxrIDBzkfg2ZQJP7LVEQanaKyLO40rdrpGRIjZo9vkHDMs4VATFwP2Z2bEfOfDosxxgh4rVRluRW33i6UVxVmDG9pOIA3oJiqJgrf2m4N0b2qDV0hBDr8Hr14N7yMUrNP6m/5aW0hIr0G79C8BgyA3w1YxF+lpP7lQQJBAPrk6cdSa1eSSoz/DW13+0LeMtBoBBPOjbI2ZH3avvKf/tdBU0QL8h0rM1JiVx95wyz0/IMuc9cgsmvNqpLjOtkCQQD0nUetfj0hH90YIM5G/q2cBZLKoYodSQ7+gW823SNSAy+QKPpKAo4MaE4risosbDNjpDPXLE1qEIqzBuE5alInAkEA1NpAO4oA51qFRPldOvH/iMtZ8NVOID+slvTubJeYR97VJWmWarR3w6dS8yHbKCj1HL77O4+9V1W2CR+DBvsggQJAFdaw3olltb5dg9jx8Z6tKz/IIjUX2pN6NjiiEDjf/WNxbKJr7g2aVvs892A1uo+SavPu+OfopW6Co4catoCKgwJBAJwJfhFqhWLfWeZUQOq7+tMp/qHs4hMQtwLvBpLp8buiFJXB5Xn/MLbMEvxirRfAYT13DTWUetz3LFBPXgmWpG4=";
	public static String ALGORITHM = "RSA";
	/**
	 * 请求微信用-》线上公钥私钥  merchantId=10124
	 */
	//public static String desKey = "cwxf0113";
	//public static final String QL_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCVJADbqW6/hKystSU1LbR7zhbptH0aC4rMhhE60ZcPxHOaX3M1tavMm0o8hcG2Pz7SInyNo8uPbVmvJu6zxmbdKZuRB+9oI4spkwwKThFwaJwpGNy94xNyo7e6/uWZahmWhlAWOiH7d/1Nqi9CsZk2adEDoUq65KD2bfs2q5E9XQIDAQAB";
	//public static final String PRIVATE_KEY ="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJSRZsHrgFLzfjAdqt1ziNjKp1tw3Ja7smJDHfq2qRhCi4UUxZtJkU0GBz95tY1Sq9BinxF2kbQH9QcV6mACbv38gN1jrY/z8LCRHgarbR9HTVe2wngJ+j7Y0xj6/lQXZ8J8YhotNTVKi0eSyUMv56OxY4Nj+tmNyfqeVcpWJ+KXAgMBAAECgYEAgROuzm54SBmLGey8pbgaPhgbvSNaU49xlVOwuUF3uj8ejpoUcS1Ck0PYijqJFOatpdf5UafNQTfkl6pfvdh/atHbaMS01Hw9ihUnb6nzGhlaQGRQ2DZYOg0a/mO0JNor1duUXs7poH2fVwf29wbCvTDYHZrxslXGIdeNEwyvqfECQQDSYgQ5IWIZrSX9lZIFAuPiicWrclbsXoBpyEWyAp834McRdApox+fXH2kS2IooAzLiVs0hFma5J+XJjA4+6npvAkEAtMgl+wNDzUwRoA6BgvE4i6qmbBSIL793tsyDMtC7G5UQReCFs55qW/mr2bsSUsr9Ic6PqZuH0OUS935LAMLOWQJAbQARlhQAwrLeIxNycxw7O60L+DcmaBLST3xO99q56XaRTS6lEgK40NFaXERK2E5H99LnNsRndnkAajshSmGsCwJAXFqDTKB2WsKpnmLKtuIUSQV8z2oNyJa/yZrgy2zmqUiVb50rsH8VRrddm3V9/t3EQykLK1JRJ95euiDCoDjkwQJADCrd8wdhN3pFSn+u5x0rDOXGD0BclqkPqgbH8NH7O8e6O3bNHXxJtfRP5mrFv6ay04welcdSWn3pxeuB4nw2zg==";

	/**
	 * 得到私钥对象
	 * 
	 * @param key
	 *            密钥字符串（经过base64编码的秘钥字节）
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKey(String privateKey) {
		try {
			byte[] keyBytes;

			keyBytes = Base64.decode(privateKey);

			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

			KeyFactory keyFactory = KeyFactory.getInstance("RSA");

			PrivateKey privatekey = keyFactory.generatePrivate(keySpec);

			return privatekey;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取公钥对象
	 * 
	 * @param key
	 *            密钥字符串（经过base64编码秘钥字节）
	 * @throws Exception
	 */
	public static PublicKey getPublicKey(String publicKey) {

		try {

			byte[] keyBytes;

			keyBytes = Base64.decode(publicKey);

			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

			KeyFactory keyFactory = KeyFactory.getInstance("RSA");

			PublicKey publickey = keyFactory.generatePublic(keySpec);

			return publickey;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;

	}
}
