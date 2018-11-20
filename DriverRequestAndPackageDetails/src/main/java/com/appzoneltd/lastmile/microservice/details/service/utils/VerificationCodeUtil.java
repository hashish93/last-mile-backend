package com.appzoneltd.lastmile.microservice.details.service.utils;

import java.util.Random;

public class VerificationCodeUtil {

	public static String generateVerificationCode() {
		final int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			stringBuilder.append(numbers[random.nextInt(10)]);
		}
		return stringBuilder.toString();
	}
	
	public static String generateEmailVerificationCode() { 
		final String alphaNumericList = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; 
		Random random = new Random(); 
		StringBuilder stringBuilder = new StringBuilder(); 
		for (int i = 0; i < 50; i++) { 
			stringBuilder.append(alphaNumericList.charAt(random.nextInt(alphaNumericList.length()))); 
		} 
		return stringBuilder.toString(); 
	} 
}
