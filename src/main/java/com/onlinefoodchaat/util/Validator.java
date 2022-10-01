package com.onlinefoodchaat.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator 
{
	public static boolean validateClientSigupDetails(String email)
	{
		Pattern mail=Pattern.compile("@gmail.com");
		Matcher match=mail.matcher(email);
		if(!email.isEmpty()&&match.find())
		return true;
		else 
		return false;
	}
}
