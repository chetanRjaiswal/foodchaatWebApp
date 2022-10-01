package com.onlinefoodchaat.util;

import java.util.Random;

public class OtpUtil {
	public static int generateOtp() {
	    // It will generate 6 digit random Number.
	    // from 0 to 999999
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);

	    // this will convert any number sequence into 6 character.
	    return number;
	}
}
