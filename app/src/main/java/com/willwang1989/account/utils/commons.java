package com.willwang1989.account.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class commons {

	private static Pattern doublePattern = Pattern.compile("-?\\d+(\\.\\d*)?");

	public static boolean isDouble(String string) {
		return doublePattern.matcher(string).matches();
	}

	public static long GetCurrentMonthTimeStamp() {
		long timeStamp = 0L;
		Date now = new Date();
		SimpleDateFormat fmt2 = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
		String sNow = fmt2.format(now);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",
				Locale.CHINA);
		Date t = null;
		try {
			t = formatter.parse(sNow + "-01");
			timeStamp = t.getTime() / 1000L;
		} catch (ParseException e) {
			System.out.println("unparseable using" + formatter);
		}
		return timeStamp;
	}

	public static String FormatDate(Long timeStamp) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CHINA);
		Date dt = new Date(timeStamp * 1000L);
		return fmt.format(dt);
	}

	public static String SHA256(String password) {

		try {
			MessageDigest md;
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());

			byte byteData[] = md.digest();

			// convert the byte to hex format method 1
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));
			}

			// System.out.println("Hex format : " + sb.toString());
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return password;

	}
}
